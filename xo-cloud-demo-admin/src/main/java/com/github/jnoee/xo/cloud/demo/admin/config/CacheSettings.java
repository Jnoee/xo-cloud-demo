package com.github.jnoee.xo.cloud.demo.admin.config;

import org.springframework.stereotype.Component;

import com.github.jnoee.xo.cache.AbstractCacheSettings;

@Component("demo.admin.config.CacheSettings")
public class CacheSettings extends AbstractCacheSettings {
  public CacheSettings() {
    addRegion("organ", "role", "user", "actor");
  }
}
