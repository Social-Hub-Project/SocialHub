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
                                        RatingRepository ratingRepository,CategoryRepository categoryRepository,
                                        FollowerRepository followerRepository) {

        return args -> {


            UserInfo adamDetails = new UserInfo("Adam", "Moscicki",
                    LocalDate.of(2000, MARCH, 29), "Warszawa", false, "aaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "",
                    Sex.MALE, LocalDate.of(2023, JANUARY, 22));

            UserInfo annaDetails = new UserInfo("Anna", "Moscicki",
                    LocalDate.of(2000, MARCH, 29), "Warszawa", false,
                    "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                    "",
                    Sex.MALE, LocalDate.of(2023, JANUARY, 22));

            UserInfo marekDetails = new UserInfo("Marek", "Moscicki",
                    LocalDate.of(2000, MARCH, 29), "Warszawa", false, "cccccccccccccccccccccccccccccc" +
                    "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                    "cccccccccccccccccccccccccccccccccccccccccccc",
                    "",
                    Sex.MALE, LocalDate.of(2023, JANUARY, 22));

            UserEntity adam = new UserEntity(Role.USER,
                    "adam@email.com",
                    passwordEncoder.encode("password"),
                    true,
                    LocalDate.of(2000, JANUARY, 6), false, adamDetails);


            UserEntity anna = new UserEntity(Role.USER,
                    "anna@email.com",
                    passwordEncoder.encode("anna"),
                    true,
                    LocalDate.of(2002, MARCH, 12), false, annaDetails);


            UserEntity marek = new UserEntity(Role.USER,
                    "marek@email.com",
                    passwordEncoder.encode("marek"),
                    false,
                    LocalDate.of(2001, MARCH, 14), false, marekDetails
            );
            userRepository.saveAll(List.of(adam, anna, marek));

            Post post1 = new Post("Post 1", false, LocalDate.now(),"" ,adam);

            postRepository.save_data(post1.getId(),post1.getDescription(),post1.isBlocked(),post1.getCreate_at(),
                    post1.getPhoto_source(),post1.getUserEntity().getId());
            Long nextId=postRepository.findMaxId()+1;
            Post post2 = new Post("Post 2", false, LocalDate.now(),"" ,marek);
            postRepository.save_data(nextId,post2.getDescription(),post2.isBlocked(),post2.getCreate_at(),
                    post2.getPhoto_source(),post2.getUserEntity().getId());


            Comment comment1 = new Comment("Comment 1", LocalDate.now(), adam, post1);
            commentRepository.save_data(comment1.getId(),comment1.getContent(),comment1.getCreated_at(),
                    comment1.getUser().getId(),comment1.getPost().getId());

            Comment comment2 = new Comment("Comment 2", LocalDate.now(), marek, post2);
            long nextPost=commentRepository.findMaxIdPost();
            Long nextId2=commentRepository.findMaxId()+1;

            commentRepository.save_data(nextId2,comment2.getContent(),comment2.getCreated_at(),
                    comment2.getUser().getId(),nextPost);

            Rating rating1 = new Rating(true,LocalDate.now() ,LocalDate.now(),adam, post1);

            ratingRepository.save_data(rating1.getId(),rating1.isValue(),rating1.getCreated_at(),rating1.getModified_at(),
                    rating1.getUser().getId(),rating1.getPost().getId());
            Rating rating2 = new Rating(false,LocalDate.now() ,LocalDate.now(),marek, post2);
            long nextPost2=ratingRepository.findMaxIdPost();
            Long nextId3=ratingRepository.findMaxId()+1;
            ratingRepository.save_data(nextId3,rating2.isValue(),rating2.getCreated_at(),rating2.getModified_at(),
                    rating2.getUser().getId(),nextPost2);



            Category category1 = new Category("Category 1");


            categoryRepository.saveAll(List.of(category1, category1));

        };




    }

}
