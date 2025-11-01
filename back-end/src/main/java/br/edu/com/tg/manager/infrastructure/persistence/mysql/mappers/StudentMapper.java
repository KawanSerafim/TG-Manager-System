package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    private final UserAccountMapper userAccountMapper;
    private final StudentGroupMapper studentGroupMapper;

    public StudentMapper(
            UserAccountMapper userAccountMapper,
            StudentGroupMapper studentGroupMapper
    ) {
        this.userAccountMapper = userAccountMapper;
        this.studentGroupMapper = studentGroupMapper;
    }

    public StudentModel toModel(Student domain) {
        if(domain == null) {
            return null;
        }

        var studentModel = new StudentModel();
        studentModel.setId(domain.getId());
        studentModel.setName(domain.getName());
        studentModel.setRegistration(domain.getRegistration());
        studentModel.setUserAccount(
                userAccountMapper.toModel(domain.getUserAccount())
        );
        studentModel.setStatus(domain.getStatus());

        if(domain.getStudentGroups() != null) {
            studentModel.setStudentGroups(
                    domain.getStudentGroups().stream()
                            .map(studentGroupMapper::toModel)
                            .collect(Collectors.toList())
            );
        }

        return studentModel;
    }

    public Student toDomain(StudentModel model) {
        if(model == null) {
            return null;
        }

        var student = new Student();
        student.setId(model.getId());
        student.setName(model.getName());
        student.setRegistration(model.getRegistration());
        student.setStatus(model.getStatus());
        student.setUserAccount(
                userAccountMapper.toDomain(model.getUserAccount())
        );

        if(model.getStudentGroups() != null) {
            student.setStudentGroups(
                    model.getStudentGroups().stream()
                            .map(studentGroupMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return student;
    }
}