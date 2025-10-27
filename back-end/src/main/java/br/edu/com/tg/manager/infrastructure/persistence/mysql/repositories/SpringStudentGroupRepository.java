package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .CourseModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentGroupModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringStudentGroupRepository extends
        JpaRepository<StudentGroupModel, Long> {
    Optional<StudentGroupModel> findByCourseAndYearAndSemester(
            CourseModel course,
            Integer year,
            Integer semester
    );
}