package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .ProfessorModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de fronteira:
 * Define um contrato abstrato para o Spring JPA persistir e manipular o
 * modelo de dados ProfessorModel. Por pertencer à infraestrutura da aplicação,
 * esta classe herda o JPARepository do Spring Boot.
 */
public interface SpringProfessorRepository extends 
JpaRepository<ProfessorModel, Long> {

    /**
     * Método de contrato de fronteira:
     * Busca um professor pela sua matrícula.
     * @param registration Matrícula do professor.
     * @return Optional vazio ou ProfessorModel.
     */
    Optional<ProfessorModel> findByRegistration(String registration);
}