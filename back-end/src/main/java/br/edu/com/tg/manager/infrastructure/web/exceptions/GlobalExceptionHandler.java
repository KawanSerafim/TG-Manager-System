package br.edu.com.tg.manager.infrastructure.web.exceptions;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Auxiliar de controladores de API-REST:
 * Interceta exceções lançadas por qualquer @RestController e as traduz em
 * respostas HTTP padronizadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory
        .getLogger(GlobalExceptionHandler.class);

    /**
     * Manipulador de exceções:
     * Captura qualquer DomainException lançada pela camada de negócio.
     * @param ex Exceção de domínio capturada.
     * @return ResponseEntity com status 400 e porta-dados de erro.
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse>
    handleDomainException(DomainException ex) {

        var errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse>
        handleMalformedJson(HttpMessageNotReadableException ex) {

        var errorResponse = new ErrorResponse(

            "O corpo da requisição (JSON) está malformado ou ilegível."
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Manipulador de exceções:
     * Captura de maneira global todas as exceções que não foram erros do
     * cliente, e sim em algo interno.
     * @param ex Exceção capturada.
     * @return ResponseEntity com status 500 e porta-dados de erro.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        // Exceção completa via terminal.
        log.error("Ocorreu um erro inesperado no servidor.", ex);

        // Exceção filtrada para exibição.
        var errorResponse = new ErrorResponse(

            "Ocorreu um erro inesperado no servidor."
        );

        // O retorno é apenas a exceção filtrada.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResponse);
    }
}