package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringCourseRepository extends
        JpaRepository<CourseModel, Long> {
    Optional<CourseModel> findByNameAndShift(String name, CourseShift shift);
}