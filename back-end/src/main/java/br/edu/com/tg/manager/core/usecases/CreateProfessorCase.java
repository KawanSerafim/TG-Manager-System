package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de cadastrar um professor.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface CreateProfessorCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param name Nome do professor.
     * @param registration Matrícula do professor.
     * @param email Email do professor.
     * @param password Senha do professor.
     * @param role Cargo do professor.
     * @param executorEmail Email do executor da ação.
     */
    record Input(
            String name,
            String registration,
            String email,
            String password,
            ProfessorRole role,
            String executorEmail
    ) {}

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados de informações dos coordenadores do curso.
     * @param id ID do professor
     * @param name Nome do professor.
     * @param registration Matrícula do professor.
     * @param email Email do professor.
     * @param role Cargo do professor.
     */
    record Output(
            Long id,
            String name,
            String registration,
            String email,
            ProfessorRole role
    ) {}

    /**
     * Método de contrato de núcleo:
     * Executa o cadastro de um professor no sistema.
     * @param input Porta-dados da requisição POST.
     * @return Output.
     */
    Output execute(Input input);
}