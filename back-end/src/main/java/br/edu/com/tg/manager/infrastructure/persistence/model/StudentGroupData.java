package br.edu.com.tg.manager.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "student_groups", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"course_id", "year", "semester"})
})
public class StudentGroupData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseData course;

    public StudentGroupData(){}

    public Long getId() {
     
        return id;
    }

    public void setId(Long id) {
     
        this.id = id;
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

    public CourseData getCourse() {
     
        return course;
    }

    public void setCourse(CourseData course) {
     
        this.course = course;
    }   
}