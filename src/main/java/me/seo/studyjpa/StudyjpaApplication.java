package me.seo.studyjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
//@EnableJpaRepositories // spring boot 자동 설정
// query 전략 사용 default create if not found ,@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE)
@Import(register.class) // 등록
public class StudyjpaApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(StudyjpaApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run();
    }

}
