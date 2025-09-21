package br.edu.com.tg.manager.core.exceptions;

/**
 * Exceção especializada.
 * Lançada para indicar uma violação de uma regra de negócio dentro do domínio
 * da aplicação.
 */
public class DomainException extends RuntimeException {

    /**
     * Construtor que aceita a mensagem de erro.
     * @param message Mensagem detalhando a violação da regra de negócio.
     */
    public DomainException(String message) {

        super(message);
    }
}