package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Modelo de dados.
 * Representa a entidade de domínio Professor, sendo a sua versão no banco de
 * dados, que será manipulado pelo JPA.
 * A classe, por fazer parte da infrastructure, permite influência de
 * ferramentas externas.
 * 
 * -------------------
 * 
 * Anotações do JPA:
 * 
 * - Anotação @Entity: indica ao JPA que esta classe é uma entidade.
 * 
 * - Anotação @Table: indica ao JPA que esta classe é uma tabela do banco
 * de dados. Dentro do parênteses, o valor atruído a variável 'name' será o
 * nome da tabela.
 * 
 * - Anotação @Id: indica ao JPA que a variável determina o ID da tabela.
 * 
 * - Anotação @GeneratedValue: indica ao JPA como o valor da variável
 * será gerada.
 * 
 * - Anotação @Column: indica ao JPA que esta variável será uma coluna da
 * tabela. Dentro do parênteses, o valor booleano a variável 'nullable' 
 * determina se a coluna poderá conter valores nulos ou não. E na
 * 'unique', determina se o valor da coluna não pode se repetir.
 * 
 * Anotação @Enumerated: indica ao JPA que o campo é do tipo Enum e deve
 * ser persistido no banco de dados. Porém, por padrão, ele é organizado através
 * de uma enumeração numérica, o que gera um risco em potencial caso os números
 * sejam alterados futuramente. Logo, o tipo (EnumType) deve ser determinado
 * como um String.
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
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfessorRole role;

    public ProfessorModel() {}

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
     
        this.name = name;
    }

    public String getRegistration() {
     
        return registration;
    }

    public void setRegistration(String registration) {
     
        this.registration = registration;
    }

    public String getEmail() {
     
        return email;
    }

    public void setEmail(String email) {
     
        this.email = email;
    }

    public String getHashedPassword() {
     
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
     
        this.hashedPassword = hashedPassword;
    }

    public ProfessorRole getRole() {
     
        return role;
    }

    public void setRole(ProfessorRole role) {
     
        this.role = role;
    }
}