package br.edu.com.tg.manager.core.exception;

/**
 * Exceção especializada.
 * Delega uma mensagem de exceção em casos de erro envolvendo
 * interpretação de arquivo.
 */
public class FileParsingException extends RuntimeException {

    public FileParsingException(String message, Throwable cause) {
        
        super(message, cause);
    }
}