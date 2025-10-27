package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa a conta de usuário que as entidades Professor, Student e
 * Administrator terão ao serem cadastradas no sistema. Esta entidade é
 * responsável pela consistência dos dados de autenticação (email e senha) e
 * pelo ciclo de vida da conta através do seu status de verificação.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public class UserAccount {
    private String email;
    private String password;
    private UserAccountStatus status;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public UserAccount() {}

    /**
     * Construtor de domínio:
     * Cria um novo objeto de UserAccount, garantindo que o email e senha sejam
     * válidos e definindo o status inicial da conta para PENDING_VERIFICATION.
     * @param email Email do usuário.
     * @param password Senha criptografada do usuário.
     */
    public UserAccount(String email, String password) {
        // Define um status inicial a conta, que exige validação de email.
        this.status = UserAccountStatus.PENDING_VERIFICATION;

        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setEmail(email);
        this.setPassword(password);
    }

    /**
     * Método de domínio:
     * Valida o formato e a presença de um endereço de email.
     * @param email Email do usuário.
     * @throws DomainException Lançada se o email for nulo, vazio ou tiver
     * formato inválido.
     */
    public static void validateEmailFormat(String email) throws DomainException {
        // Regra de domínio: o campo email é obrigatório.
        if(email == null || email.trim().isEmpty()) {
            throw new DomainException(
                "O campo email é obrigatório."
            );
        }

        // Regra de domínio: email deve conter o caractere '@'.
        if(!(email.contains("@"))) {
            throw new DomainException(
                "O formato do campo email é inválido."
            );
        }
    }

    // MÉTODOS GETTERS E SETTERS.

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Garante que o email é validado antes de ser atribuído.
        validateEmailFormat(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccountStatus getStatus() {
        return status;
    }

    public void setStatus(UserAccountStatus status) {
        // Regra de domínio: o campo status é obrigatório.
        if(status == null) {
            throw new DomainException(
                    "O campo status é obrigatório."
            );
        }
        this.status = status;
    }
}