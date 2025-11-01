package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.LoginCase;
import br.edu.com.tg.manager.infrastructure.gateways.security.authentication.JwtTokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LoginService implements LoginCase {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public LoginService(
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenService jwtTokenService
    ) {
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
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

        String userPassword = null;

        if(optionalAdministrator.isPresent()) {
            var administrator = optionalAdministrator.get();
            userPassword = administrator.getPassword();
        }

        if(optionalProfessor.isPresent()) {
            var professor = optionalProfessor.get();
            userPassword = professor.getPassword();
        }

        if(optionalStudent.isPresent()) {
            var student = optionalStudent.get();
            userPassword = student.getPassword();
        }

        if(userPassword == null) {
            throw new DomainException(
                    "O campo do email ou da senha foram digitados "
                    + "incorretamente. Tente novamente!"
            );
        }

        validatePassword(userPassword, input.password());

        String token = jwtTokenService.generateToken(input.email());

        return new Output(token, "Bearer");
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