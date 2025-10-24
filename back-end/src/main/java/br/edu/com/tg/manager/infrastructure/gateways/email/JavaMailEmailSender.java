package br.edu.com.tg.manager.infrastructure.gateways.email;

import br.edu.com.tg.manager.core.ports.gateways.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Implementador de portão de acesso:
 * Implementa o funcionamento do envio de email.
 * Por pertencer à infraestrutura da aplicação, esta classe utiliza da anotação
 * Component do SpringBoot, permitindo que o framework gerencie a classe e a
 * abstração JavaMailSender.
 */
@Component
public class JavaMailEmailSender implements EmailSender {

    private final Logger log = LoggerFactory
        .getLogger(JavaMailEmailSender.class);
    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    /**
     * Construtor de injeção de dependência:
     * Recebe o JavaMailSender configurado pelo SpringBoot.
     * @param mailSender JavaMailSender.
     */
    public JavaMailEmailSender(JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String to, String subject, String body) {

        try {

            // Cria a mensagem de email simples.
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            // Envia o email.
            mailSender.send(message);
        } catch (MailException e) {

            log.error("Erro ao enviar e-mail para {}:", to, e);
        }
    }
}