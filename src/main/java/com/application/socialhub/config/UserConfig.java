package com.application.socialhub.config;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            UserEntity adam = new UserEntity(Role.USER,
                    "adam@email.com",
                    passwordEncoder.encode("password"),
                    true,
                    LocalDate.of(2000, JANUARY, 6)
            );

            UserEntity anna = new UserEntity(Role.USER,
                    "anna@email.com",
                    passwordEncoder.encode("anna"),
                    true,
                    LocalDate.of(2002, MARCH, 12)
            );

            UserEntity marek = new UserEntity(Role.USER,
                    "marek@email.com",
                    passwordEncoder.encode("marek"),
                    false,
                    LocalDate.of(2001, MARCH, 14)
            );
            userRepository.saveAll(List.of(adam, anna, marek));
        };
    }

}
