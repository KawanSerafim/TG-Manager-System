package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de fronteira:
 * Define um contrato abstrato para o Spring JPA persistir e manipular o
 * modelo de dados StudentModel. Por pertencer à infraestrutura da aplicação,
 * esta classe herda o JPARepository do Spring Boot.
 */
public interface SpringStudentRepository extends 
JpaRepository<StudentModel, Long> {

    /**
     * Método de contrato de fronteira:
     * Busca um aluno pelo seu RA.
     * @param registration RA do aluno.
     * @return Optional vazio ou StudentModel.
     */
    Optional<StudentModel> findByRegistration(String registration);

    /**
     * Método de contrato de fronteira:
     * Busca um aluno pelo email.
     * @param email Email do aluno.
     * @return Optional vazio ou StudentModel.
     */
    Optional<StudentModel> findByUserAccountEmail(String email);
}