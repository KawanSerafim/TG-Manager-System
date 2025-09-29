package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import java.util.Optional;

/**
 * Repositório de domínio:
 * Define um contrato abstrato para a camada de infraestrutura implementar
 * a persistência de dados da entidade de domínio Professor.
 */
public interface ProfessorRepository {

    /**
     * Método de contrato de domínio:
     * Persiste o objeto de um professor no banco de dados.
     * @param professor Professor.
     */
    void save(Professor professor);

    /**
     * Método de contrato de domínio:
     * Busca um professor pela sua matrícula.
     * @param registration Matrícula do professor.
     * @return Optional vazio ou Professor.
     */
    Optional<Professor> findByRegistration(String registration);
}