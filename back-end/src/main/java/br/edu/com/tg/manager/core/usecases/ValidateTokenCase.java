package br.edu.com.tg.manager.core.usecases;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a lógica
 * de validar token.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface ValidateTokenCase {

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição.
     * @param token Token de confirmação.
     */
    record Input(String token) {}

    /**
     * Método de contrato de domínio:
     * Executa a validação de token.
     * @param input Porta-dados da requisição.
     */
    void execute(Input input);
}