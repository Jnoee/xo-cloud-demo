package com.github.jnoee.xo.cloud.demo.film.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jnoee.xo.cloud.demo.film.entity.Film;
import com.github.jnoee.xo.jpa.dao.Dao;
import com.github.jnoee.xo.message.MessageSource;

@Service
public class FilmService {
  @Resource
  private Dao<Film> filmDao;
  @Autowired
  private MessageSource messageSource;

  public Film get(String id) {
    Film film = filmDao.get(id);
    if (null == film) {
      messageSource.thrown("e.film.get.not-exist", id);
    }
    return film;
  }
}
