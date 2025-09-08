package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

/**
 * Entidade de domínio que representa a turma.
 */

public class StudentGroup {

    private Long id;
    private String courseName;

    /**
     * Construtor vazio.
     */
    public StudentGroup(){}

    /**
     * Construtor para criar uma nova turma.
     * @param courseName Nome do curso da turma.
     */
    public StudentGroup(String courseName) {

        if(courseName == null || courseName.trim().isEmpty()) {

            throw new DomainException("O nome do grupo não pode ser vazio.");
        }

        this.courseName = courseName;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCourseName() {

        return this.courseName;   
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }
}