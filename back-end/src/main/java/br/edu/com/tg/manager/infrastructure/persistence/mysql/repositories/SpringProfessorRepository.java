package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .ProfessorModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringProfessorRepository extends
        JpaRepository<ProfessorModel, Long> {
    Optional<ProfessorModel> findByRegistration(String registration);

    Optional<ProfessorModel> findByUserAccountEmail(String email);

    List<ProfessorModel> findAllByRole(ProfessorRole role);
}