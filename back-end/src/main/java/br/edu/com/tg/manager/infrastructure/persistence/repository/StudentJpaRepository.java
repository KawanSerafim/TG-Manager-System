package br.edu.com.tg.manager.infrastructure.persistence.repository;

import br.edu.com.tg.manager.infrastructure.persistence.model.StudentData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentJpaRepository extends JpaRepository<StudentData, Long> {

    Optional<StudentData> findByRegistration(String registration);
}