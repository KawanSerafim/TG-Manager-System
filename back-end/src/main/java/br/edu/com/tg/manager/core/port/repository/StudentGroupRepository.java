package br.edu.com.tg.manager.core.port.repository;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import java.util.Optional;

/**
 * Porta de saída.
 * Define um contrato para a persistência de dados da 
 * entidade de domínio que representa a turma.
 */
public interface StudentGroupRepository {

    /**
     * Salva uma instância da turma no banco de dados.
     * @param course Objeto da turma.
     * @return O objeto da turma salvo.
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Busca uma turma pelo curso, ano e semestre.
     * @param course Objeto do curso.
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @return Um null ou o objeto da turma.
     */
    Optional<StudentGroup> findByCourseAndYearAndSemester(Course course, Integer year, Integer semester);
}