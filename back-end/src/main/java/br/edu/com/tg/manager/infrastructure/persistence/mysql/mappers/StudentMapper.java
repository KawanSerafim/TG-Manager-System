package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.StudentModel;
import org.springframework.stereotype.Component;

/**
 * Mapeador.
 * Estabelece uma ponte entre a entidade de domínio Student com o modelo de
 * dados StudentModel.
 * 
 * Anotação @Component: indica ao Spring que essa classe deve ser gerenciada,
 * permitindo que ela possa ser injetada.
 */
@Component
public class StudentMapper {

    private final StudentGroupMapper studentGroupMapper;

    public StudentMapper(StudentGroupMapper studentGroupMapper) {

        this.studentGroupMapper = studentGroupMapper;
    }

    /**
     * Mapeia a entidade de domínio Student para o modelo de dados StudentModel.
     * @param domain Entidade de domínio Student.
     * @return Modelo de dados StudentModel.
     */
    public StudentModel toModel(Student domain) {

        if(domain == null) {

            return null;
        }

        var studentModel = new StudentModel();

        studentModel.setId(domain.getId());
        studentModel.setName(domain.getName());
        studentModel.setRegistration(domain.getRegistration());
        studentModel.setEmail(domain.getEmail());
        studentModel.setHashedPassword(domain.getHashedPassword());
        studentModel.setStatus(domain.getStatus());
        studentModel.setStudentGroup(

            studentGroupMapper.toModel(domain.getStudentGroup())
        );

        return studentModel;
    }

    /**
     * Mapeia o modelo de dados StudentModel para a entidade de domínio Student.
     * @param model Modelo de dados StudentModel.
     * @return Entidade de domínio Student.
     */
    public Student toDomain(StudentModel model) {

        if(model == null) {

            return null;
        }

        var student = new Student(

            model.getName(),
            model.getRegistration(),
            studentGroupMapper.toDomain(model.getStudentGroup())
        );

        if(model.getStatus() == StudentStatus.ACTIVE) {

            student.setEmail(model.getEmail());
            student.setHashedPassword(model.getHashedPassword());
        }

        student.setId(model.getId());
        student.setStatus(model.getStatus());

        return student;
    }
}