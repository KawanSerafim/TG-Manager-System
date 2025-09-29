package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Student;
import java.util.Optional;

/**
 * Repositório de domínio:
 * Determina um contrato abstrato para a camada de infraestrutura implementar
 * a persistência de dados da entidade de domínio Student.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface StudentRepository {

    /**
     * Método de contrato de domínio:
     * Persiste o objeto de um aluno no banco de dados.
     * @param student Aluno.
     */
    void save(Student student);

    /**
     * Método de contrato de domínio:
     * Busca um aluno pelo seu RA.
     * @param registration RA do aluno.
     * @return Optional vazio ou Student.
     */
    Optional<Student> findByRegistration(String registration);
}