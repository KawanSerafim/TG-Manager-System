package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.SendConfirmationEmailCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.SendConfirmationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email-confirmation/api")
@CrossOrigin(origins = "*")
public class EmailConfirmationController {
    private final SendConfirmationEmailCase useCase;

    public EmailConfirmationController(SendConfirmationEmailCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendConfirmation(
            @RequestBody SendConfirmationRequest request
    ) {
        var input = new SendConfirmationEmailCase.Input(request.email());

        useCase.execute(input);

        return ResponseEntity.ok("Token enviado ao email!");
    }
}