package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

/*
 * Anotações do JPA:
 *
 * - Anotação @Entity:
 * Indica ao Spring JPA que esta classe é uma entidade.
 *
 * - Anotação @Table:
 * Indica ao Spring JPA que esta classe é uma tabela do banco de dados. Dentro
 * dos parênteses, o valor atribuído a variável 'name' será o nome da tabela.
 *
 * - Anotação @Id:
 * Indica ao Spring JPA que a variável determina o ID da tabela.
 *
 * - Anotação @GeneratedValue:
 * Indica ao Spring JPA como o valor da variável será gerada.
 *
 * - Anotação @Column:
 * Indica ao Spring JPA que esta variável será uma coluna da
 * tabela. Dentro dos parênteses, o valor booleano 'nullable' determina se a
 * coluna poderá conter valores nulos ou não. E na 'unique', determina se o
 * valor da coluna pode ou não se repetir.
 *
 * Anotação @Embedded:
 * Indica ao Spring JPA que os campos de uma classe embutível (@Embeddable)
 * devem ser incluídos como colunas na tabela desta entidade. Em vez de criar
 * uma tabela separada para o objeto embutido, os seus campos são "achatados" e
 * persistidos diretamente na tabela da entidade principal.
 */

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
public class AdministratorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private UserAccountModel userAccount;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public AdministratorModel() {}

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

        this.name = name;
    }

    /**
     * Método Get.
     * @return Conta de usuário do administrador.
     */
    public UserAccountModel getUserAccount() {

        return userAccount;
    }

    /**
     * Método Set.
     * @param userAccount Conta de usuário fornecida.
     */
    public void setUserAccount(UserAccountModel userAccount) {

        this.userAccount = userAccount;
    }
}