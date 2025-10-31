package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.usecases.CreateCourseCase;
import java.util.List;

public record CourseResponse(
        Long id,
        String name,
        List<CourseShift> availableShifts,
        List<Discipline> availableDisciplines,
        CreateCourseCase.CoordinatorInfo tgCoordinator,
        CreateCourseCase.CoordinatorInfo courseCoordinator
) {}