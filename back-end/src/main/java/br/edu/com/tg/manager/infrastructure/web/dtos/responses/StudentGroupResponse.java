package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;

import java.util.List;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados usados para o coordenador de TG criar a turma.
 */
public record StudentGroupResponse(

   String courseName,
   CourseShift shift,
   Discipline discipline,
   Integer year,
   Integer semester,
   List<StudentResponse> students
) {}