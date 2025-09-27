package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.StudentGroupModel;
import org.springframework.stereotype.Component;

/**
 * Mapeador.
 * Estabelece uma ponte entre a entidade de domínio StudentGroup com o modelo de
 * dados StudentGroupModel.
 * 
 * Anotação @Component: indica ao Spring que essa classe deve ser gerenciada,
 * permitindo que ela possa ser injetada.
 */
@Component
public class StudentGroupMapper {

    private final CourseMapper courseMapper;

    public StudentGroupMapper(CourseMapper courseMapper) {

        this.courseMapper = courseMapper;
    }

    /**
     * Mapeia a entidade de domínio StudentGroup para o modelo de dados
     * StudentGroupModel.
     * @param domain Entidade de domínio StudentGroup.
     * @return Modelo de dados StudentGroupModel.
     */
    public StudentGroupModel toModel(StudentGroup domain) {

        if(domain == null) {

            return null;
        }

        var studentGroupModel = new StudentGroupModel();

        studentGroupModel.setId(domain.getId());
        studentGroupModel.setCourse(
            
            courseMapper.toModel(domain.getCourse())
        );
        studentGroupModel.setDiscipline(domain.getDiscipline());
        studentGroupModel.setYear(domain.getYear());
        studentGroupModel.setSemester(domain.getSemester());
        studentGroupModel.setTemporaryPassword(domain.getTemporaryPassword());

        return studentGroupModel;
    }

    /**
     * Mapeia o modelo de dados StudentGroupModel para a entidade de domínio
     * StudentGroup.
     * @param model Modelo de dados StudentGroupModel.
     * @return Entidade de domínio StudentGroup.
     */
    public StudentGroup toDomain(StudentGroupModel model) {

        if(model == null) {

            return null;
        }

        var studentGroup = new StudentGroup(

            courseMapper.toDomain(model.getCourse()),
            model.getDiscipline(),
            model.getYear(),
            model.getSemester(),
            model.getTemporaryPassword()
        );

        studentGroup.setId(model.getId());

        return studentGroup;
    }
}