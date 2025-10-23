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

    /**
     * Método de infra estrutura:
     * Captura os dados da requisição POST para criar um novo professor.
     * @param request Porta-dados das informações requisitadas.
     * @return ResponseEntity do record montado para exibição, ResponseEntity do
     * tipo texto para DomainException ou Exception genérica.
     */
    @PostMapping("create")
    public ResponseEntity<ProfessorResponse>
    create(@RequestBody ProfessorRequest request) {

        /*
         * Atribuindo ao porta-dados do caso de uso, os dados recolhidos da
         * requisição POST.
         */
        var input = new CreateProfessorCase.Input(

            request.name(),
            request.registration(),
            request.email(),
            request.password(),
            request.role()
        );

        /*
         * Executando o caso de uso. Por injeção de dependência, quem irá
         * aplicar a lógica será o service responsável pelo caso de uso.
         */
        var result = useCase.execute(input);

        // Monta um DTO de resposta com todos os dados
        var responseBody = new ProfessorResponse(

            result.id(),
            result.name(),
            result.registration(),
            result.email(),
            result.role()
        );

        // Retorna ao cliente o response body montado.
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}