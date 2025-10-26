package br.edu.com.tg.manager.core.ports.gateways;

/**
 * Portão de acesso de domínio:
 * Define um contrato para a ação de enviar um email mediante um
 * serviço externo (SMTP).
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public interface EmailSender {
    /**
     * Método de contrato de domínio:
     * Executa o envio de email a um destino.
     * @param to Email de destino.
     * @param subject Assunto do email.
     * @param body Corpo do conteúdo do email.
     */
    void sendEmail(String to, String subject, String body);
}