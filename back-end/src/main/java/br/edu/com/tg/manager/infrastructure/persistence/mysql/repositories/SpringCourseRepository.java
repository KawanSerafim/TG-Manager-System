package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositório de fronteira:
 * Define um contrato abstrato para o Spring JPA persistir e manipular o
 * modelo de dados CourseModel. Por pertencer à infraestrutura da aplicação,
 * esta classe herda o JPARepository do Spring Boot.
 */
public interface SpringCourseRepository extends 
JpaRepository<CourseModel, Long> {
    
    /**
     * Método de contrato de fronteira:
     * Busca um curso pelo seu nome e o seu turno.
     * @param name Nome do curso.
     * @param shift Turno do curso.
     * @return Optional vazio ou CourseModel.
     */
    Optional<CourseModel> findByNameAndShift(String name, CourseShift shift);
}