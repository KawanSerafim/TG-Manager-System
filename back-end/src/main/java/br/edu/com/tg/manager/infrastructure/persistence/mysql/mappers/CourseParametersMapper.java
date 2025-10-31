package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.CourseParameters;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .CourseParametersModel;
import org.springframework.stereotype.Component;

@Component
public class CourseParametersMapper {
    public CourseParametersModel toModel(CourseParameters domain) {
        if(domain == null) {
            return null;
        }

        var model = new CourseParametersModel();
        model.setAvailableShifts(domain.getAvailableShifts());
        model.setAvailableDisciplines(domain.getAvailableDisciplines());

        return model;
    }

    public CourseParameters toDomain(CourseParametersModel model) {
        if(model == null) {
            return null;
        }

        var domain = new CourseParameters();
        domain.setAvailableShifts(model.getAvailableShifts());
        domain.setAvailableDisciplines(model.getAvailableDisciplines());

        return domain;
    }
}