package br.edu.com.tg.manager.infrastructure.web.dtos;

/**
 * Porta-dados de infra estrutura:
 * Carrega os dados de aluno que estavam no arquivo que o Coordenador de TG
 * enviou.
 */
public record StudentResponse(

    String name,
    String registration
) {}