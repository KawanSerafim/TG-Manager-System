package br.edu.com.tg.manager.infrastructure.controller;

import br.edu.com.tg.manager.application.dto.StudentCsvDTO;
import br.edu.com.tg.manager.core.usecase.ReadCsvUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    private final ReadCsvUseCase readCsvUseCase;

    /*
     * O controller depende da interface do caso de uso (Porta de Entrada)
     * nao da sua implementacao. O ReadCsvService eh injetado no construtor.
     */
    public StudentController(ReadCsvUseCase readCsvUseCase) {
     
        this.readCsvUseCase = readCsvUseCase;
    }

    @PostMapping("/upload-csv")
    public ResponseEntity<?> uploadStudentsCsv(@RequestParam("file") MultipartFile file) {
        
        if (file.isEmpty()) {

            return ResponseEntity.badRequest().body("Por favor, envie um arquivo CSV válido.");
        }

        try {
            
            List<StudentCsvDTO> students = readCsvUseCase.importFromCsv(file.getInputStream(), StudentCsvDTO.class);
            
            return ResponseEntity.ok(students);
        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            
            return ResponseEntity.internalServerError().body("Falha ao processar o arquivo: " + e.getMessage());
        }
    }
}