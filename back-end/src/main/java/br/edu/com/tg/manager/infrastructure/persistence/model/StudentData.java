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

/**
 * Modelo de dados (JPA Entity)
 * Representa a tabela 'students' no banco de dados.
 * Esta classe contém as anotações de persistência e mapeia
 * a relação com a tabela 'student_groups'.
 */
@Entity
@Table(name = "students")
public class StudentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String registration;
    
    @Column(unique = true)
    private String email;
    
    @Column(length = 255)
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_group_id")
    private StudentGroupData studentGroup;

    public StudentData(){}

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

    public String getRegistration() {
     
        return registration;
    }

    public void setRegistration(String registration) {
     
        this.registration = registration;
    }

    public String getEmail() {
     
        return email;
    }

    public void setEmail(String email) {
     
        this.email = email;
    }

    public String getPassword() {
     
        return password;
    }

    public void setPassword(String password) {
     
        this.password = password;
    }

    public StudentGroupData getStudentGroup() {
     
        return studentGroup;
    }

    public void setStudentGroup(StudentGroupData studentGroup) {
     
        this.studentGroup = studentGroup;
    }
}