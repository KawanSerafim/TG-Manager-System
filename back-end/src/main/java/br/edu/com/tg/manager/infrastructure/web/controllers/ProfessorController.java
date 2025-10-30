package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.CreateProfessorCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.ProfessorRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.ProfessorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professors/api")
@CrossOrigin(origins = "*")
public class ProfessorController {
    private final CreateProfessorCase useCase;

    public ProfessorController(CreateProfessorCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("create")
    public ResponseEntity<ProfessorResponse> create(
            @RequestBody ProfessorRequest request
    ) {
        var input = new CreateProfessorCase.Input(
                request.name(),
                request.registration(),
                request.email(),
                request.password(),
                request.role()
        );

        var result = useCase.execute(input);

        var responseBody = new ProfessorResponse(
                result.id(),
                result.name(),
                result.registration(),
                result.email(),
                result.role()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}