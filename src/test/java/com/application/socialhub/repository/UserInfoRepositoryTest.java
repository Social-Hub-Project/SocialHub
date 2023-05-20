package com.application.socialhub.repository;

import com.application.socialhub.model.Role;
import com.application.socialhub.model.Sex;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static java.time.Month.FEBRUARY;
import static java.time.Month.MARCH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Disabled
    void foundUserInfoByEmail() {
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
        userRepository.save(userEntity);

        // when
        UserInfo info = underTest.findUserInfoByEmail(email);

        // then
        assertThat(info).isEqualTo(userInfo);
    }

}