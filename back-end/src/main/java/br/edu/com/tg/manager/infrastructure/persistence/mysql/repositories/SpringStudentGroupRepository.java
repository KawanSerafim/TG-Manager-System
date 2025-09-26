package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.CourseModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.StudentGroupModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório Spring JPA.
 * Determina um contrato abstrato para o Spring JPA persistir e manipular o 
 * modelo de dados StudentGroupModel.
 */
public interface SpringStudentGroupRepository extends
JpaRepository<StudentGroupModel, Long> {

    /**
     * Busca uma turma pelo seu curso, seu ano e seu semestre.
     * @param course Modelo de dados da entidade de domínio Course.
     * @param year Ano.
     * @param semester Semestre.
     * @return Optional vazio ou StudentGroupModel.
     */
    Optional<StudentGroupModel> findByCourseAndYearAndSemester(
        
        CourseModel course,
        Integer year,
        Integer semester
    );
}