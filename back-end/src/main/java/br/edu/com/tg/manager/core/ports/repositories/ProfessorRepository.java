package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import java.util.Optional;

/**
 * Repositório de núcleo:
 * Define um contrato abstrato para a camada de infraestrutura
 * implementar a persistência de dados da entidade de domínio Professor.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface ProfessorRepository {
    /**
     * Método de contrato de núcleo:
     * Persiste o objeto de um professor no banco de dados.
     * @param professor Professor.
     * @return Professor.
     */
    Professor save(Professor professor);

    /**
     * Método de contrato de núcleo:
     * Busca um professor pela sua matrícula.
     * @param registration Matrícula do professor.
     * @return Optional vazio ou Professor.
     */
    Optional<Professor> findByRegistration(String registration);

    /**
     * Método de contrato de núcleo:
     * Busca um professor pelo seu email.
     * @param email Email do professor.
     * @return Optional vazio ou Professor.
     */
    Optional<Professor> findByEmail(String email);
}