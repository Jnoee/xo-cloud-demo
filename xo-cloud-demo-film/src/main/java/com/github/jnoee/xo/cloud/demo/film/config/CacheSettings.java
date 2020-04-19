package com.github.jnoee.xo.cloud.demo.film.config;

import org.springframework.stereotype.Component;

import com.github.jnoee.xo.cache.AbstractCacheSettings;

@Component("demo.film.config.CacheSettings")
public class CacheSettings extends AbstractCacheSettings {
  public CacheSettings() {
    addRegion("film");
  }
}
