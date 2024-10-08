package ex02;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

@SpringBootApplication
@EntityScan(basePackages = {"ex02.domain"}) // bootstrapclass에 이 어노테이션이 꼭 필요하다1
@EnableJpaRepositories(basePackages = {"ex02.repository"}) // bootstrapclass에 이 어노테이션이 꼭 필요하다2
public class Ch04Ex02Application {
    public static void main(String[] args) {
        SpringApplication.run(Ch04Ex02Application.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}