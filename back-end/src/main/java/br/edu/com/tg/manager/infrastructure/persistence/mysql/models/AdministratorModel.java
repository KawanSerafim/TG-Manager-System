package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
public class AdministratorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private UserAccountModel userAccount;

    public AdministratorModel() {}

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

    public UserAccountModel getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountModel userAccount) {
        this.userAccount = userAccount;
    }
}