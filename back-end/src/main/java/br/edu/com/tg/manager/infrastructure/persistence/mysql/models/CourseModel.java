package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseShift shift;

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

    public CourseShift getShift() {
        return shift;
    }

    public void setShift(CourseShift shift) {
        this.shift = shift;
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