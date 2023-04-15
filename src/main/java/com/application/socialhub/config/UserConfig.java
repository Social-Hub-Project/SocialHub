package com.application.socialhub.config;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.Sex;
import com.application.socialhub.model.UserInfo;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.repository.UserDetailsRepository;
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
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                        UserDetailsRepository userDetailsRepository) {

        return args -> {

            UserInfo adamDetails = new UserInfo("Adam","Moscicki",
                    LocalDate.of(2000, MARCH, 29),"Warszawa",false,"aaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "",
                    Sex.MALE,LocalDate.of(2023, JANUARY, 22));

            UserInfo annaDetails = new UserInfo("Anna","Moscicki",
                    LocalDate.of(2000, MARCH, 29),"Warszawa",false,
                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                    "",
                    Sex.MALE,LocalDate.of(2023, JANUARY, 22));

            UserInfo marekDetails = new UserInfo("Marek","Moscicki",
                    LocalDate.of(2000, MARCH, 29),"Warszawa",false,"cccccccccccccccccccccccccccccc" +
                    "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                    "cccccccccccccccccccccccccccccccccccccccccccc",
                    "",
                    Sex.MALE,LocalDate.of(2023, JANUARY, 22));

            UserEntity adam = new UserEntity(Role.USER,
                    "adam@email.com",
                    passwordEncoder.encode("password"),
                    true,
                    LocalDate.of(2000, JANUARY, 6),false,adamDetails);


            UserEntity anna = new UserEntity(Role.USER,
                    "anna@email.com",
                    passwordEncoder.encode("anna"),
                    true,
                    LocalDate.of(2002, MARCH, 12),false,annaDetails);


            UserEntity marek = new UserEntity(Role.USER,
                    "marek@email.com",
                    passwordEncoder.encode("marek"),
                    false,
                    LocalDate.of(2001, MARCH, 14),false,marekDetails
            );
            userRepository.saveAll(List.of( anna, marek,adam));
//            userDetailsRepository.saveAll(List.of(annaDetails,marekDetails,adamDetails));

        };
    }

}
