package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

/**
 * Porta-dados de resposta:
 * Padroniza a estrutura de um JSON para mensagens de erro da API.
 */
public record ErrorResponse(
    String message
) {}