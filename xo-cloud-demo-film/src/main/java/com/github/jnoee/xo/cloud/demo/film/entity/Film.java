package com.github.jnoee.xo.cloud.demo.film.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.github.jnoee.xo.jpa.entity.UuidEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Film")
@Cache(region = "film", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
public class Film extends UuidEntity {
  private String name;
}
