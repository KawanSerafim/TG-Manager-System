package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados usados para o administrador criar o professor.
 */
public record ProfessorResponse(

    Long id,
    String name,
    String registration,
    String email,
    ProfessorRole role
) {}