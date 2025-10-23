package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados de aluno que estavam no arquivo que o coordenador de TG
 * enviou.
 */
public record StudentResponse(

    String name,
    String registration
) {}