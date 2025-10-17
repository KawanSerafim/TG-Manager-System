package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa a conta de usuário que as entidades terão ao serem cadastradas no
 * sistema. Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo, portanto,
 * considerada uma classe pura.
 */
public class UserAccount {

    private String email;
    private String password;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public UserAccount() {}

    /**
     * Construtor de negócio:
     * Cria um novo objeto de UserAccount e garante que o objeto seja criado num
     * estado válido.
     * @param email Email do usuário.
     * @param password Senha do usuário.
     */
    public  UserAccount(String email, String password) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setEmail(email);
        this.setPassword(password);
    }

    /**
     * Método Get.
     * @return Email do usuário.
     */
    public String getEmail() {

        return email;
    }

    /**
     * Método Set.
     * @param email Email fornecido.
     */
    public void setEmail(String email) {

        // Regra de negócio: professor não pode conter email vazio ou nulo.
        if(email == null || email.trim().isEmpty()) {

            throw new DomainException(

                    "O campo email é obrigatório."
            );
        }

        // Regra de negócio: professor não pode conter email inváldio.
        if(!(email.contains("@"))) {

            throw new DomainException(

                    "O formato do campo email é inválido."
            );
        }

        this.email = email;
    }

    /**
     * Método Get.
     * @return Senha do usuário.
     */
    public String getPassword() {

        return password;
    }

    /**
     * Método Set.
     * @param password Senha fornecida.
     */
    public void setPassword(String password) {

        this.password = password;
    }
}