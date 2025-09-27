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
 * Implementação de repositório.
 * Implementa o repositório de domínio, executando os métodos do contrato
 * com o repositório Spring JPA.
 */
@Repository
public class ProfessorRepositoryImpl implements ProfessorRepository {
    
    private final SpringProfessorRepository springRepository;
    private final ProfessorMapper professorMapper;

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

        var professorModel = professorMapper.toModel(professor);
        springRepository.save(professorModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Professor> findByRegistration(String registration) {

        Optional<ProfessorModel> optionalProfessorModel = springRepository
        .findByRegistration(registration);

        return optionalProfessorModel.map(professorMapper::toDomain);
    }
}