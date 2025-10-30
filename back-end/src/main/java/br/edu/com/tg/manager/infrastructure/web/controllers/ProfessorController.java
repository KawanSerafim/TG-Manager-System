package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.usecases.CreateProfessorCase;
import br.edu.com.tg.manager.core.usecases.ListCourseCoordinatorsCase;
import br.edu.com.tg.manager.core.usecases.ListTgCoordinatorsCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests.ProfessorRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.professor
        .ListCoordinatorResponse;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.professor
        .ProfessorResponse;
import br.edu.com.tg.manager.infrastructure.web.mappers.ListCourseCoordinatorResponseMapper;
import br.edu.com.tg.manager.infrastructure.web.mappers
        .ListTgCoordinatorResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professors/api")
@CrossOrigin(origins = "*")
public class ProfessorController {
    private final CreateProfessorCase createUseCase;
    private final ListTgCoordinatorsCase listTgUseCase;
    private final ListCourseCoordinatorsCase listCourseUseCase;
    private final ListTgCoordinatorResponseMapper tgMapper;
    private final ListCourseCoordinatorResponseMapper courseMapper;

    public ProfessorController(
            CreateProfessorCase createUseCase,
            ListTgCoordinatorsCase listTgUseCase,
            ListCourseCoordinatorsCase listCourseUseCase,
            ListTgCoordinatorResponseMapper tgMapper,
            ListCourseCoordinatorResponseMapper courseMapper
    ) {
        this.createUseCase = createUseCase;
        this.listTgUseCase = listTgUseCase;
        this.listCourseUseCase = listCourseUseCase;
        this.tgMapper = tgMapper;
        this.courseMapper = courseMapper;
    }

    @PostMapping("create")
    public ResponseEntity<ProfessorResponse> create(
            @RequestBody ProfessorRequest request
    ) {
        var input = new CreateProfessorCase.Input(
                request.name(),
                request.registration(),
                request.email(),
                request.password(),
                request.role()
        );

        var result = createUseCase.execute(input);

        var responseBody = new ProfessorResponse(
                result.id(),
                result.name(),
                result.registration(),
                result.email(),
                result.role()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/list/tg-coordinators")
    public ResponseEntity<List<ListCoordinatorResponse>> listTgCoordinators() {
        var results = listTgUseCase.execute();

        var responseBody = tgMapper.toResponseList(results);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping("/list/course-coordinators")
    public ResponseEntity<List<ListCoordinatorResponse>> listCourseCoordinators() {
        var results = listCourseUseCase.execute();

        var responseBody = courseMapper.toResponseList(results);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}