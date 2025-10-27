package br.edu.com.tg.manager.infrastructure.web.mappers;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentResponseMapper {
    public StudentResponse toResponse(Student domain) {
        return new StudentResponse(domain.getName(), domain.getRegistration());
    }

    public List<StudentResponse> toResponseList(List<Student> domains) {
        return domains.stream().map(this::toResponse).toList();
    }
}