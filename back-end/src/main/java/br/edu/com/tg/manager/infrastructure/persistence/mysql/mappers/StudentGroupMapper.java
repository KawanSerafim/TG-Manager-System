package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentGroupModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupMapper {
    private final CourseMapper courseMapper;

    public StudentGroupMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

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

        return studentGroupModel;
    }

    public StudentGroup toDomain(StudentGroupModel model) {
        if(model == null) {
            return null;
        }

        var studentGroup = new StudentGroup(
                courseMapper.toDomain(model.getCourse()),
                model.getDiscipline(),
                model.getYear(),
                model.getSemester()
        );

        studentGroup.setId(model.getId());

        return studentGroup;
    }
}