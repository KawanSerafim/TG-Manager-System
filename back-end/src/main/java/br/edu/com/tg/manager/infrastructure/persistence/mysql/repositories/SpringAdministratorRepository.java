package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.
        AdministratorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório de fronteira:
 * Define um contrato abstrato para o Spring JPA persistir e manipular o
 * modelo de dados CourseModel. Por pertencer à infraestrutura da aplicação,
 * esta classe herda o JPARepository do Spring Boot.
 */
public interface SpringAdministratorRepository extends
JpaRepository<AdministratorModel, Long> {

    /**
     * Método de contrato de fronteira:
     * Busca um administrador pelo seu email.
     * @param email Email do administrador.
     * @return Optional vazio ou AdministratorModel.
     */
    Optional<AdministratorModel> findByUserAccountEmail(String email);
}