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