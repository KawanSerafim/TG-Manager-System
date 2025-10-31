package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .CourseModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentGroupModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringStudentGroupRepository extends
        JpaRepository<StudentGroupModel, Long> {
    Optional<StudentGroupModel> findByCourseAndYearAndSemesterAndCourseShiftAndDiscipline(
            CourseModel course,
            Integer year,
            Integer semester,
            CourseShift courseShift,
            Discipline discipline
    );
}