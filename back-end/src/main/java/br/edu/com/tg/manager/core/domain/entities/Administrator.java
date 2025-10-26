package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um usuário com privilégios de ADM no sistema. Esta
 * entidade é responsável por gerenciar os recursos essenciais da
 * aplicação, como professores e cursos.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
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
     * Construtor de domínio:
     * Cria um novo objeto de Administrator e garante que o objeto
     * seja criado num estado válido.
     * @param name Nome do administrador.
     * @param userAccount Conta de usuário do administrador.
     */
    public Administrator(String name, UserAccount userAccount) {
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setUserAccount(userAccount);
    }

    // MÉTODOS GETTERS E SETTERS.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Regra de domínio: administrador não pode conter nome vazio ou nulo.
        if(name == null || name.trim().isEmpty()) {
            throw new DomainException(
                    "O campo nome é obrigatório."
            );
        }
        this.name = name;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        // Regra de domínio: administrador não pode conter conta de usuário nula.
        if(userAccount == null) {
            throw new DomainException(
                    "O campo conta de usuário é obrigatório."
            );
        }
        this.userAccount = userAccount;
    }

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Email da conta de usuário do administrador.
     */
    public String getEmail() {
        return userAccount.getEmail();
    }

    /**
     * Método Get (DELEGAÇÃO).
     * @return Senha criptografada da conta de usuário do administrador.
     */
    public String getPassword() {
        return userAccount.getPassword();
    }
}