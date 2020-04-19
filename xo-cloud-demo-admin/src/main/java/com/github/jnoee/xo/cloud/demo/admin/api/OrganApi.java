package com.github.jnoee.xo.cloud.demo.admin.api;

import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jnoee.xo.cloud.demo.admin.entity.Organ;
import com.github.jnoee.xo.cloud.demo.admin.model.OrganVo;
import com.github.jnoee.xo.cloud.demo.admin.service.OrganService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/organs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "机构")
public class OrganApi {
  @Autowired
  private OrganService organService;

  @GetMapping(path = "{id}")
  @ApiOperation("获取机构信息")
  @ApiImplicitParam(name = "id", value = "机构ID", required = true)
  @RequiresPermissions("organ:manage")
  public OrganVo get(@PathVariable @NotNull Long id) {
    Organ organ = organService.get(id);
    return new OrganVo(organ);
  }
}
