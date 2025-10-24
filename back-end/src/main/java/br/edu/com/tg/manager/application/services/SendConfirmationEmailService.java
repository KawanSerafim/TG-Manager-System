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

/**
 * Implementador de caso de uso:
 * Implementa o funcionamento e a lógica do caso de uso de enviar um email de
 * confirmação. Por pertencer à infraestrutura da aplicação, esta classe utiliza
 * da anotação Service do SpringBoot, que é uma especialização da anotação
 * Component, permitindo que o framework gerencie a classe.
 */
@Service
public class SendConfirmationEmailService implements SendConfirmationEmailCase {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final AdministratorRepository administratorRepository;
    private final TokenCache tokenCache;
    private final EmailSender emailSender;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência dos
     * repositórios de domínio e portões de saída e injeta as dependências que,
     * quando SendConfirmationEmailCase é instanciado por outra classe, a
     * implementação da interface é assumida por esta classe aqui.
     * @param professorRepository Repositório de domínio do professor.
     * @param studentRepository Repositório de domínio do aluno.
     * @param administratorRepository Repositório de domínio do administrador.
     * @param tokenCache Portão de saída do Token temporário.
     * @param emailSender Portão de saída do envio de email.
     */
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

        // Valida a unicidade do email.
        validateUniqueness(input);

        // Gera um token alfanumérico aleatório.
        String token = generateToken();

        // Construção do email.
        String subject = "Confirme seu Email (Não responda este email).";
        String body = generateEmailBody(token);

        // Armazena o token na cache do usuário.
        tokenCache.putToken(input.email(), token);

        // Envio do email.
        emailSender.sendEmail(input.email(), subject, body);
    }

    /**
     * Método de aplicação:
     * Verifica se já há alguém com esse email cadastrado no sistema.
     * @param input Input da requisição.
     */
    private void validateUniqueness(Input input) {

        // Busca no banco de dados alguém com o email informado.
        Optional<Professor> optionalProfessor = professorRepository
            .findByEmail(input.email());

        Optional<Student> optionalStudent = studentRepository
            .findByEmail(input.email());

        Optional<Administrator> optionalAdministrator = administratorRepository
            .findByEmail(input.email());

        // Se encontrar, lança uma exceção de domínio.
        if(

            optionalProfessor.isPresent() ||
            optionalStudent.isPresent() ||
            optionalAdministrator.isPresent()
        ) {

            throw new DomainException(

                "Esse email já foi cadastrado no sistema."
            );
        }
    }

    /**
     * Método de aplicação:
     * Gera um token alfanumérico com valores aleatórios.
     * @return String com o token.
     */
    private String generateToken() {

        int length = 6;
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                            "abcdefghijklmnopqrstuvwxyz" +
                            "0123456789";
        Random random = new SecureRandom();
        StringBuilder token = new StringBuilder(length);

        for(int i = 0; i < length; i++) {

            token.append(

                CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
            );
        }

        return token.toString();
    }

    /**
     * Método de aplicação:
     * Construi um texto para o corpo do email de envio.
     * @param token Token alfanumérico.
     * @return String com o corpo do email de envio.
     */
    private String generateEmailBody(String token) {

        return
            "Olá e seja bem-vindo!\n\n" +
            "Você está a um passo de concluir o seu cadastro, e para isso " +
            "é preciso inserir o token abaixo no formulário.\n\n" +
            "Token: " + token + "\n\n" +
            "Atenção: Este token de confirmação é válido por apenas " +
            "15 minutos. Se ele expirar, será preciso solicitar um novo token.";
    }
}