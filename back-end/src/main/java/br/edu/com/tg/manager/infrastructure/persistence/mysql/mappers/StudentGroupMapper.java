package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentGroupModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component: indica ao Spring que esta classe deve ser gerenciada
 * pelo framework.
 */

/**
 * Mapeador:
 * Estabelece uma ponte entre a entidade de domínio StudentGroup com o modelo de
 * dados StudentGroupModel.
 */
@Component
public class StudentGroupMapper {

    private final CourseMapper courseMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta diretamente a dependência de que, ao criar StudentGroupMapper,
     * CourseMapper também é criado.
     * @param courseMapper Mapeador de curso.
     */
    public StudentGroupMapper(CourseMapper courseMapper) {

        this.courseMapper = courseMapper;
    }

    /**
     * Método de aplicação:
     * Converte a entidade de domínio StudentGroup para o modelo de dados
     * StudentGroupModel.
     * @param domain Entidade de domínio StudentGroup.
     * @return Modelo de dados StudentGroupModel.
     */
    public StudentGroupModel toModel(StudentGroup domain) {

        // Cláusula de guarda.
        if(domain == null) {

            return null;
        }

        // Inserção dos dados da entidade de domínio no modelo de dados.
        var studentGroupModel = new StudentGroupModel();

        studentGroupModel.setId(domain.getId());
        studentGroupModel.setCourse(
            
            courseMapper.toModel(domain.getCourse())
        );
        studentGroupModel.setDiscipline(domain.getDiscipline());
        studentGroupModel.setYear(domain.getYear());
        studentGroupModel.setSemester(domain.getSemester());
        studentGroupModel.setTemporaryPassword(domain.getTemporaryPassword());

        // Retorno do modelo de dados.
        return studentGroupModel;
    }

    /**
     * Método de aplicação:
     * Converte o modelo de dados StudentGroupModel para a entidade de domínio
     * StudentGroup.
     * @param model Modelo de dados StudentGroupModel.
     * @return Entidade de domínio StudentGroup.
     */
    public StudentGroup toDomain(StudentGroupModel model) {

        // Cláusula de guarda.
        if(model == null) {

            return null;
        }

        // Inserção dos dados do modelo de dados na entidade de domínio.
        var studentGroup = new StudentGroup(

            courseMapper.toDomain(model.getCourse()),
            model.getDiscipline(),
            model.getYear(),
            model.getSemester(),
            model.getTemporaryPassword()
        );

        studentGroup.setId(model.getId());

        // Retorno da entidade de domínio.
        return studentGroup;
    }
}