package br.edu.com.tg.manager.core.usecases;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de validar um token.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface ValidateTokenCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param token Token.
     */
    record Input(String token) {}

    /**
     * Método de contrato de núcleo:
     * Executa a validação de um token.
     * @param input Porta-dados da requisição POST.
     */
    void execute(Input input);
}