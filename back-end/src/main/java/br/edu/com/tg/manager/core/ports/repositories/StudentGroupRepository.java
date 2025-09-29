package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.entities.Course;
import java.util.Optional;

/**
 * Repositório de domínio:
 * Determina um contrato abstrato para a camada de infraestrutura implementar
 * a persistência de dados da entidade de domínio StudentGroup.
 */
public interface StudentGroupRepository {

    /**
     * Método de contrato de domínio:
     * Persiste o objeto de uma turma no banco de dados.
     * @param studentGroup StudentGroup.
     * @return StudentGroup.
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Método de contrato de domínio:
     * Busca uma turma pelo seu curso, seu ano e seu semestre.
     * @param course Curso da turma.
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @return Optional vazio ou StudentGroup.
     */
    Optional<StudentGroup> findByCourseAndYearAndSemester(
        
        Course course,
        Integer year,
        Integer semester
    );
}