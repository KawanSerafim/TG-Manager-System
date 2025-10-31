package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.LoginCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService implements LoginCase {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Output execute(Input input) {
        if(input.email() == null || (input.email()).trim().isEmpty()) {
            throw new DomainException(
                    "O campo email é obrigatório."
            );
        }

        if(input.password() == null || (input.password()).trim().isEmpty()) {
            throw new DomainException(
                    "O campo senha é obrigatório."
            );
        }

        var optionalAdministrator = getOptionalAdministrator(input.email());
        var optionalProfessor = getOptionalProfessor(input.email());
        var optionalStudent = getOptionalStudent(input.email());

        Output output = null;
        String userPassword = null;

        if(optionalAdministrator.isPresent()) {
            var administrator = optionalAdministrator.get();
            var userAccount = administrator.getUserAccount();

            output = new Output(
                    administrator.getName(),
                    administrator.getEmail(),
                    userAccount.getStatus()
            );
            userPassword = administrator.getPassword();
        }

        if(optionalProfessor.isPresent()) {
            var professor = optionalProfessor.get();
            var userAccount = professor.getUserAccount();

            output = new Output(
                    professor.getName(),
                    professor.getEmail(),
                    userAccount.getStatus()
            );
            userPassword = professor.getPassword();
        }

        if(optionalStudent.isPresent()) {
            var student = optionalStudent.get();
            var userAccount = student.getUserAccount();

            output = new Output(
                    student.getName(),
                    student.getEmail(),
                    userAccount.getStatus()
            );
            userPassword = student.getPassword();
        }

        if(userPassword == null) {
            throw new DomainException(
                    "O campo do email ou da senha foram digitados "
                    + "incorretamente. Tente novamente!"
            );
        }

        validatePassword(userPassword, input.password());
        return output;
    }

    private void validatePassword(
            String userPassword,
            String requestPassword
    ) {
        boolean passwordsMatch = passwordEncoder.matches(
                requestPassword,
                userPassword
        );

        if(!passwordsMatch) {
            throw new DomainException(
                    "O campo do email ou da senha foram digitados "
                    + "incorretamente. Tente novamente!"
            );
        }
    }

    private Optional<Administrator> getOptionalAdministrator(String email) {
        return administratorRepository.findByEmail(email);
    }

    private Optional<Professor> getOptionalProfessor(String email) {
        return professorRepository.findByEmail(email);
    }

    private Optional<Student> getOptionalStudent(String email) {
        return studentRepository.findByEmail(email);
    }
}