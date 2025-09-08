package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

public class Student {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private String password;
    private StudentGroup studentGroup;

    /**
     * Construtor vazio.
     */
    public Student(){}

    /**
     * Construtor para criar um aluno.
     * @param name Nome do aluno.
     * @param registration Valor do RA.
     */
    public Student(String name, String registration) {

        if(name == null || name.trim().isEmpty()) {

            throw new DomainException("O nome de, pelo menos um aluno, está vazio. Verifique sua fonte de dados.");
        }

        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException("A matrícula (RA) de, pelo menos um aluno, está vazia. Verifique sua fonte de dados.");
        }

        this.name = name;
        this.registration = registration;
    }

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

    public StudentGroup getStudentGroup() {
     
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
     
        this.studentGroup = studentGroup;
    }   
}