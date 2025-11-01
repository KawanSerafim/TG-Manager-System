package br.edu.com.tg.manager.infrastructure.web.controllers;

import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import br.edu.com.tg.manager.infrastructure.gateways.security.authentication.CustomUserDetails;
import br.edu.com.tg.manager.infrastructure.web.dtos.requests
        .StudentGroupRequest;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses
        .StudentGroupResponse;
import br.edu.com.tg.manager.infrastructure.web.mappers.StudentResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/student-group/api")
@CrossOrigin(origins = "*")
public class StudentGroupController {
    private final CreateStudentGroupCase useCase;
    private final StudentDataReader studentDataReader;
    private final StudentResponseMapper studentResponseMapper;

    public StudentGroupController(
            CreateStudentGroupCase useCase,
            StudentDataReader studentDataReader,
            StudentResponseMapper studentResponseMapper
    ) {
        this.useCase = useCase;
        this.studentDataReader = studentDataReader;
        this.studentResponseMapper = studentResponseMapper;
    }

    @PostMapping(consumes = "multipart/form-data", path = "create")
    public ResponseEntity<StudentGroupResponse> create(
            StudentGroupRequest request,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails loggedInUser
    ) throws IOException {
        var fileData = studentDataReader.read(file.getInputStream());
        var input = new CreateStudentGroupCase.Input(
                request.courseName(),
                request.discipline(),
                fileData,
                loggedInUser.getUsername()
        );

        var result = useCase.execute(input);

        var studentDTOs = studentResponseMapper
                .toResponseList(result.students());

        var responseBody = new StudentGroupResponse(
                result.courseName(),
                result.courseShift(),
                result.discipline(),
                result.year(),
                result.semester(),
                studentDTOs
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBody);
    }
}