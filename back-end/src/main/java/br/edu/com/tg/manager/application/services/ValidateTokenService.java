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
    public void execute(Input input) {

        // Verifica se há um email associado ao token.
        String email = tokenCache.getEmailByToken(input.token())
            .orElseThrow(() -> new DomainException(

                // Se não houver, é porque o token é inválido ou expirou.
                "Token inválido ou expirado. Tente novamente!"
            ));

        // Tenta achar algum usuário com o email fornecido.
        Optional<Administrator> optionalAdministrator = administratorRepository.
            findByEmail(email);
        Optional<Professor> optionalProfessor =  professorRepository.
            findByEmail(email);
        Optional<Student> optionalStudent = studentRepository.
            findByEmail(email);

        // Se não achar, lança exceção de domínio.
        if(

            optionalAdministrator.isEmpty() &&
            optionalProfessor.isEmpty() &&
            optionalStudent.isEmpty()
        ) {

            throw new DomainException(

                "O email = " + email + " não foi encontrado."
            );
        }

        // Se achar, envia a função de atualizar administrador.
        optionalAdministrator.ifPresent(this::updateAdministrator);

        // Se achar, envia a função de atualizar professor.
        optionalProfessor.ifPresent(this::updateProfessor);

        // Se achar, envia a função de atualizar aluno.
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
        student.setUserAccount(userAccount);

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