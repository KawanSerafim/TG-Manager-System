package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Modelo de dados embutível:
 * Representa os dados da conta de um usuário (email e senha).
 *
 * Anotação @Embeddable:
 * Marca esta classe como um componente que pode ser embutido em outras
 * entidades. Esta classe NÃO terá uma tabela própria no banco de dados.
 * Seus campos serão adicionados como colunas na tabela da entidade que a usar.
 */
@Embeddable
public class UserAccountModel {

    /*
     * nullable = true: Porque um aluno em pré-cadastro ainda não tem email/senha.
     * unique = true: Garante que não existam duas contas com o mesmo email.
     */
    @Column(name = "email", nullable = true, unique = true)
    private String email;

    /*
     * nullable = true: Pelo mesmo motivo do email.
     */
    @Column(name = "password", nullable = true)
    private String password;

    /**
     * Construtor vazio:
     * Necessário para o funcionamento do framework de persistência (JPA).
     */
    public UserAccountModel() {}

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