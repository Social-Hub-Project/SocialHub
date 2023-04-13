package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.exception.DuplicateResourceException;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static com.application.socialhub.model.Sex.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private EmailValidatorService emailValidator;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailSender emailSender;

    private RegistrationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RegistrationService(userDAO,
                emailValidator,
                confirmationTokenService,
                passwordEncoder,
                emailSender);
    }

    @Test
    void registerShouldCreateUser() {

        // given
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Dominik",
                "Kowal",
                "dkowal@gmail.com",
                "password",
                MALE,
                "Kraków",
                "12-02-2000"
        );

        User user = new User(Role.USER,
                "dkowal@gmail.com",
                passwordEncoder.encode("password"),
                true,
                LocalDate.now());

        given(emailValidator.test(anyString()))
                .willReturn(true);

        given(userDAO.existsUserWithEmail(anyString()))
                .willReturn(false);

        // when
        underTest.register(request);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userDAO).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser, user);
    }

    @Test
    @Disabled
    void willThrowWhenEmailIsNotValid() {
        //TODO

        // given

        // when

        // then
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        UserRegistrationRequest request = new UserRegistrationRequest(
                "Dominik",
                "Kowal",
                "dkowal@gmail.com",
                "password",
                MALE,
                "Kraków",
                "12-02-2000"
        );
        given(emailValidator.test(anyString()))
                .willReturn(true);

        given(userDAO.existsUserWithEmail(anyString()))
                .willReturn(true);

        // when
        // then
        assertThatThrownBy(() -> underTest.register(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("email" + request.email() +" already taken");

        verify(userDAO, never()).save(any());
    }

    @Test
    @Disabled
    void shouldBuildValidConfirmationEmail() {

        //given
        String name = "Jacek";
        String link = "http://localhost:8080/auth/confirmToken?token=";
        String expectedEmail = "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";

        //when
        //underTest.buildEmail(name, link);
    }
}