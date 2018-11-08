package com.github.jnoee.xo.cloud.demo.admin.model;

import com.github.jnoee.xo.cloud.demo.admin.entity.User;
import com.github.jnoee.xo.utils.BeanUtils;

import lombok.Data;

@Data
public class UserVo {
  private String username;
  private String name;
  private Integer ordinal;

  public static UserVo forGet(User user) {
    UserVo vo = new UserVo();
    BeanUtils.copyFields(user, vo);
    return vo;
  }
}
