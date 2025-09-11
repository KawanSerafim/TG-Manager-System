package br.edu.com.tg.manager.infrastructure.persistence.repository;

import br.edu.com.tg.manager.infrastructure.persistence.model.CourseData;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentGroupData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupJpaRepository extends JpaRepository<StudentGroupData, Long>{

    Optional<StudentGroupData> findByCourseAndYearAndSemester(CourseData course, Integer year, Integer semester);
}