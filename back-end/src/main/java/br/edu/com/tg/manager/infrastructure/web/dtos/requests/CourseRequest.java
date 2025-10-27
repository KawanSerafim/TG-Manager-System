package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;

public record CourseRequest(
        String name,
        CourseShift shift,
        String tgCoordinatorRegistration,
        String courseCoordinatorRegistration
) {}