package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.gateways.TokenCache;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.ValidateTokenCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ValidateTokenService implements ValidateTokenCase {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final TokenCache tokenCache;

    public ValidateTokenService(
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            TokenCache tokenCache
    ) {
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.tokenCache = tokenCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void execute(Input input) {
        String email = tokenCache.getEmailByToken(input.token())
                .orElseThrow(() -> new DomainException(
                        "Token inválido ou expirado. Tente novamente!"
                ));

        Optional<Administrator> optionalAdministrator = administratorRepository
                .findByEmail(email);
        Optional<Professor> optionalProfessor =  professorRepository
                .findByEmail(email);
        Optional<Student> optionalStudent = studentRepository
                .findByEmail(email);

        if(optionalAdministrator.isEmpty()
                && optionalProfessor.isEmpty()
                && optionalStudent.isEmpty()
        ) {
            throw new DomainException(
                    "O email = " + email + " não foi encontrado."
            );
        }

        optionalAdministrator.ifPresent(this::updateAdministrator);
        optionalProfessor.ifPresent(this::updateProfessor);
        optionalStudent.ifPresent(this::updateStudent);

        tokenCache.removeToken(input.token());
    }

    private void updateAdministrator(Administrator administrator) {
        var userAccount = administrator.getUserAccount();
        validateUserAccountStatus(userAccount.getStatus());
        userAccount.setStatus(UserAccountStatus.EMAIL_CONFIRMED);
        administrator.setUserAccount(userAccount);

        administratorRepository.save(administrator);
    }

    private void updateProfessor(Professor professor) {
        var userAccount = professor.getUserAccount();
        validateUserAccountStatus(userAccount.getStatus());
        userAccount.setStatus(UserAccountStatus.EMAIL_CONFIRMED);
        professor.setUserAccount(userAccount);

        professorRepository.save(professor);
    }

    private void updateStudent(Student student) {
        var userAccount = student.getUserAccount();
        validateUserAccountStatus(userAccount.getStatus());
        userAccount.setStatus(UserAccountStatus.EMAIL_CONFIRMED);
        student.completeRegistration(userAccount);

        studentRepository.save(student);
    }

    private void validateUserAccountStatus(UserAccountStatus status) {
        if(status != UserAccountStatus.PENDING_VERIFICATION) {
            throw new DomainException(
                    "A conta de usuário não tem o estado válido para a ação."
            );
        }
    }
}