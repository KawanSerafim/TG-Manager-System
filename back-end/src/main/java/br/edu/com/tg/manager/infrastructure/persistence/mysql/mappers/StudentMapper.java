package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import org.springframework.stereotype.Component;

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
        studentModel.setStudentGroup(
                studentGroupMapper.toModel(domain.getStudentGroup())
        );

        return studentModel;
    }

    public Student toDomain(StudentModel model) {
        if(model == null) {
            return null;
        }

        var student = new Student(
                model.getName(),
                model.getRegistration(),
                studentGroupMapper.toDomain(model.getStudentGroup())
        );

        student.setUserAccount(
                userAccountMapper.toDomain(model.getUserAccount())
        );
        student.setId(model.getId());
        student.setStatus(model.getStatus());

        return student;
    }
}