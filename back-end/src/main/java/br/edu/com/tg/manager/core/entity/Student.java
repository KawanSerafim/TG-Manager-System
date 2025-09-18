package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

/**
 * Entidade de domínio.
 * Representa uma classe de domínio que representa o aluno da FATEC.
 * A classe, por fazer parte do core, é pura.
 */
public class Student {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private String password;
    private StudentGroup studentGroup;

    /* Construtor vazio. Necessário para os Mappers. */
    public Student(){}

    /**
     * Construtor para construir um aluno.
     * @param name A variável que representa o nome do aluno.
     * @param registration A variável que representa a matrícula
     * do aluno (RA).
     * @param email A variável que representa o email do aluno.
     * @param password A variável que representa a senha do aluno.
     * @param studentGroup O objeto que representa uma turma.
     */
    public Student(
        String name,
        String registration,
        String email,
        String password,
        StudentGroup studentGroup
    ) {

        this.setName(name);
        this.setRegistration(registration);
        this.setEmail(email);
        this.setPassword(password);
        this.setStudentGroup(studentGroup);
    }

    /* Getters e Setters */

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
        
        /* Regra de negócio: o nome do aluno não pode estar vazio. */
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(
                "O nome de, pelo menos um aluno, está vazio. Verifique " + 
                "sua fonte de dados."
            );
        }

        this.name = name;
    }

    public String getRegistration() {
     
        return registration;
    }

    public void setRegistration(String registration) {
        
        /* Regra de negócio: a matrícula (RA) do aluno não estar vazia. */
        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException(
                "A matrícula (RA) de, pelo menos um aluno, está vazia. " +
                "Verifique sua fonte de dados."
            );
        }

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
        
        if(studentGroup == null) {

            throw new DomainException(
                "O aluno deve pertencer a uma turma."
            );
        }

        this.studentGroup = studentGroup;
    }   
}