package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.CreateProfessorCase;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementador de caso de uso:
 * Implementa o funcionamento e a lógica do caso de uso de criar um professor e
 * o persistir no banco de dados. Por pertencer à infraestrutura da aplicação,
 * esta classe utiliza da anotação Service do SpringBoot, que é uma
 * especialização da anotação Component, permitindo que o framework gerencie
 * a classe.
 */
@Service
public class CreateProfessorService implements CreateProfessorCase {

    private final ProfessorRepository professorRepository;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência dos
     * repositórios de domínio e injeta a dependência que, quando
     * CreateProfessorCase é instanciado por outra classe, a implementação da
     * interface é assumida por esta classe aqui.
     * @param professorRepository Repositório de domínio do professor.
     */
    public CreateProfessorService(ProfessorRepository professorRepository) {

        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Output execute(Input input) {

        var professor = getProfessor(input);
        var professorSaved = professorRepository.save(professor);

        return new CreateProfessorCase.Output(

            professorSaved.getId(),
            professorSaved.getName(),
            professorSaved.getRegistration(),
            professorSaved.getEmail(),
            professorSaved.getRole()
        );
    }

    /**
     * Método de aplicação:
     * Tenta encontrar um professor com a matrícula fornecida, e se não achar,
     * insere num objeto Professor.
     * @param input Porta-dados da requisição.
     * @return Professor.
     */
    private Professor getProfessor(Input input) {

        /*
         * Insere num Optional, o professor que será buscado pelo repositório
         * usando a sua matrícula.
         */
        Optional<Professor> optionalProfessor = professorRepository
            .findByRegistration(input.registration());

        if(optionalProfessor.isPresent()) {

            throw new DomainException(

                "A matrícula já foi cadastrada no sistema."
            );
        }

        /*
         * Insere num Optional, o professor que será buscado pelo repositório
         * usando o seu email.
         */
        optionalProfessor = professorRepository.findByEmail(input.email());

        if(optionalProfessor.isPresent()){

            throw new DomainException(

                "O email já foi cadastrado no sistema."
            );
        }

        /*
         * Se não, um novo professor é criado utilizando os valores fornecidos
         * pela requisição, e o salva no banco de dados.
         */
        var userAccount = new UserAccount(input.email(), input.password());

        return new Professor(

            input.name(),
            input.registration(),
            userAccount,
            input.role()
        );
    }
}