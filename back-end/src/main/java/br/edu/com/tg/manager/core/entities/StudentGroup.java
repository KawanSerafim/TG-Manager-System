package br.edu.com.tg.manager.core.entities;

import java.time.Year;

import br.edu.com.tg.manager.core.exceptions.DomainException;

/**
 * Entidade de domínio.
 * Representa uma turma de TG de um determinado curso, ano e semestre.
 * A classe, por fazer parte do core, é pura.
 */
public class StudentGroup {

    private Long id;
    private Course course;
    private Discipline discipline;
    private Integer year;
    private Integer semester;
    private String temporaryPassword;

    /**
     * Construtor vazio.
     * Necessário para frameworks de persistência.
     */
    public StudentGroup() {}

    public StudentGroup(

        Course course,
        Discipline discipline,
        Integer year,
        Integer semester,
        String temporaryPassword
    ) {
        
        this.setCourse(course);
        this.setDiscipline(discipline);
        this.setYear(year);
        this.setSemester(semester);
        this.setTemporaryPassword(temporaryPassword);
    }

    public Long getId() {
       
        return id;
    }

    public void setId(Long id) {
       
        this.id = id;
    }

    public Course getCourse() {
       
        return course;
    }

    public void setCourse(Course course) {
        
        if(course == null) {

            throw new DomainException(

                "O campo curso é obrigatório."
            );
        }

        this.course = course;
    }

    public Discipline getDiscipline() {
       
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        
        if(discipline == null) {

            throw new DomainException(

                "O campo disciplina é obrigatório."
            );
        }

        this.discipline = discipline;
    }

    public Integer getYear() {
       
        return year;
    }

    public void setYear(Integer year) {
        
        if(year == null) {

            throw new DomainException(

                "O campo ano é obrigatório."
            );
        }

        int currentYear = Year.now().getValue();

        if(!(year.equals(currentYear))) {

            throw new DomainException(

                "O valor do campo ano é obrigatório."
            );
        }

        this.year = year;
    }

    public Integer getSemester() {
     
        return semester;
    }

    public void setSemester(Integer semester) {
        
        if(semester == null) {

            throw new DomainException(

                "O campo semestre é obrigatório."
            );
        }

        if(semester != 1 && semester != 2) {

            throw new DomainException(

                "O valor do campo semestre é obrigatório."
            );
        }

        this.semester = semester;
    }

    public String getTemporaryPassword() {
     
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        
        if(temporaryPassword == null || temporaryPassword.trim().isEmpty()) {

            throw new DomainException(

                "O campo senha temporária é obrigatório."
            );
        }

        this.temporaryPassword = temporaryPassword;
    }
}