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

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(
            DomainException ex
    ) {
        var errorResponse = new ErrorResponse(ex.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJson(
            HttpMessageNotReadableException ex
    ) {
        var errorResponse = new ErrorResponse(
                "O corpo da requisição (JSON) está malformado ou ilegível."
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Ocorreu um erro inesperado no servidor.", ex);

        var errorResponse = new ErrorResponse(
                "Ocorreu um erro inesperado no servidor."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}