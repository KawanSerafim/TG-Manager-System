package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

/**
 * Entidade de domínio.
 * Representa uma classe de domínio que representa o curso da FATEC.
 * A classe, por fazer parte do core, é pura.
 */
public class Course {

    private Long id;
    private String name;
    
    /* Construtor vazio. Necessário para os Mappers. */
    public Course(){}

    /**
     * Construtor para construir um curso.
     * @param name A varíavel que representa o nome do curso.
     */
    public Course(String name) {
        
        this.setName(name);
    }

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
        
        /* Regra de negócio: nome de curso não pode estar vazio. */
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException("O nome do curso é obrigatório.");
        }

        this.name = name;
    }   
}