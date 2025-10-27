package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import jakarta.persistence.*;

@Entity
@Table(name = "student_groups")
public class StudentGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Discipline discipline;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;

    public StudentGroupModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}