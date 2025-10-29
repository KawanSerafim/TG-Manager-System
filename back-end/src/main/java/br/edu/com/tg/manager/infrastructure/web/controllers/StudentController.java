package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.CompleteStudentRegistrationCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.CompleteRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students/api")
@CrossOrigin(origins = "*")
public class StudentController {
    private final CompleteStudentRegistrationCase useCase;

    public StudentController(
            CompleteStudentRegistrationCase useCase
    ) {
        this.useCase = useCase;
    }

    @PostMapping("complete-registration")
    public ResponseEntity<String> completeRegistration(
            @RequestBody CompleteRegistrationRequest request
    ) {
        var input = new CompleteStudentRegistrationCase.Input(
                request.registration(),
                request.email(),
                request.password()
        );

        useCase.execute(input);

        return ResponseEntity.ok("Cadastro Finalizado com sucesso!");
    }
}