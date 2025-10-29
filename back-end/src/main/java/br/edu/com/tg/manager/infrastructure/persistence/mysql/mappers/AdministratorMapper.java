package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .AdministratorModel;
import org.springframework.stereotype.Component;

@Component
public class AdministratorMapper {
    private final UserAccountMapper userAccountMapper;

    public AdministratorMapper(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    public AdministratorModel toModel(Administrator domain) {
        if(domain == null) return null;

        var administratorModel = new AdministratorModel();
        administratorModel.setId(domain.getId());
        administratorModel.setName(domain.getName());
        administratorModel.setUserAccount(
                userAccountMapper.toModel(domain.getUserAccount())
        );

        return administratorModel;
    }

    public Administrator toDomain(AdministratorModel model) {
        if(model == null) return null;

        var administrator = new Administrator(
                model.getName(),
                userAccountMapper.toDomain(model.getUserAccount())
        );

        administrator.setId(model.getId());

        return administrator;
    }
}