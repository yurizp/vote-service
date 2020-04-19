package com.vote.voteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@EnableMongoAuditing
@EnableSwagger2WebFlux
@SpringBootApplication(scanBasePackages = "com.vote.*")
public class VoteServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(VoteServiceApplication.class, args);
  }
}
