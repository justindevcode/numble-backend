package com.community.numble.system.email.service.service;


import com.community.numble.common.Constants;
import com.community.numble.common.utils.DateUtils;
import com.community.numble.system.email.domain.Email;
import com.community.numble.system.email.repository.EmailRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    private final EmailRepository emailRepository;

    @Transactional
    public void sendEmailToken(Email toEmail) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        boolean tokenVerified = emailRepository.existsByToEmailAndTokenVerified(toEmail.getToEmail(), toEmail.getTokenVerified());

        if(tokenVerified) {
            toEmail.setTokenVerified("N");
            emailRepository.save(toEmail);
        }

        String token = UUID.randomUUID().toString().substring(0, 5);
        String fromEmail = "dmstjd1024@naver.com";

        Email sendEmail = Email.builder()
            .toEmail(toEmail.getToEmail())
            .subject("[와글와글] 이메일 인증 안내입니다.")
            .content(sendTokenTemplate(token, fromEmail))
            .fromEmail(fromEmail)
            .sendDate(DateUtils.format(LocalDateTime.now(), Constants.DATE_FORMAT_YMDHMS))
            .token(token)
            .tokenVerified("N")
            .build();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, false, "UTF-8");
        mimeMessageHelper.setTo(sendEmail.getToEmail());
        mimeMessageHelper.setSubject(sendEmail.getSubject());
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setText(sendEmail.getContent(), true);
        javaMailSender.send(message);
        log.info("send email {}", sendEmail.getContent());

        emailRepository.save(sendEmail);

    }

    public String sendTokenTemplate(String token, String fromEmail){
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("host", fromEmail);
        return templateEngine.process("mail/token", context);
    }

    public boolean checkEmailToken(Email checkEmail) {

        Email emailToken = emailRepository.findByToEmailAndTokenVerified(checkEmail.getToEmail(), checkEmail.getTokenVerified());

        return StringUtils.equals(emailToken.getToken(), checkEmail.getToken());
    }
}
