package br.edu.com.tg.manager.infrastructure.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "student_groups")
public class StudentGroupData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseName;

    @OneToMany(
        
        mappedBy = "studentGroup", 
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<StudentData> students;

    public StudentGroupData(){}

    public Long getId() {
     
        return id;
    }

    public void setId(Long id) {
     
        this.id = id;
    }

    public String getCourseName() {
     
        return courseName;
    }

    public void setCourseName(String courseName) {
     
        this.courseName = courseName;
    }

    public List<StudentData> getStudents() {
     
        return students;
    }

    public void setStudents(List<StudentData> students) {
     
        this.students = students;
    }
}