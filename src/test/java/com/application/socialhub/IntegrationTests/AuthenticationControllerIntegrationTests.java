// package com.application.socialhub.IntegrationTests;
// import com.application.socialhub.dto.UserRegistrationRequest;
// import com.application.socialhub.model.Role;
// import com.application.socialhub.model.Sex;
// import com.application.socialhub.model.UserEntity;
// import com.application.socialhub.model.UserInfo;
// import com.application.socialhub.repository.UserRepository;

// import com.application.socialhub.service.AuthenticationService;
// import com.application.socialhub.service.RegistrationService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.web.servlet.MockMvc;

// import java.time.LocalDate;
// import java.time.LocalDateTime;

// import static com.application.socialhub.model.Sex.MALE;
// import static java.time.Month.FEBRUARY;
// import static org.springframework.http.RequestEntity.post;

// @SpringBootTest
// @TestPropertySource(
// locations = "classpath:application-it.properties"
// )
// @AutoConfigureMockMvc
// public class AuthenticationControllerIntegrationTests {

// @Autowired
// private MockMvc mockMvc;

// @Autowired
// private ObjectMapper objectMapper;

// @Autowired
// private AuthenticationService authenticationService;

// @Autowired
// private RegistrationService registrationService;

// @Autowired
// private UserRepository userRepository;

// @Test
// @Disabled
// void canRegisterNewStudent() {
// // given
// UserRegistrationRequest userRegistrationRequest = new
// UserRegistrationRequest("Adam",
// "Bialy",
// "adam@gmail.com",
// "password",
// MALE,
// "Krakow",
// LocalDateTime.now().toString());

// UserInfo userInfo = new UserInfo("john",
// "doe",
// LocalDate.of(2000,FEBRUARY,12),
// "Krakow",
// false,
// " sd",
// " ",
// Sex.MALE,
// LocalDate.now());

// UserEntity userEntity = new UserEntity(Role.USER,
// "adam@gmail.com",
// "password",
// true,
// LocalDate.now(),
// true,
// userInfo);

// // // when
// // ResultActions resultActions = mockMvc
// // .perform(post("/auth/register")
// // .contentType(MediaType.APPLICATION_JSON)
// // .accept(MediaType.APPLICATION_JSON)
// // .content(objectMapper.writeValueAsString(userRegistrationRequest)));
// // // then
// // resultActions.andExpect(status().isOk());
// // List<User> users = userRepository.findAll();
// // assertThat(users)
// // .usingElementComparatorIgnoringFields("id")
// // .contains(user);
// }

// }