package br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.ProfessorModel;
import org.springframework.stereotype.Component;

/**
 * Mapeador.
 * Estabelece uma ponte entre a entidade de domínio Professor com o modelo de
 * dados ProfessorModel.
 * 
 * Anotação @Component: indica ao Spring que essa classe deve ser gerenciada,
 * permitindo que ela possa ser injetada.
 */
@Component
public class ProfessorMapper {
    
    /**
     * Mapeia a entidade de domínio Professor para o modelo de dados
     * ProfessorModel.
     * @param domain Entidade de domínio Professor.
     * @return Modelo de dados ProfessorModel.
     */
    public ProfessorModel toModel(Professor domain) {

        if(domain == null) {

            return null;
        }

        var professorModel = new ProfessorModel();

        professorModel.setId(domain.getId());
        professorModel.setName(domain.getName());
        professorModel.setRegistration(domain.getRegistration());
        professorModel.setEmail(domain.getEmail());
        professorModel.setHashedPassword(domain.getHashedPassword());
        professorModel.setRole(domain.getRole());

        return professorModel;
    }

    /**
     * Mapeia o modelo de dados ProfessorModel para a entidade de domínio
     * Professor.
     * @param model Modelo de dados ProfessorModel.
     * @return Entidade de domínio Professor.
     */
    public Professor toDomain(ProfessorModel model) {

        if(model == null) {

            return null;
        }

        var professor = new Professor(

            model.getName(),
            model.getRegistration(),
            model.getEmail(),
            model.getHashedPassword(),
            model.getRole()
        );

        professor.setId(model.getId());

        return professor;
    }
}