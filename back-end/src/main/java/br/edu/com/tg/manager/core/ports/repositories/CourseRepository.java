package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Course;
import java.util.Optional;

/**
 * Repositório de núcleo:
 * Define um contrato abstrato para a camada de infraestrutura
 * implementar a persistência de dados da entidade de domínio Course.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface CourseRepository {
    /**
     * Método de contrato de núcleo:
     * Persiste o objeto de um curso no banco de dados.
     * @param course Curso.
     * @return Course.
     */
    Course save(Course course);

    /**
     * Método de contrato de núcleo:
     * Busca um curso pelo seu nome e seu turno.
     * @param name Nome do curso.
     * @return Optional vazio ou Course.
     */
    Optional<Course> findByName(String name);
}