package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade de domínio:
 * Representa um aluno da instituição, em determinadas turmas.
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
    private List<StudentGroup> studentGroups = new ArrayList<>();

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
     * @param studentGroups Turma(s) do aluno.
     */
    public Student(
            String name,
            String registration,
            StudentGroup studentGroups
    ) {
        // Define o status inicial no construtor de domínio.
        this.setStatus(StudentStatus.PRE_REGISTRATION);

        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setRegistration(registration);

        // Matricula na turma.
        this.enrollInGroup(studentGroups);
    }

    // MÉTODOS DE DOMÍNIO.

    /**
     * Método de domínio:
     * Matricula o aluno em uma turma.
     * @param studentGroup Turma.
     */
    public void enrollInGroup(StudentGroup studentGroup) {
        // Regra de domínio: o campo turma é obrigatório.
        if(studentGroup == null) {
            throw new DomainException(
                    "O campo turma é obrigatório."
            );
        }

        // Regra de domínio: o aluno não pode matricular na mesma turma.
        if(this.studentGroups.contains(studentGroup)) {
            throw new DomainException(
                    "O aluno já está matrículado nesta turma."
            );
        }

        // Adiciona à lista.
        this.studentGroups.add(studentGroup);
    }

    /**
     * Método de domínio:
     * Atualiza a conta de usuário do aluno para uma ativa.
     * @param userAccount Conta de usuário do aluno.
     */
    public void completeRegistration(UserAccount userAccount) {
        // Regra de domínio: conta de usuário deve estar com o email confirmado.
        if(userAccount.getStatus() != UserAccountStatus.EMAIL_CONFIRMED) {
            throw new DomainException(
                    "A conta de usuário não tem o estado válido para a ação."
            );
        }

        this.userAccount = userAccount;
        userAccount.setStatus(UserAccountStatus.ACTIVE);
        this.setStatus(StudentStatus.REGISTERED);
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
        // Regra de domínio: o campo nome é obrigatório.
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
        // Regra de domínio: o campo nome é obrigatório.
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
        // Regra de domínio: o campo status é obrigatório.
        if(status == null) {
            throw new DomainException(
                    "O campo status é obrigatório."
            );
        }
        this.status = status;
    }

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(List<StudentGroup> studentGroups) {
        // Regra de domínio: o campo turmas é obrigatório.
        if(studentGroups == null) {
            throw new DomainException(
                    "O campo turmas é obrigatório."
            );
        }
        this.studentGroups = studentGroups;
    }

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Email da conta de usuário do aluno.
     */
    public String getEmail() {
        if(this.userAccount == null) {
            return null;
        }
        return userAccount.getEmail();
    }

    /**
     * Método Set (DELEGAÇÃO).
     * @param email Email fornecido.
     */
    public void setEmail(String email) {
        if(this.userAccount == null) {
            throw new DomainException(
                    "Ação impossível com conta de usuário nula."
            );
        }
        this.userAccount.setEmail(email);
    }

    /**
     * Método Get (DELEGAÇÃO).
     * @return Senha criptografada da conta de usuário do aluno.
     */
    public String getPassword() {
        if(this.userAccount == null) {
            return null;
        }
        return userAccount.getPassword();
    }

    /**
     * Método Set (DELEGAÇÃO).
     * @param password Senha fornecida.
     */
    public void setPassword(String password) {
        if(this.userAccount == null) {
            throw new DomainException(
                    "Ação impossível com conta de usuário nula."
            );
        }
        this.userAccount.setPassword(password);
    }
}