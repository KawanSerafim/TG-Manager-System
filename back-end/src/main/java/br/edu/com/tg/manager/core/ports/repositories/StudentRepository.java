package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Student;
import java.util.Optional;

/**
 * Repositório de domínio.
 * Determina um contrato abstrato para a camada de infra-estrutura implementar
 * a persistência de dados da entidade de domínio Student.
 */
public interface StudentRepository {

    /**
     * Persiste o objeto de um aluno no banco de dados.
     * @param Student Aluno.
     */
    void save(Student student);

    /**
     * Busca um aluno pelo seu RA.
     * @param registration RA.
     * @return Optional vazio ou Student.
     */
    Optional<Student> findByRegistration(String registration);
}