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
    public ResponseEntity<CourseResponse> create(
            @RequestBody CourseRequest request,
            @RequestHeader("Authorization") String token
    ) {
        var input = new CreateCourseCase.Input(
                request.name(),
                request.availableShifts(),
                request.availableDisciplines(),
                request.tgCoordinatorRegistration(),
                request.courseCoordinatorRegistration(),
                token
        );

        var result = useCase.execute(input);

        var responseBody = new CourseResponse(
                result.id(),
                result.name(),
                result.availableShifts(),
                result.availableDisciplines(),
                result.tgCoordinator(),
                result.courseCoordinator()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}