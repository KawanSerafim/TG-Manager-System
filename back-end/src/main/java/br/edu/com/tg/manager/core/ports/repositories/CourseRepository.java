package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.enums.CourseShift;

import java.util.Optional;

/**
 * Repositório de domínio.
 * Determina um contrato abstrato para a camada de infra-estrutura implementar
 * a persistência de dados da entidade de domínio Course.
 */
public interface CourseRepository {

    /**
     * Persiste o objeto de um curso no banco de dados.
     * @param course Curso
     */
    void save(Course course);

    /**
     * Busca um curso pelo seu nome.
     * @param name Nome.
     * @return Optional vazio ou Course.
     */
    Optional<Course> findByNameAndShift(String name, CourseShift shift);
}