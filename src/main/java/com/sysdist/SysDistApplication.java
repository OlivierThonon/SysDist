package com.sysdist;

import com.sysdist.models.Article;
import com.sysdist.models.Users;
import com.sysdist.repositories.ArticleRepository;
import com.sysdist.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class SysDistApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysDistApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ArticleRepository articleRepository, UserRepository userRepository) {
        return (args) -> {

            articleRepository.save(new Article("Toblerone amour personnalisée", "Alimentaire", 10, 15));
            articleRepository.save(new Article("Rose éternelle sous cloche", "Decoration", 50, 25));
            articleRepository.save(new Article("Baby Groot Pot de Fleur", "Decoration", 5, 75));
            articleRepository.save(new Article("Filtre à eau extérieur", "Survie", 2, 35));
            articleRepository.save(new Article("Pelle de camping pliante", "Survie", 2, 50));

            userRepository.save(new Users("thol", "$2y$10$OvjWT6mX2bkzbq1alrCai.phjLdP9ltIadIRJhzKRlstzJz8dS9By", 100));

            for (Article customer : articleRepository.findAll()) {
                System.out.println(customer.toString());
            }
        };
    }
}
