package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

public class Course {

    private Long id;
    private String name;

    public Course(){}
    public Course(String name) {
    
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException("O nome do curso não pode estar vazio.");
        }

        this.name = name;
    }

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
    
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException("O nome do curso não pode estar vazio.");
        }

        this.name = name;
    }   
}