package br.edu.com.tg.manager.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Modelo de dados.
 * Um espelho da entidade de domínio que representa o curso, e que será
 * manipulado pelo JPA.
 * A classe, por fazer parte da infrastructure, adota as anotações do 
 * JPA do Spring Boot.
 *
 * Anotação @Entity: indica ao framework que esta classe é uma entidade.
 * Anotação @Table: indica ao framework que esta classe é uma tabela do banco
 * de dados. Dentro do parênteses, o valor atruído a variável 'name' será o
 * nome da tabela.
 * 
 * Anotação @Id: indica ao framework que esta variável determina o ID da tabela.
 *
 * Anotação @GeneratedValue: indica ao framework como o valor da variável
 * será gerada.
 * 
 * Anotação @Column: indica ao framework que esta variável será uma coluna
 * da tabela. Dentro do parênteses, o valor booleano a variável 'nullable' 
 * determina se a coluna poderá conter valores nulos ou não. E na
 * 'unique', determina se o valor da coluna não pode se repetir.
 */
@Entity
@Table(name = "courses")
public class CourseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /* Construtor vazio, necessário para o JPA. */
    public CourseData(){}

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
}