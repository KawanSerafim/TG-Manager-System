package br.edu.com.tg.manager.infrastructure.persistence.repository;

import br.edu.com.tg.manager.infrastructure.persistence.model.CourseData;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentGroupData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório Spring Boot Data JPA para entidade
 * de domínio da turma. O framework reconhece os nomes dos
 * métodos e os executa em tempo de execução, interagindo
 * diretamente com o banco de dados.
 * 
 * Anotação @Repository: permite que o framework reconheça
 * essa interface como um contrato para realizar as querys
 * do banco de dados.
 */
@Repository
public interface StudentGroupJpaRepository extends JpaRepository<StudentGroupData, Long>{

    /**
     * Método responsável por realizar a busca de um objeto da turma
     * através de seu curso, ano e semestre.
     * A escrita 'findByCourseAndYearAndSemester' é proposital, pois 
     * o hibernate reconhece o tipo da operação justamente através
     * da nomeação do método.
     * @param course Objeto do curso.
     * @param year Variável do ano da turma.
     * @param semester Variável do semestre da turma.
     * @return Modelo de dados do objeto da turma.
     */
    Optional<StudentGroupData> findByCourseAndYearAndSemester(CourseData course, Integer year, Integer semester);
}