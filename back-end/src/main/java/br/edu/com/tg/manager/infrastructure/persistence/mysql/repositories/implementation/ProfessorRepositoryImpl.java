package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .implementation;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
        .ProfessorMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .ProfessorModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .SpringProfessorRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementador de repositório:
 * Implementa o repositório de domínio da entidade Professor, executando os
 * métodos do contrato com o repositório Spring JPA. Por pertencer à
 * infraestrutura da aplicação, esta classe utiliza da anotação Repository do
 * Spring Boot, permitindo que o framework manipule o banco de dados com os
 * métodos do contrato.
 */
@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {
    
    private final SpringProfessorRepository springRepository;
    private final ProfessorMapper professorMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta, através do SpringBoot, a dependência que, quando
     * ProfessorRepository é instanciado por outra classe, a implementação da
     * interface é assumida por esta classe aqui.
     * @param springRepository Repositório Spring JPA do professor.
     * @param professorMapper Mapeador do professor.
     */
    public ProfessorRepositoryImpl(

        SpringProfessorRepository springRepository,
        ProfessorMapper professorMapper
    ) {

        this.springRepository = springRepository;
        this.professorMapper = professorMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Professor professor) {

        /*
         * Converte a entidade de domínio para um modelo de dados, para poder
         * persistir no banco de dados.
         */
        var professorModel = professorMapper.toModel(professor);
        springRepository.save(professorModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Professor> findByRegistration(String registration) {

        // Tenta encontrar um professor com a matrícula fornecida.
        Optional<ProfessorModel> optionalProfessorModel = springRepository
        .findByRegistration(registration);

        /*
         * Se achar o professor, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalProfessorModel.map(professorMapper::toDomain);
    }
}