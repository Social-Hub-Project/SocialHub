package com.application.socialhub.service;

import com.application.socialhub.dao.UserDAO;
import com.application.socialhub.dto.AuthenticationFailedResponse;
import com.application.socialhub.dto.RegistrationResponse;
import com.application.socialhub.dto.UserRegistrationRequest;
import com.application.socialhub.model.ConfirmationToken;
import com.application.socialhub.model.Role;
import com.application.socialhub.model.UserEntity;
import com.application.socialhub.model.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationService {
    private final ConfirmationTokenService confirmationTokenService;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    public RegistrationService(@Qualifier("jpa") UserDAO userDAO,
            ConfirmationTokenService confirmationTokenService,
            PasswordEncoder passwordEncoder,
            EmailSender emailSender) {
        this.confirmationTokenService = confirmationTokenService;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    public ResponseEntity<Object> register(UserRegistrationRequest request) {

        if (userDAO.existsUserWithEmail(request.email())) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return new ResponseEntity<>(new AuthenticationFailedResponse("Email is taken!"), HttpStatus.BAD_REQUEST);
        }
        if (!request.password().equals(request.passwordConfirmation())) {
            return new ResponseEntity<>(new AuthenticationFailedResponse("Passwords should be equal!"),
                    HttpStatus.BAD_REQUEST);
        }

        UserInfo userInfo = new UserInfo(
                request.name(),
                request.surname(),
                LocalDate.now(),
                request.residence(),
                false,
                "/exampleImages/userIcon.png",
                "/exampleImages/background.png",
                request.sex(),
                LocalDate.now());

        UserEntity userEntity = new UserEntity(
                Role.USER,
                request.email(),
                passwordEncoder.encode(request.password()),
                true,
                LocalDate.now(), true, userInfo);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userEntity);
        userEntity.setEnabled(true);
        userDAO.save(userEntity);

        // confirmationTokenService.saveConfirmationToken(confirmationToken);
        //
        // String link = "http://localhost:8080/auth/confirmToken?token=" + token;
        //
        // emailSender.send(request.email(), buildEmail(request.name(), link));
        return new ResponseEntity<>(new RegistrationResponse("User " + userInfo.getName() + " registered successfully"),
                HttpStatus.OK);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userDAO.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                +
                "    <tboy>d<tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
                +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
                +
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
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                +
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
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
                +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name
                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\""
                + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
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
    }
}
