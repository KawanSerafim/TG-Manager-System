package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio.
 * Representa um aluno.
 * A classe, por fazer parte do core, é pura.
 */
public class Student {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private String hashedPassword;
    private StudentStatus status;
    private StudentGroup studentGroup;

    /**
     * Construtor vazio.
     * Necessário para frameworks de persistência.
     */
    public Student() {}

    /**
     * Construtor de negócio para criar um novo objeto de pre-Student.
     * Garante que o objeto seja criado em um estado válido.
     * @param name
     * @param registration
     * @param studentGroup
     */
    public Student(

        String name,
        String registration,
        StudentGroup studentGroup
    ) {

        this.setName(name);
        this.setRegistration(registration);
        this.setStudentGroup(studentGroup);
        this.setStatus(StudentStatus.PRE_REGISTRATION);
    }

    public void finalizeRegistration(String email, String hashedPassword) {

        /* Regra de negócio: o aluno deve estar pré-cadastrado para poder
         * finalizar um cadastro.
         */
        if(this.status != StudentStatus.PRE_REGISTRATION) {

            throw new DomainException(

                "O aluno não tem o estado válido para ação."
            );
        }

        this.setEmail(email);
        this.setHashedPassword(hashedPassword);
        this.setStatus(StudentStatus.ACTIVE);
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
        
        /* Regra de negócio: aluno não pode conter nome vazio ou nulo. */
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }

        this.name = name;
    }

    public String getRegistration() {
     
        return registration;
    }

    public void setRegistration(String registration) {
        
        /* Regra de negócio: aluno não pode conter RA vazio ou nulo. */
        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException(

                "O campo RA é obrigatório."
            );
        }

        this.registration = registration;
    }

    public String getEmail() {
     
        return email;
    }

    public void setEmail(String email) {
        
        /* Regra de negócio: aluno não pode conter email vazio ou nulo. */
        if(email == null || email.trim().isEmpty()) {

            throw new DomainException(

                "O campo email é obrigatório."
            );
        }

        /* Regra de negócio: aluno não pode conter email inválido. */
        if(!(email.contains("@"))) {

            throw new DomainException(

                "O formato do campo email é inválido."
            );
        }

        this.email = email;
    }

    public String getHashedPassword() {
     
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
     
        this.hashedPassword = hashedPassword;
    }

    public StudentStatus getStatus() {
     
        return status;
    }

    public void setStatus(StudentStatus status) {
        
        /* Regra de negócio: aluno não pode conter status nulo. */
        if(status == null) {

            throw new DomainException(

                "O campo status é obrigatório."
            );
        }

        this.status = status;
    }

    public StudentGroup getStudentGroup() {
     
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        
        /* Regra de negócio: aluno não pode conter turma nula. */
        if(studentGroup == null) {

            throw new DomainException(

                "O campo turma é obrigatório."
            );
        }

        this.studentGroup = studentGroup;
    }
}