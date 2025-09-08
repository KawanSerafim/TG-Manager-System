package br.edu.com.tg.manager.infrastructure.controller;

import br.edu.com.tg.manager.core.usecase.ImportStudentsFromCsvUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Camada de Infraestrutura.
 * Ponto de entrada da API REST para funcionalidades relacionadas a Alunos.
 * Lida com requisicoes HTTP e as delega para a camada de aplicacao (casos de uso).
 * (Comentario em portugues)
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final ImportStudentsFromCsvUseCase importStudentsFromCsvUseCase;

    public StudentController(ImportStudentsFromCsvUseCase importStudentsFromCsvUseCase) {
     
        this.importStudentsFromCsvUseCase = importStudentsFromCsvUseCase;
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<?> uploadStudentsCsv(
        @RequestParam("file") MultipartFile file,
        @RequestParam("courseName") String courseName) {
        
        if (file.isEmpty()) {

            return ResponseEntity.badRequest().body("Por favor, envie um arquivo CSV válido.");
        }

        if (courseName == null || courseName.isBlank()) {
            
            return ResponseEntity.badRequest().body("O nome do curso é obrigatório.");
        }

        try {
            
            importStudentsFromCsvUseCase.importFromCsv(file.getInputStream(), courseName);
            
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            
            return ResponseEntity.internalServerError().body("Falha ao processar o arquivo: " + e.getMessage());
        }
    }
}