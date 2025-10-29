package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de logar no sistema.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface LoginCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param email Email do usuário.
     * @param password Senha do usuário.
     */
    record Input(String email, String password) {}

    record Output(
            String userName,
            String email,
            UserAccountStatus userAccountStatus
    ) {}

    /**
     * Método de contrato de núcleo:
     * Executa o login do usuário.
     * @param input Porta-dados da requisição POST.
     */
    Output execute(Input input);
}