package br.edu.com.tg.manager.core.usecases;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a lógica
 * de enviar um email de confirmação.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface SendConfirmationEmailCase {

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição.
     * @param email Email do usuário.
     */
    record Input(String email) {}

    /**
     * Método de contrato de domínio:
     * Executa o envio de email de confirmação.
     * @param input Porta-dados da requisição.
     */
    void execute(Input input);
}