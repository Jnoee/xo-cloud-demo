package com.github.jnoee.xo.cloud.demo.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.github.jnoee.xo.jpa.dao.DaoScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableJpaRepositories
@EntityScan
@DaoScan
public class FilmServer {
  public static void main(String[] args) {
    SpringApplication.run(FilmServer.class, args);
  }
}
