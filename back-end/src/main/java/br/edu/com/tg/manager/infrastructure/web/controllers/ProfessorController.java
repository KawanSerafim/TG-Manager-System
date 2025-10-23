package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.usecases.CreateProfessorCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.ProfessorRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.ProfessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor/api")
@CrossOrigin(origins = "*")
public class ProfessorController {

    private static final Logger log = LoggerFactory
        .getLogger(ProfessorController.class);
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
    @PostMapping("create-professor")
    public ResponseEntity<?> create(@RequestBody ProfessorRequest request) {

        try {

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
        } catch (DomainException e) {

            /*
             * Qualquer exceção de domínio lançado nas camadas internas, são
             * tratadas aqui.
             */
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {

            // Logger para lançar o corpo da mensagem da exceção só no terminal.
            log.error("Ocorreu um erro inesperado no servidor.", e);

            // Retorna ao cliente um body informando erro no servidor.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(

                    "Ocorreu um erro inesperado no servidor. Por favor, " +
                    "tente novamente mais tarde."
                );
        }
    }
}