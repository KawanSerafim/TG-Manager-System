package br.edu.com.tg.manager.core.port.repository;

import br.edu.com.tg.manager.core.entity.Course;
import java.util.Optional;

/**
 * Porta de saída.
 * Define um contrato para a persistência de dados da entidade de domínio
 * que representa o curso.
 */
public interface CourseRepository {

    /**
     * Salva uma instância do curso no banco de dados.
     * @param course Objeto do curso.
     * @return O objeto de curso salvo.
     */
    Course save(Course course);

    /**
     * Busca um curso pelo seu nome.
     * @param name Nome do curso.
     * @return Um null ou o objeto do curso.
     */
    Optional<Course> findByName(String name);
}