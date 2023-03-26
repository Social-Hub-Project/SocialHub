package com.application.socialhub.config;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import com.application.socialhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {

        return args -> {
            User adam = new User(Role.USER,
                    "adam@email.com",
                    "adam",
                    "password",
                    LocalDate.of(2000, JANUARY, 6).toString()
            );

            User anna = new User(Role.USER,
                    "anna@email.com",
                    "anna",
                    "anna1",
                    LocalDate.of(2002, MARCH, 12).toString()
            );

            User marek = new User(Role.USER,
                    "marek@email.com",
                    "marek",
                    "admin",
                    LocalDate.of(2001, MARCH, 14).toString()
            );
            userRepository.saveAll(List.of(adam, anna, marek));
        };
    }

}