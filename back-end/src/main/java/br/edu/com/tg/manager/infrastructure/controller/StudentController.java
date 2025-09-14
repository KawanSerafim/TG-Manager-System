package br.edu.com.tg.manager.infrastructure.controller;

import br.edu.com.tg.manager.core.usecase.ImportStudentsFromFileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.Year;

/**
 * Pontos de entrada da API REST.
 * O framework permite que essa classe se comunique com chamadas externas via
 * protocolo HTTP. Aqui, pode-se coletar dados estrangeiros e enviá-los à
 * aplicação, ou vice-versa.
 * 
 * Anotação @RestController: indica ao framework que esta classe é um
 * controlador de API e que seus métodos irão retornar dados (geralmente JSON)
 * diretamente no corpo da resposta.
 * 
 * Anotação @RequestMapping: indica ao framework qual o prefixo da URL base
 * para todos os endpoints nesta classe. Dentro do parênteses, estará o
 * prefixo da URL.
 * 
 * Anotação @CrossOrigin: indica ao framework uma exceção à um domínio para ter
 * acesso a realizar requisições para esta API. O valor inserido ao 'origins'
 * indicará qual o domínio irá poder ter esse privilégio, enquanto '*' quer
 * dizer que qualquer um pode acessar.
 * 
 * Anotação @PostMapping: indica ao framework qual é o endpoint do método. A
 * URL irá completar o prefixo com o endpoint, formando o link para a requisição
 * HTTP para coleta ou envio de dados.
 * 
 * Anotação @RequestParam: indica ao framework qual o nome do parâmetro do dado
 * que a requisição irá enviar, e assim adicionar à uma variável adequada ao
 * tipo de dado coletado.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    /* Instância do contrato do caso de uso para importar alunos de um
     * aqui.
     */
    private final ImportStudentsFromFileUseCase importStudentsFromCsvUseCase;

    /* Injeção de dependência para quem for implementar o caso de uso
     * inserido no construtor.
     */
    public StudentController(
        ImportStudentsFromFileUseCase importStudentsFromCsvUseCase
    ) {
     
        this.importStudentsFromCsvUseCase = importStudentsFromCsvUseCase;
    }

    public ResponseEntity<?> badRequest(String body) {
        
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Endpoint para fazer o upload de um arquivo para importação de alunos.
     * Responde à requisições POST em /api/students/upload-file.
     * @param file Arquivo enviado pelo cliente.
     * @param courseName Nome do curso que a turma pertence.
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @return Uma resposta HTTP com status 204 (No Content) em caso de sucesso,
     * ou um status de erro (400 ou 500) em caso de falha.
     */
    @PostMapping("/upload-csv")
    public ResponseEntity<?> uploadStudentsFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("courseName") String courseName,
        @RequestParam("year") Integer year,
        @RequestParam("semester") Integer semester) {
        
        /* Validação de fronteira: enviar um arquivo é obrigatório. */
        if(file.isEmpty()) {

            return badRequest("Por favor, envie um arquivo CSV válido.");
        }

        /* Validação de fronteira: o nome do curso não pode ser vazio. */
        if(courseName == null || courseName.isBlank()) {
            
            return badRequest("O nome do curso é obrigatório.");
        }

        /* Validação de fronteira: o ano não pode ser vazio e nem pode ser
         * menor que o ano atual.
         */
        int currentYear = Year.now().getValue();

        if(year == null || year < currentYear) {

            return badRequest(
                "O ano fornecido é inválido. Deve ser um número " +
                "a partir do ano atual."
            );
        }

        /* Validação de fronteira: o semestre não pode ser nada além que
         * 1 ou 2.
         */
        if(semester == null || (semester != 1 && semester != 2)) {

            return badRequest(
                "O semestre fornecido é inválido. Deve ser 1 ou 2."
            );
        }

        try {
            
            /* Delega a execução para a camada de aplicação através da injeção
             * de dependência, passando os dados extraídos.
            */
            importStudentsFromCsvUseCase.importFromFile(
                file.getInputStream(), 
                courseName, 
                year, 
                semester
            );
            
            /* Retorna uma resposta de sucesso sem conteúdo. */
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {

            /* Captura as exceções especializadas da camada core e as
             * retorna uma mensagem adequada ao erro.
             */
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            
            /* Captura erros de mais baixo nível, como falha ao ler
             * o stream do arquivo enviado.
             */
            return ResponseEntity.internalServerError().body(
                "Falha ao processar o arquivo: " + e.getMessage()
            );
        }
    }
}