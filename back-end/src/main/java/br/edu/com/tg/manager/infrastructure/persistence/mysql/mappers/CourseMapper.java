package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.CourseModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component: indica ao Spring que esta classe deve ser gerenciada
 * pelo framework.
 */

/**
 * Mapeador:
 * Estabelece uma ponte entre a entidade de domínio Course com o modelo de
 * dados CourseModel.
 */
@Component
public class CourseMapper {

    private final ProfessorMapper professorMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta diretamente a dependência de que, ao criar CourseMapper,
     * ProfessorMapper também é criado.
     * @param professorMapper Mapeador de professor.
     */
    public CourseMapper(ProfessorMapper professorMapper) {

        this.professorMapper = professorMapper;
    }

    /**
     * Método de aplicação:
     * Converte a entidade de domínio Course para o modelo de dados CourseModel.
     * @param domain Entidade de domínio Course.
     * @return Modelo de dados CourseModel.
     */
    public CourseModel toModel(Course domain) {

        // Cláusula de guarda.
        if(domain == null) {

            return null;
        }

        // Inserção dos dados da entidade de domínio no modelo de dados.
        var courseModel = new CourseModel();
        
        courseModel.setId(domain.getId());
        courseModel.setName(domain.getName());
        courseModel.setShift(domain.getShift());
        
        courseModel.setTgCoordinator(
            
            professorMapper.toModel(domain.getTgCoordinator())
        );

        courseModel.setCourseCoordinator(
            
            professorMapper.toModel(domain.getCourseCoordinator())
        );

        // Retorno do modelo de dados.
        return courseModel;
    }

    /**
     * Método de aplicação:
     * Converte o modelo de dados CourseModel para a entidade de domínio Course.
     * @param model Modelo de dados CourseModel.
     * @return Entidade de domínio Course.
     */
    public Course toDomain(CourseModel model) {

        // Cláusula de guarda.
        if(model == null) {

            return null;
        }

        // Inserção dos dados do modelo de dados na entidade de domínio.
        var course = new Course(

            model.getName(),
            model.getShift(),
            professorMapper.toDomain(model.getTgCoordinator()),
            professorMapper.toDomain(model.getCourseCoordinator())
        );

        course.setId(model.getId());

        // Retorno da entidade de domínio.
        return course;
    }
}