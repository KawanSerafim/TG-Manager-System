package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;

import java.util.Optional;

/**
 * Repositório de núcleo:
 * Define um contrato abstrato para a camada de infraestrutura
 * implementar a persistência de dados da entidade de domínio StudentGroup.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface StudentGroupRepository {
    /**
     * Método de contrato de núcleo:
     * Persiste o objeto de uma turma no banco de dados.
     * @param studentGroup Turma.
     * @return StudentGroup.
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Método de contrato de núcleo:
     * Busca uma turma pelo seu curso, ano e semestre.
     * @param course Curso da turma.
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @param courseShift Turno do curso da turma.
     * @param discipline Disciplina de TG da turma.
     * @return Optional vazio ou StudentGroup.
     */
    Optional<StudentGroup> findByCourseAndYearAndSemesterAndCourseShiftAndDiscipline(
            Course course,
            Integer year,
            Integer semester,
            CourseShift courseShift,
            Discipline discipline
    );
}