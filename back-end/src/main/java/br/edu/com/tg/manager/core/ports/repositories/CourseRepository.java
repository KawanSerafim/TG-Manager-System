package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import java.util.Optional;

/**
 * Repositório de domínio:
 * Define um contrato abstrato para a camada de infraestrutura implementar
 * a persistência de dados da entidade de domínio Course.
 */
public interface CourseRepository {

    /**
     * Método de contrato de domínio:
     * Persiste o objeto de um curso no banco de dados.
     * @param course Curso.
     */
    void save(Course course);

    /**
     * Método de contrato de domínio:
     * Busca um curso pelo seu nome e seu turno.
     * @param name Nome do curso.
     * @param shift Turno do curso.
     * @return Optional vazio ou Course.
     */
    Optional<Course> findByNameAndShift(String name, CourseShift shift);
}