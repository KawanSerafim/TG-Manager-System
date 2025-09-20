package br.edu.com.tg.manager.infrastructure.persistence.repository;

import br.edu.com.tg.manager.infrastructure.persistence.model.StudentData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório Spring Boot Data JPA para entidade de domínio do 
 * aluno. O framework reconhece os nomes dos métodos e os executa em tempo de 
 * execução, interagindo diretamente com o banco de dados.
 * 
 * Anotação @Repository: permite que o framework reconheça essa interface como 
 * um contrato para realizar as querys do banco de dados.
 */
@Repository
public interface StudentJpaRepository extends JpaRepository<StudentData, Long> {

    /**
     * Método responsável por realizar a busca de um objeto do aluno através da 
     * sua matrícula (RA). A escrita 'findByRegistration' é proposital, pois o 
     * hibernate reconhece o tipo da operação justamente através da nomeação do 
     * método.
     * @param registration Variável da matrícula (RA) do aluno.
     * @return Modelo de dados do objeto do aluno.
     */
    Optional<StudentData> findByRegistration(String registration);
}