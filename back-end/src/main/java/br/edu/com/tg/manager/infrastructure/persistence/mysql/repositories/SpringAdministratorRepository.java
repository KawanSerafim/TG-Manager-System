package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.
        AdministratorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringAdministratorRepository extends
        JpaRepository<AdministratorModel, Long> {
    Optional<AdministratorModel> findByUserAccountEmail(String email);
}