package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests
        .StudentGroupRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses
        .StudentGroupResponse;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student-group/api")
@CrossOrigin(origins = "*")
public class StudentGroupController {

    private final CreateStudentGroupCase useCase;
    private final StudentDataReader studentDataReader;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência do caso de uso
     * para criação de turmas.
     * @param useCase Caso de uso de criar turma.
     * @param studentDataReader Portão de acesso de domínio dos dados dos alunos
     * e demais informações da turma.
     */
    public StudentGroupController(

        CreateStudentGroupCase useCase,
        StudentDataReader studentDataReader
    ) {

        this.useCase = useCase;
        this.studentDataReader = studentDataReader;
    }

    /**
     * Método de infra estrutura:
     * Captura os dados da requisição POST para criar uma nova turma.
     * @param request Porta-dados das informações requisitadas.
     * @return ResponseEntity do record montado para exibição, ResponseEntity do
     * tipo texto para DomainException ou Exception genérica.
     */
    @PostMapping(consumes = "multipart/form-data", path = "create")
    public ResponseEntity<StudentGroupResponse> create(

        StudentGroupRequest request,
        @RequestPart("file") MultipartFile file
    ) throws IOException {

        /*
         * Salva numa instância um record que traduz os dados vindo
         * do arquivo.
         */
        var fileData = studentDataReader.read(file.getInputStream());

        /*
         * Atribuindo ao porta-dados do caso de uso, os dados recolhidos da
         * requisição POST.
         */
        var input = new CreateStudentGroupCase.Input(

            request.courseName(),
            request.discipline(),
            fileData
        );

        /*
         * Executando o caso de uso. Por injeção de dependência, quem irá
         * aplicar a lógica será o service responsável pelo caso de uso.
         */
        var result = useCase.execute(input);

        // Converte a lista de alunos para um DTO.
        List<StudentResponse> studentDTOs = result.students().stream()
            .map(student ->

                new StudentResponse(

                    student.getName(),
                    student.getRegistration()
                )
            ).toList();

        // Monta um DTO de resposta com todos os dados
        var responseBody = new StudentGroupResponse(

            result.courseName(),
            result.courseShift(),
            result.discipline(),
            result.year(),
            result.semester(),
            studentDTOs
        );

        // Retorna ao cliente o response body montado.
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseBody);
    }
}