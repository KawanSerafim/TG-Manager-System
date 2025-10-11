package br.edu.com.tg.manager.infrastructure.web.dto;

/**
 * Porta-dados de infra estrutura:
 * Carrega os dados dos alunos que estão num arquivo.
 * @param name Nome do aluno.
 * @param registration Matrícula do aluno.
 */
public record StudentDataRequest(

        String name,
        String registration
) {
}
