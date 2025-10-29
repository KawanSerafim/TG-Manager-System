package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringStudentRepository extends
        JpaRepository<StudentModel, Long> {
    Optional<StudentModel> findByRegistration(String registration);

    Optional<StudentModel> findByUserAccountEmail(String email);
}