package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um usuário com privilégios de ADM no sistema. Ela quem possuirá
 * a responsabilidade de gerenciar os recursos essenciais da aplicação, como
 * professores e cursos. Por pertencer ao núcleo (core) da aplicação, esta
 * classe é independente de frameworks ou bibliotecas externas, sendo, portanto,
 * considerada uma classe pura.
 */
public class Administrator {

    private Long id;
    private String name;
    private UserAccount userAccount;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public Administrator() {}

    /**
     * Construtor de negócio:
     * Cria um novo objeto de Administrator e garante que o objeto seja criado
     * num estado válido.
     * @param name Nome do administrador.
     * @param userAccount Conta de usuário do administrador.
     */
    public Administrator(String name, UserAccount userAccount) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setName(name);
        this.setUserAccount(userAccount);
    }

    /**
     * Método Get.
     * @return ID do administrador.
     */
    public Long getId() {

        return id;
    }

    /**
     * Método Set.
     * @param id ID fornecido.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * Método Get.
     * @return Nome do administrador.
     */
    public String getName() {

        return name;
    }

    /**
     * Método Set.
     * @param name Nome fornecido.
     */
    public void setName(String name) {

        // Regra de negócio: administrador não pode conter nome vazio ou nulo.
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }
        this.name = name;
    }

    /**
     * Método Get.
     * @return Conta de usuário do administrador.
     */
    public UserAccount getUserAccount() {

        return userAccount;
    }

    /**
     * Método Set.
     * @param userAccount Conta de usuário fornecida.
     */
    public void setUserAccount(UserAccount userAccount) {

        /*
         * Regra de negócio: administrador não pode conter conta de usuário
         * vazia.
         */
        if(userAccount == null) {

            throw new DomainException(

                "O campo conta de usuário é obrigatório."
            );
        }
        this.userAccount = userAccount;
    }
}