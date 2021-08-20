package me.seo.studyjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class StudyjpaApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(StudyjpaApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run();
    }

}
