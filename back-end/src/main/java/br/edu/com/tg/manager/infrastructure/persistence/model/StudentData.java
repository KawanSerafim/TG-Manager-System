package br.edu.com.tg.manager.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Modelo de dados.
 * Um espelho da entidade de domínio que representa
 * o curso, e que será manipulado pelo JPA.
 * A classe, por fazer parte da infrastructure, adota
 * as anotações do JPA do Spring Boot.
 *
 * Anotação @Entity: indica ao framework que esta classe é
 * uma entidade.
 * Anotação @Table: indica ao framework que esta classe é
 * uma tabela do banco de dados. Dentro do parênteses, 
 * o valor atruído a variável 'name' será o nome da tabela.
 * 
 * Anotação @Id: indica ao framework que esta variável determina 
 * o ID da tabela.
 *
 * Anotação @GeneratedValue: indica ao framework como o valor
 * da variável será gerada.
 * 
 * Anotação @Column: indica ao framework que esta
 * variável será uma coluna da tabela. Dentro do parênteses, 
 * o valor booleano a variável 'nullable'determina se a coluna 
 * poderá conter valores nulos ou não. E na 'unique', determina 
 * se o valor da coluna não pode se repetir.
 * 
 * Anotação @ManyToOne: indica ao framework que esta classe
 * faz parte de uma relação de muitos para um, onde a variável
 * representa o um, e essa classe o muitos. Dentro do parênteses,
 * o 'fetch' determina ao hibernate o método de busca aos dados. E
 * o optional indica se a relação é ou não obrigatória.
 * 
 * Anotação @JoinColumn: indica ao framework aonde alocar o ID
 * da outra tabela relacionada. A variável 'name' determina o nome
 * da coluna em que o ID será salvo. 
 */
@Entity
@Table(name = "students")
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String registration;
    
    @Column(unique = true)
    private String email;
    
    @Column(length = 255)
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_group_id", nullable = false)
    private StudentGroupData studentGroup;

    /* Construtor vazio, necessário para o JPA. */
    public StudentData(){}

    /* Getters e Setters. */

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

    public String getPassword() {
     
        return password;
    }

    public void setPassword(String password) {
     
        this.password = password;
    }

    public StudentGroupData getStudentGroup() {
     
        return studentGroup;
    }

    public void setStudentGroup(StudentGroupData studentGroup) {
     
        this.studentGroup = studentGroup;
    }
}