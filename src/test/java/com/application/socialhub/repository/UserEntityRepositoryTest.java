package com.application.socialhub.repository;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.Sex;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class UserEntityRepositoryTest {

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
        UserInfo userInfo = new UserInfo("john",
                "doe",
                LocalDate.of(2000,FEBRUARY,12),
                "Krakow",
                false,
                " sd",
                " ",
                Sex.MALE,
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                email,
                "password",
                true,
                LocalDate.of(2001, MARCH, 14),
                true,
                userInfo
        );
        underTest.save(userEntity);

        // when
        boolean exists = underTest.selectExistsEmail(email);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void isUserEnabled() {
        // given
        String email = "kamila@gmail.com";
        UserInfo userInfo = new UserInfo("john",
                "doe",
                LocalDate.of(2000,FEBRUARY,12),
                "Krakow",
                false,
                " sd",
                " ",
                Sex.MALE,
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                email,
                "password",
                true,
                LocalDate.of(2001, MARCH, 14),
                true,
                userInfo
        );
        underTest.save(userEntity);

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
        UserInfo userInfo = new UserInfo("john",
                "doe",
                LocalDate.of(2000,FEBRUARY,12),
                "Krakow",
                false,
                " sd",
                " ",
                Sex.MALE,
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                email,
                "password",
                true,
                LocalDate.of(2001, MARCH, 14),
                false,
                userInfo
        );
        underTest.save(userEntity);

        // when
        boolean enabled = underTest.selectUserEnabled(email);
        // then
        assertThat(enabled).isFalse();
    }
    @Test
    void foundUserByEmail() {
        // given
        String email = "kamila@gmail.com";
        UserInfo userInfo = new UserInfo("john",
                "doe",
                LocalDate.of(2000,FEBRUARY,12),
                "Krakow",
                false,
                " sd",
                " ",
                Sex.MALE,
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                email,
                "password",
                true,
                LocalDate.of(2001, MARCH, 14),
                true,
                userInfo
        );
        underTest.save(userEntity);

        // when
        UserEntity user = underTest.findUserByEmail(email);
        // then
        assertEquals(user, userEntity);
    }

    @Test
    void didNotFoundUserByEmail() {
        // given
        String emailTest = "kamil@gmail.com";
        String email = "kamila@gmail.com";
        UserInfo userInfo = new UserInfo("john",
                "doe",
                LocalDate.of(2000,FEBRUARY,12),
                "Krakow",
                false,
                " sd",
                " ",
                Sex.MALE,
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                email,
                "password",
                true,
                LocalDate.of(2001, MARCH, 14),
                true,
                userInfo
        );
        underTest.save(userEntity);

        // when
        UserEntity user = underTest.findUserByEmail(emailTest);
        // then
        assertNotEquals(user, userEntity);
    }
}