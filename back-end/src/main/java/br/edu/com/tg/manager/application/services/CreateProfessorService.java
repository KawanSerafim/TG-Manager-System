package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.application.events.UserRequiresConfirmationEvent;
import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateProfessorCase;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CreateProfessorService implements CreateProfessorCase {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    public CreateProfessorService(
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            ApplicationEventPublisher eventPublisher,
            PasswordEncoder passwordEncoder
    ) {
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Output execute(Input input) {
        validateEmailUniqueness(input);
        var professor = getProfessor(input);
        var professorSaved = professorRepository.save(professor);

        eventPublisher.publishEvent(
                new UserRequiresConfirmationEvent(professorSaved.getEmail())
        );

        return new CreateProfessorCase.Output(
                professorSaved.getId(),
                professorSaved.getName(),
                professorSaved.getRegistration(),
                professorSaved.getEmail(),
                professorSaved.getRole()
        );
    }

    private void validateEmailUniqueness(CreateProfessorCase.Input input) {
        Optional<Professor> optionalProfessor = professorRepository
                .findByEmail(input.email());

        Optional<Student> optionalStudent = studentRepository
                .findByEmail(input.email());

        Optional<Administrator> optionalAdministrator = administratorRepository
                .findByEmail(input.email());

        if(optionalProfessor.isPresent()
                || optionalStudent.isPresent()
                || optionalAdministrator.isPresent()
        ) {
            throw new DomainException(
                    "Esse email já foi cadastrado no sistema."
            );
        }
    }

    private Professor getProfessor(Input input) {
        Optional<Professor> optionalProfessor = professorRepository
                .findByRegistration(input.registration());

        if(optionalProfessor.isPresent()) {
            throw new DomainException(
                    "A matrícula já foi cadastrada no sistema."
            );
        }

        String rawPassword = input.password();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        var userAccount = new UserAccount(input.email(), hashedPassword);

        return new Professor(
                input.name(),
                input.registration(),
                userAccount,
                input.role()
        );
    }
}