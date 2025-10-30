package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

public record ProfessorResponse(
        Long id,
        String name,
        String registration,
        String email,
        ProfessorRole role
) {}