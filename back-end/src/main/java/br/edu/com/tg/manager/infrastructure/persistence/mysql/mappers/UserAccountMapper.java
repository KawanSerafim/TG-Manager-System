package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.
        UserAccountModel;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    public UserAccountModel toModel(UserAccount domain) {
        if(domain == null) {
            return null;
        }

        var userAccountModel = new UserAccountModel();
        userAccountModel.setEmail(domain.getEmail());
        userAccountModel.setPassword(domain.getPassword());
        userAccountModel.setStatus(domain.getStatus());

        return userAccountModel;
    }

    public UserAccount toDomain(UserAccountModel model) {
        if(model == null || model.getEmail() == null ) {
            return null;
        }

        var userAccount = new UserAccount();
        userAccount.setEmail(model.getEmail());
        userAccount.setPassword(model.getPassword());
        userAccount.setStatus(model.getStatus());

        return userAccount;
    }
}