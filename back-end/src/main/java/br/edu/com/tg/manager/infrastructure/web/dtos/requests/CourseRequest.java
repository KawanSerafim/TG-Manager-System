package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.util.List;

public record CourseRequest(
        String name,
        List<CourseShift> availableShifts,
        List<Discipline> availableDisciplines,
        String tgCoordinatorRegistration,
        String courseCoordinatorRegistration
) {}