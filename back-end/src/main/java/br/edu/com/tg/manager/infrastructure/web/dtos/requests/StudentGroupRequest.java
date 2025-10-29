package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.Discipline;

public record StudentGroupRequest(String courseName, Discipline discipline) {}