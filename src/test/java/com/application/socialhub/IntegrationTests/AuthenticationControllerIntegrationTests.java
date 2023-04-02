package com.application.socialhub.IntegrationTests;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import com.application.socialhub.repository.UserRepository;
import com.github.javafaker.Faker;

import com.application.socialhub.service.AuthenticationService;
import com.application.socialhub.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.application.socialhub.model.Sex.MALE;
import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;

    private final Faker faker = new Faker();

    @Test
    void canRegisterNewStudent() throws Exception {
        // given
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest("Adam",
                "Bialy",
                "adam@gmail.com",
                "password",
                MALE,
                "Krakow",
                LocalDateTime.now().toString());

        User user = new User(Role.USER,
                "adam@gmail.com",
                "Adam",
                "password",
                LocalDateTime.now().toString());

//        // when
//        ResultActions resultActions = mockMvc
//                .perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userRegistrationRequest)));
//        // then
//        resultActions.andExpect(status().isOk());
//        List<User> users = userRepository.findAll();
//        assertThat(users)
//                .usingElementComparatorIgnoringFields("id")
//                .contains(user);
    }

}
