package com.github.jnoee.xo.cloud.demo.admin.model;

import com.github.jnoee.xo.cloud.demo.admin.entity.Organ;
import com.github.jnoee.xo.utils.BeanUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganVo {
  @ApiModelProperty(value = "ID")
  private String id;
  @ApiModelProperty(value = "名称")
  private String name;

  public OrganVo(Organ organ) {
    BeanUtils.copyFields(organ, this);
  }
}
