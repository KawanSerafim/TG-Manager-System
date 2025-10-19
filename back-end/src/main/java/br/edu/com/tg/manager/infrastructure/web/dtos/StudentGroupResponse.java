package br.edu.com.tg.manager.infrastructure.web.dtos;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.util.List;

/**
 * Porta-dados de infra estrutura:
 * Carrega os dados que foram usados para o Coordenador de TG criar a turma.
 */
public record StudentGroupResponse(

   String courseName,
   CourseShift shift,
   Discipline discipline,
   Integer year,
   Integer semester,
   List<StudentResponse> students
) {}