package com.github.jnoee.xo.cloud.demo.film.api;

import javax.validation.constraints.NotBlank;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jnoee.xo.cloud.demo.film.entity.Film;
import com.github.jnoee.xo.cloud.demo.film.model.FilmVo;
import com.github.jnoee.xo.cloud.demo.film.service.FilmService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/films")
public class FilmApi {
  @Autowired
  private FilmService filmService;

  @GetMapping(path = "{id}")
  @ApiOperation("获取影片信息")
  @ApiImplicitParam(name = "id", value = "影片ID", required = true)
  @RequiresAuthentication
  public FilmVo get(@PathVariable @NotBlank String id) {
    Film film = filmService.get(id);
    return new FilmVo(film);
  }
}
