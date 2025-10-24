package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories.implementation;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers.
        AdministratorMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.
        AdministratorModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories.
        SpringAdministratorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementador de repositório:
 * Implementa o repositório de domínio da entidade Administrator, executando os
 * métodos do contrato com o repositório Spring JPA. Por pertencer à
 * infraestrutura da aplicação, esta classe utiliza da anotação Repository do
 * Spring Boot, permitindo que o framework manipule o banco de dados com os
 * métodos do contrato.
 */
@Repository
public class AdministratorRepositoryImpl implements AdministratorRepository {

    private final SpringAdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta a dependência que, quando AdministradorRepository é instanciado
     * por outra classe, a implementação da interface é assumida por esta classe
     * aqui.
     * @param administratorRepository Repositório Spring JPA do administrador.
     * @param administratorMapper Mapeador de administrador.
     */
    public AdministratorRepositoryImpl(

        SpringAdministratorRepository administratorRepository,
        AdministratorMapper administratorMapper
    ) {

        this.administratorRepository = administratorRepository;
        this.administratorMapper = administratorMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Administrator save(Administrator administrator) {

        /*
         * Converte a entidade de domínio para um modelo de dados, para poder
         * persistir no banco de dados.
         */
        var administratorModel = administratorMapper.toModel(administrator);
        administratorRepository.save(administratorModel);

        /*
         * Ao salvar, retorna uma entidade de domínio utilizando do modelo de
         * dados obtido, para que o ID seja retornado com o valor atualizado.
         */
        return administratorMapper.toDomain(administratorModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Administrator> findById(Long id) {

        // Tenta encontrar o administrador com o id fornecido.
        Optional<AdministratorModel> optionalAdministratorModel =
            administratorRepository.findById(id);

        /*
         * Se achar o administrador, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalAdministratorModel.map(

            this.administratorMapper::toDomain
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Administrator> findByEmail(String email) {

        // Tenta encontrar o administrador com o email fornecido.
        Optional<AdministratorModel> optionalAdministratorModel =
            administratorRepository.findByUserAccountEmail(email);

        /*
         * Se achar o administrador, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalAdministratorModel.map(

            this.administratorMapper::toDomain
        );
    }
}