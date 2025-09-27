package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * Repositório Spring JPA.
 * Determina um contrato abstrato para o Spring JPA persistir e manipular o 
 * modelo de dados CourseModel.
 */
public interface SpringCourseRepository extends 
JpaRepository<CourseModel, Long> {
    
    /**
     * Busca um curso pelo seu nome e seu turno.
     * @param name Nome.
     * @param shift Turno.
     * @return Optional vazio ou CourseModel.
     */
    Optional<CourseModel> findByNameAndShift(String name, CourseShift shift);
}