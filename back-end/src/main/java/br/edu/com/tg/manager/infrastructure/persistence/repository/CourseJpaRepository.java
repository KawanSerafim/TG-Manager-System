package br.edu.com.tg.manager.infrastructure.persistence.repository;

import br.edu.com.tg.manager.infrastructure.persistence.model.CourseData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseData, Long> {

    Optional<CourseData> findByName(String name);
}