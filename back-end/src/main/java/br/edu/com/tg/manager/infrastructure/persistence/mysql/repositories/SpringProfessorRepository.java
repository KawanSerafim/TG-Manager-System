package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.ProfessorModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório Spring JPA.
 * Determina um contrato abstrato para o Spring JPA persistir e manipular o 
 * modelo de dados ProfessorModel.
 */
public interface SpringProfessorRepository extends 
JpaRepository<ProfessorModel, Long> {

    /**
     * Busca um professor pela sua matrícula.
     * @param registration Matrícula.
     * @return Optional vazio ou ProfessorModel.
     */
    Optional<ProfessorModel> findByRegistration(String registration);
}