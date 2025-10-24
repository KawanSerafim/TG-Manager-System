package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.CreateCourseCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.CourseRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.CourseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/api")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CreateCourseCase useCase;

    public CourseController(CreateCourseCase useCase) {

        this.useCase = useCase;
    }

    @PostMapping("create")
    public ResponseEntity<CourseResponse>
    create(@RequestBody CourseRequest request) {

        /*
         * Atribuindo ao porta-dados do caso de uso, os dados recolhidos da
         * requisição POST.
         */
        var input = new CreateCourseCase.Input(

            request.name(),
            request.shift(),
            request.tgCoordinatorRegistration(),
            request.courseCoordinatorRegistration()
        );

        /*
         * Executa o caso de uso. Por injeção de dependência, quem irá
         * aplicar a lógica será o service responsável pelo caso de uso.
         */
        var result = useCase.execute(input);

        // Monta um DTO de resposta com todos os dados
        var responseBody = new CourseResponse(

            result.id(),
            result.name(),
            result.shift(),
            result.tgCoordinator(),
            result.courseCoordinator()
        );

        // Retorna ao cliente o response body montado.
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}