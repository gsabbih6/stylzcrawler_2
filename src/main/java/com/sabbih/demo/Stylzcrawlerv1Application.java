package com.sabbih.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableCassandraRepositories
public class Stylzcrawlerv1Application {


    public static void main(String[] args) {
        SpringApplication.run(Stylzcrawlerv1Application.class, args);
        new Controller().start();

    }

}
