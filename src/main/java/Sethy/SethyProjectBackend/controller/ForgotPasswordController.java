package Sethy.SethyProjectBackend.controller;


import Sethy.SethyProjectBackend.Utility.Utility;
import Sethy.SethyProjectBackend.exception.NotFoundException;
import Sethy.SethyProjectBackend.model.User;
import Sethy.SethyProjectBackend.model.dto.ForgotPasswordDto;
import Sethy.SethyProjectBackend.model.dto.MessageDto;
import Sethy.SethyProjectBackend.model.dto.PasswordResetTokenDto;
import Sethy.SethyProjectBackend.model.dto.ResetPasswordDto;
import Sethy.SethyProjectBackend.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin
public class ForgotPasswordController {
    private final JavaMailSender mailSender;
    private final UserService userService;

    @Autowired
    public ForgotPasswordController(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(HttpServletRequest request, @RequestBody ForgotPasswordDto forgotPasswordDto) {
        String token = RandomString.make(30);
        String userMail = forgotPasswordDto.getUserMail();

        try {
            userService.updateResetPasswordToken(token, userMail);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password/" + token;
            sendEmail(userMail, resetPasswordLink);
            return new ResponseEntity<>(new MessageDto("We have sent a reset password link to your email. Please check."), HttpStatus.OK);

        } catch (NotFoundException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (UnsupportedEncodingException | MessagingException e) {
            return new ResponseEntity<>(new MessageDto("Error while sending email"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("layermarkhakan@gmail.com", "Sethy App");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

    }

    @GetMapping("/reset_password/{token}")
    public ResponseEntity<?> showResetPasswordForm(@PathVariable("token") String token) {
        User user = userService.getByResetPasswordToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new PasswordResetTokenDto(token),HttpStatus.OK);
    }

    @PostMapping("/reset_password/{token}")
    public ResponseEntity<?> processResetPassword(@PathVariable("token") String token,@RequestBody ResetPasswordDto resetPasswordDto) {

        User user = userService.getByResetPasswordToken(token);
        String userPassword = resetPasswordDto.getUserPassword();
        if (user == null) {
            return new ResponseEntity<>(new MessageDto("Invalid Token"),HttpStatus.NOT_FOUND);
        } else {
            userService.updatePassword(user, userPassword);

            return new ResponseEntity<>(new MessageDto("You have successfully changed your password."),HttpStatus.NOT_FOUND);
        }
    }
}
