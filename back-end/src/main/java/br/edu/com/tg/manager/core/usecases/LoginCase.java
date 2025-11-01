package br.edu.com.tg.manager.core.usecases;

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

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados de informações dos coordenadores do curso.
     * @param token Token JWT gerado.
     * @param tokenType O tipo do Token.
     */
    record Output(String token, String tokenType) {}

    /**
     * Método de contrato de núcleo:
     * Executa o login do usuário.
     * @param input Porta-dados da requisição POST
     * @return Output.
     */
    Output execute(Input input);
}