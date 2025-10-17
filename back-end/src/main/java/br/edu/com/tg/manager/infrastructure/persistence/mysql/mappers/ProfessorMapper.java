package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .ProfessorModel;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component:
 * Indica ao Spring que esta classe deve ser gerenciada pelo framework.
 */

/**
 * Mapeador:
 * Estabelece uma ponte entre a entidade de domínio Professor com o modelo de
 * dados ProfessorModel.
 */
@Component
public class ProfessorMapper {

    private final UserAccountMapper userAccountMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta diretamente a dependência de que, ao criar ProfessorMapper,
     * UserAccountMapper também é criado.
     * @param userAccountMapper Mapeador de conta de usuário.
     */
    public ProfessorMapper(UserAccountMapper userAccountMapper) {

        this.userAccountMapper = userAccountMapper;
    }
    
    /**
     * Método de aplicação:
     * Converte a entidade de domínio Professor para o modelo de dados
     * ProfessorModel.
     * @param domain Entidade de domínio Professor.
     * @return Modelo de dados ProfessorModel.
     */
    public ProfessorModel toModel(Professor domain) {

        // Cláusula de guarda.
        if(domain == null) {

            return null;
        }

        // Inserção dos dados da entidade de domínio no modelo de dados.
        var professorModel = new ProfessorModel();

        professorModel.setId(domain.getId());
        professorModel.setName(domain.getName());
        professorModel.setRegistration(domain.getRegistration());
        professorModel.setUserAccount(

            userAccountMapper.toModel(domain.getUserAccount())
        );
        professorModel.setRole(domain.getRole());

        // Retorno do modelo de dados.
        return professorModel;
    }

    /**
     * Método de aplicação:
     * Converte o modelo de dados ProfessorModel para a entidade de domínio
     * Professor.
     * @param model Modelo de dados ProfessorModel.
     * @return Entidade de domínio Professor.
     */
    public Professor toDomain(ProfessorModel model) {

        // Cláusula de guarda.
        if(model == null) {

            return null;
        }

        // Inserção dos dados do modelo de dados na entidade de domínio.
        var professor = new Professor(

            model.getName(),
            model.getRegistration(),
            userAccountMapper.toDomain(model.getUserAccount()),
            model.getRole()
        );

        professor.setId(model.getId());

        // Retorno da entidade de domínio.
        return professor;
    }
}