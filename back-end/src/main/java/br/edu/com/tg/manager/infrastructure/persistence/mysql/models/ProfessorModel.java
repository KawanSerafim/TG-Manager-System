package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
 *
 * - Anotação @Enumerated:
 * Indica ao Spring JPA que o campo é do tipo Enum e
 * deve ser persistido no banco de dados. Porém, por padrão, ele é organizado
 * mediante uma enumeração numérica, o que gera um risco em potencial caso os
 * números sejam alterados futuramente. Logo, o tipo (EnumType) deve ser
 * determinado como um String.
 */

/**
 * Modelo de dados:
 * Determina um modelo da entidade de domínio Professor, que será manipulado
 * pelo Spring JPA. Por pertencer à infraestrutura da aplicação, esta classe
 * utiliza das anotações persistence, as quais permitem ao framework manipular
 * os dados no SGBD.
 */
@Entity
@Table(name = "professors")
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String registration;

    @Embedded
    private UserAccountModel userAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfessorRole role;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public ProfessorModel() {}

    /**
     * Método Get.
     * @return ID salvo no modelo de dados do professor.
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
     * @return Nome salvo no modelo de dados do professor.
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
     * @return Matrícula salva no modelo de dados do professor.
     */
    public String getRegistration() {
     
        return registration;
    }

    /**
     * Método Set.
     * @param registration Matrícula fornecida.
     */
    public void setRegistration(String registration) {
     
        this.registration = registration;
    }

    /**
     * Método Get.
     * @return Email salvo no modelo de dados do professor.
     */
    public String getEmail() {
     
        return userAccount.getEmail();
    }

    /**
     * Método Set.
     * @param email Email fornecido.
     */
    public void setEmail(String email) {
     
        this.userAccount.setEmail(email);
    }

    /**
     * Método Get.
     * @return Senha salva no modelo de dados do professor.
     */
    public String getPassword() {
     
        return userAccount.getPassword();
    }

    /**
     * Método Set.
     * @param password Senha fornecida.
     */
    public void setPassword(String password) {
     
        this.userAccount.setPassword(password);
    }

    /**
     * Método Get.
     * @return Cargo salvo no modelo de dados do professor.
     */
    public ProfessorRole getRole() {
     
        return role;
    }

    /**
     * Método Set.
     * @param role Cargo fornecido.
     */
    public void setRole(ProfessorRole role) {
     
        this.role = role;
    }
}