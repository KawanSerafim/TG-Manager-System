package br.edu.com.tg.manager.core.ports.repositories;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import java.util.Optional;

/**
 * Repositório de domínio:
 * Define um contrato abstrato para a camada de infraestrutura implementar
 * a persistência de dados da entidade de domínio Administrator.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface AdministratorRepository {

    /**
     * Método de contrato de domínio:
     * Persiste o objeto de um administrador no banco de dados.
     * @return Professor.
     */
    Administrator save(Administrator administrator);

    /**
     * Método de contrato de domínio:
     * Busca um administrador pelo seu ID.
     * @param id ID do administrador.
     * @return Optional vazio ou Administrator.
     */
    Optional<Administrator> findById(Long id);

    /**
     * Método de contrato de domínio:
     * Busca um administrador pelo seu email.
     * @param email Email do administrador.
     * @return Optional vazio ou Administrator.
     */
    Optional<Administrator> findByEmail(String email);
}