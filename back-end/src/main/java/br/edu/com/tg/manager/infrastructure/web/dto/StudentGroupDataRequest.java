package br.edu.com.tg.manager.infrastructure.web.dto;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.util.List;

/**
 * Porta-dados de infra estrutura:
 * Carregas os dados da turma que estão numa requisição HTTP.
 * @param courseName Nome do curso da turma.
 * @param discipline Disciplina da turma.
 * @param year Ana da turma.
 * @param semester Semestre da turma.
 * @param courseShift Turno da turma.
 * @param students Lista de alunos da turma.
 */
public record StudentGroupDataRequest(

        String courseName,
        Discipline discipline,
        Integer year,
        Integer semester,
        CourseShift courseShift,
        List<StudentDataRequest> students
) {
}
