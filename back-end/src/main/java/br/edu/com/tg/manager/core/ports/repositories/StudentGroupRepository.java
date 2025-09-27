package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.entities.Course;
import java.util.Optional;

/**
 * Repositório de domínio.
 * Determina um contrato abstrato para a camada de infra-estrutura implementar
 * a persistência de dados da entidade de domínio StudentGroup.
 */
public interface StudentGroupRepository {

    /**
     * Persiste o objeto de uma turma no banco de dados.
     * @param studentGroup StudentGroup.
     * @return StudentGroup.
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Busca uma turma pelo seu curso, seu ano e seu semestre.
     * @param course Curso.
     * @param year Ano.
     * @param semester Semestre.
     * @return Optional vazio ou StudentGroup.
     */
    Optional<StudentGroup> findByCourseAndYearAndSemester(
        
        Course course,
        Integer year,
        Integer semester
    );
}