package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .AdministratorModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component:
 * indica ao Spring que esta classe deve ser gerenciada pelo framework.
 */

@Component
public class AdministratorMapper {

    private final UserAccountMapper userAccountMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta diretamente a dependência de que, ao criar AdministratorMapper,
     * UserAccountMapper também é criada.
     * @param userAccountMapper Mapeador da conta de usuário.
     */
    public AdministratorMapper(UserAccountMapper userAccountMapper) {

        this.userAccountMapper = userAccountMapper;
    }

    /**
     * Método de aplicação:
     * Converte a entidade de domínio Administrator para o modelo de dados
     * AdministratorModel.
     * @param domain Entidade de domínio Administrator.
     * @return Modelo de dados AdministratorModel.
     */
    public AdministratorModel toModel(Administrator domain) {

        // Cláusula de guarda.
        if(domain == null) return null;

        // Inserção dos dados da entidade de domínio no modelo de dados.
        var administratorModel = new AdministratorModel();

        administratorModel.setId(domain.getId());
        administratorModel.setName(domain.getName());
        administratorModel.setUserAccount(

            userAccountMapper.toModel(domain.getUserAccount())
        );

        // Retorno do modelo de dados.
        return administratorModel;
    }

    /**
     * Método de aplicação:
     * Converte o modelo de dados AdministratorModel para a entidade de domínio
     * Administrator.
     * @param model Modelo de dados AdministratorModel.
     * @return Entidade de domínio Administrator.
     */
    public Administrator toDomain(AdministratorModel model) {

        // Cláusula de guarda.
        if(model == null) return null;

        var administrator = new Administrator(

            model.getName(),
            userAccountMapper.toDomain(model.getUserAccount())
        );

        // Inserção dos dados do modelo de dados na entidade de domínio.
        administrator.setId(model.getId());

        // Retorno da entidade de domínio.
        return administrator;
    }
}