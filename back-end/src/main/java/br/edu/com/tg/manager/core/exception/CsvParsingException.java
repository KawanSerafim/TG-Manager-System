package br.edu.com.tg.manager.core.exception;

public class CsvParsingException extends RuntimeException {

    public CsvParsingException(String message, Throwable cause) {
        
        super(message, cause);
    }
}