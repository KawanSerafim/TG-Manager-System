package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

/**
 * Entidade de domínio que representa a turma.
 */

public class StudentGroup {

    private Long id;
    private Integer year;
    private Integer semester;
    private Course course;

    /**
     * Construtor vazio.
     */
    public StudentGroup(){}

    /**
     * Construtor para criar uma nova turma.
     * @param course instância do curso.
     * @param year ano da turma.
     * @param semester semestre da turma.
     */
    public StudentGroup(Course course, Integer year, Integer semester) {

        if(course == null) {

            throw new DomainException("A turma deve estar associada a um curso.");
        }

        if(year == null || year < 2025) {

            throw new DomainException("Ano da turma inválido.");
        }

        if(semester == null || (semester != 1 && semester != 2)) {

            throw new DomainException("Semestre da turma inválido.");
        }

        this.course = course;
        this.year = year;
        this.semester = semester;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Integer getYear() {
     
        return year;
    }

    public void setYear(Integer year) {
        
        if(year == null || year < 2025) {

            throw new DomainException("Ano da turma inválido.");
        }

        this.year = year;
    }

    public Integer getSemester() {
        
        if(semester == null || (semester != 1 && semester != 2)) {

            throw new DomainException("Semestre da turma inválido.");
        }

        return semester;
    }

    public void setSemester(Integer semester) {
     
        this.semester = semester;
    }

    public Course getCourse() {
     
        return course;
    }

    public void setCourse(Course course) {
        
        if(course == null) {

            throw new DomainException("A turma deve estar associada a um curso.");
        }

        this.course = course;
    }   
}