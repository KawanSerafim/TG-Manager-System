package br.edu.com.tg.manager.core.exception;

/**
 * Exceção especializada.
 * Delega uma mensagem de exceção em casos de erro envolvendo a tentativa de
 * usar um adaptador de um arquivo com um tipo de DTO (ou classe alvo) que ele 
 * não suporta.
 */
public class UnsupportedFileTypeException extends RuntimeException {

    public UnsupportedFileTypeException(String message) {

        super(message);
    }
}