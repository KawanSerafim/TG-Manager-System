package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import jakarta.persistence.*;
import java.util.List;

@Embeddable
public class CourseParametersModel {
    @ElementCollection(targetClass = CourseShift.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "course_available_shifts",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "shift", nullable = false)
    private List<CourseShift> availableShifts;

    @ElementCollection(targetClass = Discipline.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "course_available_disciplines",
            joinColumns = @JoinColumn(name = "course_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "discipline", nullable = false)
    private List<Discipline> availableDisciplines;

    public CourseParametersModel() {}

    public List<CourseShift> getAvailableShifts() {
        return availableShifts;
    }

    public void setAvailableShifts(List<CourseShift> availableShifts) {
        this.availableShifts = availableShifts;
    }

    public List<Discipline> getAvailableDisciplines() {
        return availableDisciplines;
    }

    public void setAvailableDisciplines(List<Discipline> availableDisciplines) {
        this.availableDisciplines = availableDisciplines;
    }
}