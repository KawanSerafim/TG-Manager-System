package br.edu.com.tg.manager.core.exception;

/**
 * Exceção especializada.
 * Delega uma mensagem de exceção em casos de erro
 * envolvendo interpretação de CSV.
 */
public class CsvParsingException extends RuntimeException {

    public CsvParsingException(String message, Throwable cause) {
        
        super(message, cause);
    }
}