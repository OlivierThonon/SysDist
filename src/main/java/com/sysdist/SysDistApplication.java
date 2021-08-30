package com.sysdist;

import com.sysdist.models.Article;
import com.sysdist.repositories.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SysDistApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysDistApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ArticleRepository repository) {
        return (args) -> {

            repository.save(new Article("Toblerone amour personnalisée", "Alimentaire", 10, 15));
            repository.save(new Article("Rose éternelle sous cloche", "Decoration", 50, 25));
            repository.save(new Article("Baby Groot Pot de Fleur", "Decoration", 5, 75));
            repository.save(new Article("Filtre à eau extérieur", "Survie", 2, 35));
            repository.save(new Article("Pelle de camping pliante", "Survie", 2, 50));

            for (Article customer : repository.findAll()) {
                System.out.println(customer.toString());
            }
        };
    }
}
