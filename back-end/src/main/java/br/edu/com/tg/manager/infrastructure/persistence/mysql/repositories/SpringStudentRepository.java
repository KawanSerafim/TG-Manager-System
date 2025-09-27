package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories;

import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.StudentModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório Spring JPA.
 * Determina um contrato abstrato para o Spring JPA persistir e manipular o 
 * modelo de dados StudentModel.
 */
public interface SpringStudentRepository extends 
JpaRepository<StudentModel, Long> {

    /**
     * Busca um aluno pelo seu RA.
     * @param registration RA.
     * @return Optional vazio ou StudentModel.
     */
    Optional<StudentModel> findByRegistration(String registration);
}