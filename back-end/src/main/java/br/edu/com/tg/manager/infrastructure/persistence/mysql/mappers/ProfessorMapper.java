package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .ProfessorModel;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {
    private final UserAccountMapper userAccountMapper;

    public ProfessorMapper(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    public ProfessorModel toModel(Professor domain) {
        if(domain == null) {
            return null;
        }

        var professorModel = new ProfessorModel();
        professorModel.setId(domain.getId());
        professorModel.setName(domain.getName());
        professorModel.setRegistration(domain.getRegistration());
        professorModel.setUserAccount(
                userAccountMapper.toModel(domain.getUserAccount())
        );
        professorModel.setRole(domain.getRole());

        return professorModel;
    }

    public Professor toDomain(ProfessorModel model) {
        if(model == null) {
            return null;
        }

        var professor = new Professor(
                model.getName(),
                model.getRegistration(),
                userAccountMapper.toDomain(model.getUserAccount()),
                model.getRole()
        );

        professor.setId(model.getId());

        return professor;
    }
}