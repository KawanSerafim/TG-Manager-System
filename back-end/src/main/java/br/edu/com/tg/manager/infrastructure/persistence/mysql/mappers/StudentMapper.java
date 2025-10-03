package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component: indica ao Spring que esta classe deve ser gerenciada
 * pelo framework.
 */

/**
 * Mapeador:
 * Estabelece uma ponte entre a entidade de domínio Student com o modelo de
 * dados StudentModel.
 */
@Component
public class StudentMapper {

    private final StudentGroupMapper studentGroupMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta diretamente a dependência de que, ao criar StudentMapper,
     * StudentGroupMapper também é criado.
     * @param studentGroupMapper Mapeador de turma.
     */
    public StudentMapper(StudentGroupMapper studentGroupMapper) {

        this.studentGroupMapper = studentGroupMapper;
    }

    /**
     * Método de aplicação:
     * Converte a entidade de domínio Student para o modelo de dados StudentModel.
     * @param domain Entidade de domínio Student.
     * @return Modelo de dados StudentModel.
     */
    public StudentModel toModel(Student domain) {

        // Cláusula de guarda.
        if(domain == null) {

            return null;
        }

        // Inserção dos dados da entidade de domínio no modelo de dados.
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

        // Retorno do modelo de dados.
        return studentModel;
    }

    /**
     * Método de aplicação:
     * Converte o modelo de dados StudentModel para a entidade de domínio Student.
     * @param model Modelo de dados StudentModel.
     * @return Entidade de domínio Student.
     */
    public Student toDomain(StudentModel model) {

        // Cláusula de guarda.
        if(model == null) {

            return null;
        }

        // Inserção dos dados do modelo de dados na entidade de domínio.
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

        // Retorno da entidade de domínio.
        return student;
    }
}