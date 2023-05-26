package com.application.socialhub.config;

import com.application.socialhub.model.*;
import com.application.socialhub.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

import static java.time.Month.*;

@Configuration
public class UserConfig {

        @Bean
        CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        PostRepository postRepository, CommentRepository commentRepository,
                        RatingRepository ratingRepository, CategoryRepository categoryRepository,
                        FollowerRepository followerRepository, PostCategoryRepository postCategoryRepository) {

                return args -> {

                        // add comments
                        UserInfo adamDetails = new UserInfo("Adam", "Moscicki",
                                        LocalDate.of(2000, MARCH, 29), "Warszawa", false,
                                        "/exampleImages/userIcon.png",
                                        "/exampleImages/background.png",
                                        Sex.MALE, LocalDate.of(2023, JANUARY, 22));

                        UserInfo annaDetails = new UserInfo("Anna", "Moscicki",
                                        LocalDate.of(2000, MARCH, 29), "Warszawa", false,
                                        "/exampleImages/userIcon.png",
                                        "/exampleImages/background.png",
                                        Sex.MALE, LocalDate.of(2023, JANUARY, 22));

                        UserInfo marekDetails = new UserInfo("Marek", "Moscicki",
                                        LocalDate.of(2000, MARCH, 29), "Warszawa", false,
                                        "/exampleImages/userIcon.png",
                                        "/exampleImages/background.png",
                                        Sex.MALE, LocalDate.of(2023, JANUARY, 22));

                        UserEntity adam = new UserEntity(Role.USER,
                                        "adam@email.com",
                                        passwordEncoder.encode("password"),
                                        true,
                                        LocalDate.of(2000, JANUARY, 6), true, adamDetails);

                        UserEntity anna = new UserEntity(Role.USER,
                                        "anna@email.com",
                                        passwordEncoder.encode("anna"),
                                        true,
                                        LocalDate.of(2002, MARCH, 12), true, annaDetails);

                        UserEntity marek = new UserEntity(Role.USER,
                                        "marek@email.com",
                                        passwordEncoder.encode("marek"),
                                        false,
                                        LocalDate.of(2001, MARCH, 14), false, marekDetails);
                        userRepository.saveAll(List.of(adam, anna, marek));

                        // add posts
                        Post post1 = new Post("Post 1", true, LocalDate.of(2022, 5, 1), "/exampleImages/cat.jpg", adam);
                        Post post2 = new Post("Post 2", false, LocalDate.of(2022, 5, 1), "/exampleImages/cat.jpg",
                                        marek);
                        postRepository.saveAll(List.of(post1, post2));


            UserEntity marek = new UserEntity(Role.USER,
                    "marek@email.com",
                    passwordEncoder.encode("marek"),
                    true,
                    LocalDate.of(2001, MARCH, 14), true, marekDetails
            );
            userRepository.saveAll(List.of(adam, anna, marek));


                        commentRepository.saveAll(List.of(comment1, comment2));

                        // save ratings

                        Rating rating1 = new Rating(-1, LocalDate.now(), LocalDate.now(), adam, post1);
                        Rating rating2 = new Rating(1, LocalDate.now(), LocalDate.now(), marek, post2);

                        ratingRepository.saveAll(List.of(rating1, rating2));

                        // save categories
                        Category category1 = new Category("Easter");
                        Category category2 = new Category("Christmas");

                        categoryRepository.saveAll(List.of(category1, category2));

                        // save postCategory

                        PostCategory postCategory = new PostCategory(post2, category1);
                        PostCategory postCategory1 = new PostCategory(post1, category2);

                        postCategoryRepository.saveAll(List.of(postCategory, postCategory1));

                        Followers followers1 = new Followers(LocalDate.now(), adam, anna);
                        Followers followers2 = new Followers(LocalDate.now(), adam, marek);
                        Followers followers3 = new Followers(LocalDate.now(), anna, marek);

                        followerRepository.saveAll(List.of(followers1, followers2, followers3));

                };

            Followers followers1= new Followers(LocalDate.now(),adam,anna);
            Followers followers2= new Followers(LocalDate.now(),adam,marek);
            Followers followers3= new Followers(LocalDate.now(),anna,marek);
            Followers followers4= new Followers(LocalDate.now(),marek,adam);



            followerRepository.saveAll(List.of(followers1,followers2,followers3,followers4));

        };


    }

}
