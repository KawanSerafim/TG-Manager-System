package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private CourseParametersModel parameters;

    @OneToOne
    @JoinColumn(name = "tg_coordinator_id", nullable = false)
    private ProfessorModel tgCoordinator;

    @OneToOne
    @JoinColumn(name = "course_coordinator_id", nullable = false)
    private ProfessorModel courseCoordinator;

    public CourseModel() {}

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

    public CourseParametersModel getParameters() {
        return parameters;
    }

    public void setParameters(CourseParametersModel parameters) {
        this.parameters = parameters;
    }

    public ProfessorModel getTgCoordinator() {
        return tgCoordinator;
    }

    public void setTgCoordinator(ProfessorModel tgCoordinator) {
        this.tgCoordinator = tgCoordinator;
    }

    public ProfessorModel getCourseCoordinator() {
        return courseCoordinator;
    }

    public void setCourseCoordinator(ProfessorModel courseCoordinator) {
        this.courseCoordinator = courseCoordinator;
    }
}