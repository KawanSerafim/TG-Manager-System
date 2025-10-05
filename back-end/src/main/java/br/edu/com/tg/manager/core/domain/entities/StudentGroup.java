package br.edu.com.tg.manager.core.domain.entities;

import java.time.Year;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa uma turma de TG de um determinado curso, ano e semestre.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * classe pura.
 */
public class StudentGroup {

    private Long id;
    private Course course;
    private Discipline discipline;
    private Integer year;
    private Integer semester;
    private String temporaryPassword;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public StudentGroup() {}

    /**
     * Construtor de negócio:
     * Cria um novo objeto de StudentGroup e garante que o objeto seja criado
     * num estado válido.
     * @param course Curso da turma.
     * @param discipline Disciplina de TG da turma.
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @param temporaryPassword Senha temporária da turma.
     */
    public StudentGroup(

        Course course,
        Discipline discipline,
        Integer year,
        Integer semester,
        String temporaryPassword
    ) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setCourse(course);
        this.setDiscipline(discipline);
        this.setYear(year);
        this.setSemester(semester);
        this.setTemporaryPassword(temporaryPassword);
    }

    /**
     * Método Get.
     * @return ID da turma.
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
     * @return Curso da turma.
     */
    public Course getCourse() {
       
        return course;
    }

    /**
     * Método Set.
     * @param course Curso fornecido.
     */
    public void setCourse(Course course) {
        
        // Regra de negócio: turma não pode conter curso nulo.
        if(course == null) {

            throw new DomainException(

                "O campo curso é obrigatório."
            );
        }

        this.course = course;
    }

    /**
     * Método Get.
     * @return Disciplina de TG da turma.
     */
    public Discipline getDiscipline() {
       
        return discipline;
    }

    /**
     * Método Set.
     * @param discipline Disciplina de TG fornecida.
     */
    public void setDiscipline(Discipline discipline) {
        
        // Regra de negócio: turma não pode conter disciplina de TG nula.
        if(discipline == null) {

            throw new DomainException(

                "O campo disciplina de TG é obrigatório."
            );
        }

        this.discipline = discipline;
    }

    /**
     * Método Get.
     * @return Ano da turma.
     */
    public Integer getYear() {
       
        return year;
    }

    /**
     * Método Set.
     * @param year Ano fornecido.
     */
    public void setYear(Integer year) {
        
        // Regra de negócio: turma não pode conter ano nulo.
        if(year == null) {

            throw new DomainException(

                "O campo ano é obrigatório."
            );
        }

        int currentYear = Year.now().getValue();

        // Regra de negócio: turma não pode conter ano inválido.
        if(!(year.equals(currentYear))) {

            throw new DomainException(

                "O ano não pode ser diferente do atual."
            );
        }

        this.year = year;
    }

    /**
     * Método Get.
     * @return Semestre da turma.
     */
    public Integer getSemester() {
     
        return semester;
    }

    /**
     * Método Set.
     * @param semester Semestre fornecido.
     */
    public void setSemester(Integer semester) {
        
        // Regra de negócio: turma não pode conter semestre nulo.
        if(semester == null) {

            throw new DomainException(

                "O campo semestre é obrigatório."
            );
        }

        // Regra de negócio: turma não pode conter semestre inválido.
        if(semester != 1 && semester != 2) {

            throw new DomainException(

                "O semestre foge do padrão: 1 ou 2."
            );
        }

        this.semester = semester;
    }

    /**
     * Método Get.
     * @return Senha temporária da turma.
     */
    public String getTemporaryPassword() {
     
        return temporaryPassword;
    }

    /**
     * Método Set.
     * @param temporaryPassword Senha fornecida.
     */
    public void setTemporaryPassword(String temporaryPassword) {
        
        /*
         * Regra de negócio: turma não pode conter senha temporária vazia ou
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