package com.github.jnoee.xo.cloud.demo.film.model;

import com.github.jnoee.xo.cloud.demo.film.entity.Film;
import com.github.jnoee.xo.utils.BeanUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FilmVo {
  @ApiModelProperty(value = "ID")
  private String id;
  @ApiModelProperty(value = "名称")
  private String name;

  public FilmVo(Film film) {
    BeanUtils.copyFields(film, this);
  }
}
