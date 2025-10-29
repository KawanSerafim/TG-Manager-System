package br.edu.com.tg.manager.infrastructure.gateways.email;

import br.edu.com.tg.manager.core.ports.gateways.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JavaMailEmailSender implements EmailSender {
    private final JavaMailSender mailSender;
    private final Logger log = LoggerFactory
            .getLogger(JavaMailEmailSender.class);

    @Value("${spring.mail.from}")
    private String fromEmail;

    public JavaMailEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}