package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um aluno da instituição, numa determinada turma.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public class Student {
    private Long id;
    private String name;
    private String registration;
    private UserAccount userAccount;
    private StudentStatus status;
    private StudentGroup studentGroup;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public Student() {}

    /**
     * Construtor de domínio:
     * Cria um novo objeto de Student e garante que o objeto seja
     * criado num estado inicial válido (PRE_REGISTRATION).
     * @param name Nome do aluno.
     * @param registration Matrícula do aluno.
     * @param studentGroup Turma do aluno.
     */
    public Student(
            String name,
            String registration,
            StudentGroup studentGroup
    ) {
        // Define o status inicial no construtor de domínio.
        this.setStatus(StudentStatus.PRE_REGISTRATION);

        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setRegistration(registration);
        this.setStudentGroup(studentGroup);
    }

    // MÉTODOS GETTERS E SETTERS.

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
        // Regra de negócio: aluno não pode conter nome vazio ou nulo.
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
        // Regra de negócio: aluno não pode conter matrícula vazia ou nula.
        if(registration == null || registration.trim().isEmpty()) {
            throw new DomainException(
                    "O campo matrícula é obrigatório."
            );
        }
        this.registration = registration;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        // Regra de negócio: aluno não pode conter status nulo.
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
        // Regra de negócio: aluno não pode conter turma nula.
        if(studentGroup == null) {
            throw new DomainException(
                    "O campo turma é obrigatório."
            );
        }
        this.studentGroup = studentGroup;
    }

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Email da conta de usuário do aluno.
     */
    public String getEmail() {

        return userAccount.getEmail();
    }

    /**
     * Método Set (DELEGAÇÃO).
     * @param email Email fornecido.
     */
    public void setEmail(String email) {

        this.userAccount.setEmail(email);
    }

    /**
     * Método Get (DELEGAÇÃO).
     * @return Senha criptografada da conta de usuário do aluno.
     */
    public String getPassword() {

        return userAccount.getPassword();
    }

    /**
     * Método Set (DELEGAÇÃO).
     * @param password Senha fornecida.
     */
    public void setPassword(String password) {

        this.userAccount.setPassword(password);
    }
}