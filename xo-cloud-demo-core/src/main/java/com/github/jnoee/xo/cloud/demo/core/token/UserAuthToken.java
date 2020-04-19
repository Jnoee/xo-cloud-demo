package com.github.jnoee.xo.cloud.demo.core.token;

import com.github.jnoee.xo.auth.AuthToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthToken extends AuthToken {
  private static final long serialVersionUID = -7248460073711978127L;
  private Long id;
}
