package com.application.socialhub.repository;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void doesEmailExists() {
        // given
        String email = "kamila@gmail.com";

        User user = new User(
                Role.USER,
                email,
                "Kamila",
                "password",
                "01-04-2023"
        );
        underTest.save(user);

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void isUserEnabled() {
        // given
        String email = "kamila@gmail.com";

        User user = new User(
                Role.USER,
                email,
                "Kamila",
                "password",
                "01-04-2023"
        );
        underTest.save(user);

        // when
        underTest.enableUser(email);
        boolean enabled = underTest.selectUserEnabled(email);

        // then
        assertThat(enabled).isTrue();
    }

    @Test
    void isUserNotEnabled() {
        // given
        String email = "kamila@gmail.com";

        User user = new User(
                Role.USER,
                email,
                "Kamila",
                "password",
                "01-04-2023"
        );
        underTest.save(user);

        // when
        boolean enabled = underTest.selectUserEnabled(email);
        // then
        assertThat(enabled).isFalse();
    }
}