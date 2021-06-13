package com.sabbih.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class Stylzcrawlerv1Application {


    public static void main(String[] args) {
        SpringApplication.run(Stylzcrawlerv1Application.class, args);
        new Controller().start();

    }

}
