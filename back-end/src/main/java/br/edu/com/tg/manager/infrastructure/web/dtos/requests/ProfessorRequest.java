package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

public record ProfessorRequest(
        String name,
        String registration,
        String email,
        String password,
        ProfessorRole role
) {}