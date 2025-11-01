package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import java.time.Year;

/**
 * Entidade de domínio:
 * Representa uma turma da disciplina de Trabalho de Graduação (TG)
 * em um determinado semestre, ano e turno.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public class StudentGroup {
    private Long id;
    private Course course;
    private Discipline discipline;
    private Integer year;
    private Integer semester;
    private CourseShift courseShift;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public StudentGroup() {}

    /**
     * Construtor de domínio:
     * Cria um novo objeto de StudentGroup e garante que o objeto
     * seja criado num estado válido.
     * @param course Curso associado à turma.
     * @param discipline Disciplina de TG (TG1 ou TG2).
     * @param year Ano da turma.
     * @param semester Semestre da turma.
     * @param courseShift Turno da turma.
     */
    public StudentGroup(
            Course course,
            Discipline discipline,
            Integer year,
            Integer semester,
            CourseShift courseShift
    ) {
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setCourse(course);
        this.setDiscipline(discipline);
        this.setYear(year);
        this.setSemester(semester);
        this.setCourseShift(courseShift);
    }

    // MÉTODOS GETTERS E SETTERS.

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
        // Regra de domínio: o campo curso é obrigatório.
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
        // Regra de domínio: o curso deve existir para validar a disciplina.
        if(this.course == null) {
            throw new DomainException(
                    "Impossível validar a disciplina com curso nulo."
            );
        }

        // Regra de domínio: o campo disciplina de TG é obrigatório.
        if(discipline == null) {
            throw new DomainException(
                    "O campo disciplina de TG é obrigatório."
            );
        }

        // Regra de domínio: a disciplina deve ser suportada pelo curso.
        if(!this.course.getAvailableDisciplines().contains(discipline)) {
            throw new DomainException(
                    "A disciplina [" + discipline + "] não é oferecida pelo "
                    + "curso."
            );
        }
        this.discipline = discipline;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        // Regra de domínio: o campo ano é obrigatório.
        if(year == null) {
            throw new DomainException(
                    "O campo ano é obrigatório."
            );
        }

        int currentYear = Year.now().getValue();

        // Regra de domínio: o ano não pode ser diferente do atual.
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
        // Regra de domínio: o campo semestre é obrigatório.
        if(semester == null) {
            throw new DomainException(
                    "O campo semestre é obrigatório."
            );
        }

        // Regra de domínio: o semestre foge do padrão: 1 ou 2.
        if(semester != 1 && semester != 2) {
            throw new DomainException(
                    "O semestre foge do padrão: 1 ou 2."
            );
        }
        this.semester = semester;
    }

    public CourseShift getCourseShift() {
        return courseShift;
    }

    public void setCourseShift(CourseShift courseShift) {
        // Regra de domínio: o curso deve existir para validar o turno.
        if(this.course == null) {
            throw new DomainException(
                    "Impossível validar o turno com curso nulo."
            );
        }

        // Regra de domínio: o campo turno do curso é obrigatório.
        if(courseShift == null) {
            throw new DomainException(
                    "O campo turno do curso é obrigatório."
            );
        }

        // Regra de domínio: o turno deve ser suportado pelo curso.
        if(!this.course.getAvailableShifts().contains(courseShift)) {
            throw new DomainException(
                    "O turno [" + courseShift + "] não é oferecido pelo "
                    + "curso."
            );
        }

        this.courseShift = courseShift;
    }

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Nome do curso associado à turma.
     */
    public String getCourseName() {
        return course.getName();
    }
}