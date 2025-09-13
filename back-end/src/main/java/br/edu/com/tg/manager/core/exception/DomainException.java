package br.edu.com.tg.manager.core.exception;

/**
 * Exceção especializada.
 * Delega uma mensagem de exceção em casos de erro
 * envolvendo violações de regras de negócio.
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {

        super(message);
    }
}