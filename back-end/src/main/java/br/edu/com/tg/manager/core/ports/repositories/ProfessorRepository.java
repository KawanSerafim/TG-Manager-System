package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import java.util.Optional;

/**
 * Repositório de domínio.
 * Determina um contrato abstrato para a camada de infra-estrutura implementar
 * a persistência de dados da entidade de domínio Professor.
 */
public interface ProfessorRepository {

    /**
     * Persiste o objeto de um professor no banco de dados.
     * @param professor Professor.
     */
    void save(Professor professor);

    /**
     * Busca um professor pela sua matrícula.
     * @param registration Matrícula.
     * @return Optional vazio ou Professor.
     */
    Optional<Professor> findByRegistration(String registration);
}