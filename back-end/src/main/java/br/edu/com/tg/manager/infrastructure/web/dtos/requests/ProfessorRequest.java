package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados que o administrador usa para enviar a requisição.
 */
public record ProfessorRequest(

    String name,
    String registration,
    String email,
    String password,
    ProfessorRole role
) {}