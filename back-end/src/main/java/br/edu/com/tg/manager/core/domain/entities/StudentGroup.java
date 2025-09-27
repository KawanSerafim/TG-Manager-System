package br.edu.com.tg.manager.core.domain.entities;

import java.time.Year;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

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
        
        /* Regra de negócio: turma não pode conter curso nulo. */
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
        
        /* Regra de negócio: turma não pode conter disciplina nula. */
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
        
        /* Regra de negócio: turma não pode conter ano nulo. */
        if(year == null) {

            throw new DomainException(

                "O campo ano é obrigatório."
            );
        }

        int currentYear = Year.now().getValue();

        /* Regra de negócio: turma não pode conter ano inválido. */
        if(!(year.equals(currentYear))) {

            throw new DomainException(

                "O ano não pode ser diferente do atual."
            );
        }

        this.year = year;
    }

    public Integer getSemester() {
     
        return semester;
    }

    public void setSemester(Integer semester) {
        
        /* Regra de negócio: turma não pode conter semestre nulo. */
        if(semester == null) {

            throw new DomainException(

                "O campo semestre é obrigatório."
            );
        }

        /* Regra de negócio: turma não pode conter semestre inválido. */
        if(semester != 1 && semester != 2) {

            throw new DomainException(

                "O semestre foge do padrão: 1 ou 2."
            );
        }

        this.semester = semester;
    }

    public String getTemporaryPassword() {
     
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        
        /* Regra de negócio: turma não pode conter senha temporária vazia ou
         * nula.
         */
        if(temporaryPassword == null || temporaryPassword.trim().isEmpty()) {

            throw new DomainException(

                "O campo senha temporária é obrigatório."
            );
        }

        this.temporaryPassword = temporaryPassword;
    }
}