package com.github.jnoee.xo.cloud.demo.admin.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jnoee.xo.cloud.demo.admin.entity.User;
import com.github.jnoee.xo.cloud.demo.admin.model.UserDto;
import com.github.jnoee.xo.cloud.demo.admin.model.UserVo;
import com.github.jnoee.xo.cloud.demo.admin.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "用户")
public class UserApi {
  @Autowired
  private UserService userService;

  @GetMapping(path = "{id}")
  @ApiOperation("获取用户信息")
  @ApiImplicitParam(name = "id", value = "用户ID", required = true)
  @RequiresPermissions("user:manage")
  public UserVo get(@PathVariable @NotNull Long id) {
    User user = userService.get(id);
    return UserVo.forGet(user);
  }

  @ApiOperation(value = "新增用户")
  @PostMapping
  @RequiresPermissions("user:manage")
  public void create(@Valid UserDto dto) {
    userService.create(dto.toUser());
  }
}
