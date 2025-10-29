package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.application.events.UserRequiresConfirmationEvent;
import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CompleteStudentRegistrationCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CompleteStudentRegistrationService
        implements CompleteStudentRegistrationCase {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    public CompleteStudentRegistrationService(
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
    public void execute(Input input) {
        validateEmailUniqueness(input.email());
        var student = getStudent(input);

        String rawPassword = input.password();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        var userAccount = new UserAccount(input.email(), hashedPassword);
        student.setUserAccount(userAccount);

        studentRepository.save(student);

        eventPublisher.publishEvent(
                new UserRequiresConfirmationEvent(student.getEmail())
        );
    }

    private void validateEmailUniqueness(String email) {
        Optional<Professor> optionalProfessor = professorRepository
                .findByEmail(email);

        Optional<Student> optionalStudent = studentRepository
                .findByEmail(email);

        Optional<Administrator> optionalAdministrator = administratorRepository
                .findByEmail(email);

        if(optionalProfessor.isPresent()
                || optionalStudent.isPresent()
                || optionalAdministrator.isPresent()
        ) {
            throw new DomainException(
                    "Esse email já foi cadastrado no sistema."
            );
        }
    }

    private Student getStudent(Input input) {
        Optional<Student> optionalStudent = studentRepository
                .findByRegistration(input.registration());

        if(optionalStudent.isEmpty()) {
            throw new DomainException(
                    "O aluno com matrícula = " + input.registration()
                    + " não foi encontrado."
            );
        }

        var student = optionalStudent.get();

        if(student.getStatus() != StudentStatus.PRE_REGISTRATION) {
            throw new DomainException(
                    "O aluno não tem o estado válido para a ação."
            );
        }

        return student;
    }
}
