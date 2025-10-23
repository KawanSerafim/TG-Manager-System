package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.Discipline;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados que o coordenador de TG usa para enviar a requisição.
 */
public record StudentGroupRequest(

    String courseName,
    Discipline discipline
) {}