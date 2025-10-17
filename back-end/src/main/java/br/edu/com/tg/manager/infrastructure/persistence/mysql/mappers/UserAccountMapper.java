package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.
        UserAccountModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component:
 * Indica ao Spring que esta classe deve ser gerenciada pelo framework.
 */

/**
 * Mapeador:
 * Estabelece uma ponte entre a entidade de domínio UserAccount com o modelo de
 * dados UserAccountModel.
 */
@Component
public class UserAccountMapper {

    public UserAccountModel toModel(UserAccount domain) {

        // Cláusula de guarda.
        if(domain == null) {

            return null;
        }

        // Inserção dos dados da entidade de domínio no modelo de dados.
        var userAccountModel = new UserAccountModel();

        userAccountModel.setEmail(domain.getEmail());
        userAccountModel.setPassword(domain.getPassword());

        // Retorno do modelo de dados.
        return userAccountModel;
    }

    public UserAccount toDomain(UserAccountModel model) {

        // Cláusula de guarda.
        if(model == null || model.getEmail() == null ) {

            return null;
        }

        /*
         * Inserção dos dados do modelo de dados na entidade de domínio e
         * retorno da entidade de domínio.
         */
        return new UserAccount(

            model.getEmail(),
            model.getPassword()
        );
    }
}