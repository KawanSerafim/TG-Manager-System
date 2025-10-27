package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .implementation;

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

@Repository
public class AdministratorRepositoryImpl implements AdministratorRepository {
    private final SpringAdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

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
        var administratorModel = administratorMapper.toModel(administrator);
        administratorRepository.save(administratorModel);

        return administratorMapper.toDomain(administratorModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Administrator> findById(Long id) {
        Optional<AdministratorModel> optionalAdministratorModel =
                administratorRepository.findById(id);

        return optionalAdministratorModel.map(
                this.administratorMapper::toDomain
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Administrator> findByEmail(String email) {
        Optional<AdministratorModel> optionalAdministratorModel =
                administratorRepository.findByUserAccountEmail(email);

        return optionalAdministratorModel.map(
                this.administratorMapper::toDomain
        );
    }
}