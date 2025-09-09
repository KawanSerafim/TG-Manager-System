package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentData;

/**
 * Mapper responsável por converter a entidade de domínio
 * Student para modelo de dados StudentData, e vice-versa.
 */
public class StudentMapper {

    private StudentMapper(){}

    public static Student toDomain(StudentData data) {

        if(data == null) {

            return null;
        }

        var student = new Student(data.getName(), 
        data.getRegistration());
        student.setId(data.getId());
        student.setPassword(data.getPassword());

        student.setStudentGroup(StudentGroupMapper
        .toDomain(data.getStudentGroup()));

        return student;
    }

    public static StudentData toData(Student domain) {

        if(domain == null) {

            return null;
        }

        var studentData = new StudentData();
        studentData.setId(domain.getId());
        studentData.setName(domain.getName());
        studentData.setRegistration(domain.getRegistration());
        studentData.setEmail(domain.getEmail());
        studentData.setPassword(domain.getPassword());

        studentData.setStudentGroup(StudentGroupMapper
        .toData(domain.getStudentGroup()));
    
        return studentData;
    }
}