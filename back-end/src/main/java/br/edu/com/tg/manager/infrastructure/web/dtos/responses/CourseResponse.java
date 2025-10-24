package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.usecases.CreateCourseCase;

public record CourseResponse(

    Long id,
    String name,
    CourseShift shift,
    CreateCourseCase.CoordinatorInfo tgCoordinator,
    CreateCourseCase.CoordinatorInfo courseCoordinator
) {}