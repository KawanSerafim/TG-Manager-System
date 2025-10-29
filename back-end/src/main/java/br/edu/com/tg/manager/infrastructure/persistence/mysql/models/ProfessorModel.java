package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class ProfessorModel {
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
    @Column(nullable = false)
    private ProfessorRole role;

    public ProfessorModel() {}

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

    public ProfessorRole getRole() {
        return role;
    }

    public void setRole(ProfessorRole role) {
        this.role = role;
    }
}