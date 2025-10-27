package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.gateways.EmailSender;
import br.edu.com.tg.manager.core.ports.gateways.TokenCache;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.SendConfirmationEmailCase;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Service
public class SendConfirmationEmailService implements SendConfirmationEmailCase {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final AdministratorRepository administratorRepository;
    private final TokenCache tokenCache;
    private final EmailSender emailSender;

    public SendConfirmationEmailService(
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            AdministratorRepository administratorRepository,
            TokenCache tokenCache,
            EmailSender emailSender
    ) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.administratorRepository = administratorRepository;
        this.tokenCache = tokenCache;
        this.emailSender = emailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Input input) {
        UserAccount.validateEmailFormat(input.email());

        String token = generateToken();
        String subject = "Confirme seu Email.";
        String body = generateEmailBody(token);

        tokenCache.putToken(token, input.email());
        emailSender.sendEmail(input.email(), subject, body);
    }

    private String generateToken() {
        int length = 6;
        Random random = new SecureRandom();
        StringBuilder token = new StringBuilder(length);
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz0123456789";

        for(int i = 0; i < length; i++) {
            token.append(
                    CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
            );
        }
        return token.toString();
    }

    private String generateEmailBody(String token) {
        return
            "NÃO RESPONDA ESTE EMAIL!\n\n"
            + "Olá e seja bem-vindo!\n"
            + "Você está a um passo de concluir o seu cadastro, e para isso "
            + "é preciso inserir o token abaixo no formulário.\n\n"
            + "Token: " + token + "\n\n"
            + "Atenção: Este token de confirmação é válido por apenas "
            + "15 minutos. Se ele expirar, será preciso solicitar um novo "
            + "token.";
    }
}