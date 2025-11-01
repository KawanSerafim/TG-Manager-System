package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.LoginCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.LoginRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses
        .JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login/api")
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginCase useCase;

    public LoginController(LoginCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> login(
            @RequestBody LoginRequest request
    ) {
        var input = new LoginCase.Input(request.email(),request.password());

        var result = useCase.execute(input);

        var responseBody = new JwtAuthenticationResponse(
                result.token(),
                result.tokenType()
        );

        return ResponseEntity.ok(responseBody);
    }
}