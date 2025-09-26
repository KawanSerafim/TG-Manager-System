package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private final ProfessorMapper professorMapper;

    public CourseMapper(ProfessorMapper professorMapper) {

        this.professorMapper = professorMapper;
    }

    /**
     * Mapeia a entidade de domínio Course para o modelo de dados CourseModel.
     * @param domain Entidade de domínio Course.
     * @return Modelo de dados CourseModel.
     */
    public CourseModel toModel(Course domain) {

        if(domain == null) {

            return null;
        }

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

        return courseModel;
    }

    /**
     * Mapeia o modelo de dados CourseModel para a entidade de domínio Course.
     * @param model Modelo de dados CourseModel.
     * @return Entidade de domínio Course.
     */
    public Course toDomain(CourseModel model) {

        if(model == null) {

            return null;
        }

        var course = new Course(

            model.getName(),
            model.getShift(),
            professorMapper.toDomain(model.getTgCoordinator()),
            professorMapper.toDomain(model.getCourseCoordinator())
        );

        course.setId(model.getId());

        return course;
    }
}