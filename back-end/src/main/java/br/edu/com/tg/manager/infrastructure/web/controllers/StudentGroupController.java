package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student-group/api")
public class StudentGroupController {

    private final CreateStudentGroupCase usecase;

    public StudentGroupController(CreateStudentGroupCase usecase) {

        this.usecase = usecase;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> create(

        @RequestParam("courseName") String courseName,
        @RequestParam("discipline") Discipline discipline,
        @RequestPart("file") MultipartFile file,
        @RequestParam("temporaryPassword") String temporaryPassword
    ) {

        try {

            var input = new CreateStudentGroupCase.Input(

                courseName,
                discipline,
                file.getInputStream(),
                temporaryPassword
            );

            usecase.execute(input);

            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Turma e alunos pré-cadastrados com sucesso!");
        } catch (DomainException e) {
            
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    
                    "Ocorreu um erro inesperado no servidor. Por favor, " +
                    "tente novamente mais tarde."
                );
        }
    }
}