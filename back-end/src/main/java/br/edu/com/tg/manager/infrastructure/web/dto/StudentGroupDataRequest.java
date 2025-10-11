package br.edu.com.tg.manager.infrastructure.web.dto;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.util.List;

public record StudentGroupDataRequest(

        String courseName,
        Discipline discipline,
        Integer year,
        Integer semester,
        CourseShift courseShift,
        List<StudentDataRequest> students
) {
}
