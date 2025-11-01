package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String registration;

    @Embedded
    private UserAccountModel userAccount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StudentStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_enrolled_groups",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "student_group_id")
    )
    private List<StudentGroupModel> studentGroups = new ArrayList<>();

    public StudentModel() {}

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

    public UserAccountModel getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountModel userAccount) {
        this.userAccount = userAccount;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public List<StudentGroupModel> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(List<StudentGroupModel> studentGroups) {
        this.studentGroups = studentGroups;
    }
}