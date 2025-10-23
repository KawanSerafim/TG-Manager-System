package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a
 * lógica de criar um professor.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface CreateProfessorCase {

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição.
     * @param name Nome do professor.
     * @param registration Matricula do professor.
     * @param email Email do professor.
     * @param password Senha do professor.
     * @param role Cargo do professor.
     */
    record Input(

        String name,
        String registration,
        String email,
        String password,
        ProfessorRole role
    ) {}

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição que foram salvos.
     * @param name Nome do professor.
     * @param registration Matricula do professor.
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
     * Método de contrato de domínio:
     * Executa a criação de um novo professor, e o persiste no banco de dados.
     * @param input Porta-dados da requisição.
     * @return Porta-dados da resposta da requisição.
     */
    Output execute(Input input);
}