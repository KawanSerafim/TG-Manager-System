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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Anotações da web.bind:
 *
 * Anotação @PostMapping: forma encurtada de um RequestMapping para método POST.
 *
 * Anotação @RequestMapping: indica ao Spring Boot o mapeamento das requisições.
 * São dois níveis, o de classe que define o prefixo da URL, e o de método que
 * define o endpoint.
 *
 * Anotação @RequestParam: indica ao Spring Boot o parâmetro que deve ser
 * buscado na requisição, podendo extraí-los à uma variável.
 *
 * Anotação @RequestPart: indica ao Spring Boot o parâmetro que deve ser
 * buscado na requisição, onde o parâmetro é um upload de arquivo.
 *
 * Anotação @RestController: indica ao Spring Boot que esta classe irá receber
 * requisições web e que seus retornos serão sem modelo - geralmente, JSONs.
 */

/**
 * Controlador de fronteira:
 * Estabelece a comunicação com a página HTTP que envia os dados da turma. Por
 * pertencer à infraestrutura da aplicação, esta classe utiliza das anotações
 * web.bind do Spring Boot.
 */
@RestController
@RequestMapping("/student-group/api")
public class StudentGroupController {

    private static final Logger logger = LoggerFactory
        .getLogger(StudentGroupController.class);
    private final CreateStudentGroupCase useCase;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência do caso de uso
     * para criação de turmas.
     * @param useCase Caso de uso de criar turma.
     */
    public StudentGroupController(CreateStudentGroupCase useCase) {

        this.useCase = useCase;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> create(

        @RequestParam("courseName") String courseName,
        @RequestParam("discipline") Discipline discipline,
        @RequestPart("file") MultipartFile file,
        @RequestParam("temporaryPassword") String temporaryPassword
    ) {

        try {

            /*
             * Atribuindo ao porta-dados do caso de uso, os dados recolhidos da
             * requisição POST.
             */
            var input = new CreateStudentGroupCase.Input(

                courseName,
                discipline,
                file.getInputStream(),
                temporaryPassword
            );

            /*
             * Executando o caso de uso. Por injeção de dependência, quem irá
             * aplicar a lógica será o service responsável pelo caso de uso.
             */
            useCase.execute(input);

            // Retorna ao cliente um body informando o sucesso da execução.
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Turma e alunos pré-cadastrados com sucesso!");
        } catch (DomainException e) {

            /*
             * Qualquer exceção de domínio lançado nas camadas internas, são
             * tratadas aqui.
             */
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {

            // Logger para lançar o corpo da mensagem da exceção só no terminal.
            logger.error("Ocorreu um erro inesperado no servidor.", e);

            // Retorna ao cliente um body informando erro no servidor.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    
                    "Ocorreu um erro inesperado no servidor. Por favor, " +
                    "tente novamente mais tarde."
                );
        }
    }
}