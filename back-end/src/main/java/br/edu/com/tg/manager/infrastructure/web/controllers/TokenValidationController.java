package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.ValidateTokenCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.ValidateTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validate-token")
@CrossOrigin(origins = "*")
public class TokenValidationController {
    private final ValidateTokenCase useCase;

    public TokenValidationController(ValidateTokenCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<String> validateToken(
            @RequestBody ValidateTokenRequest request
    ) {
        var input = new ValidateTokenCase.Input(request.token());

        useCase.execute(input);

        return ResponseEntity.ok("Token validado com sucesso.");
    }
}