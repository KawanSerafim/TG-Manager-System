package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um aluno da instituição, numa determinada turma.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * classe pura.
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
     * Construtor de negócio:
     * Cria um novo objeto de pre-Student e garante que o objeto seja criado num
     * estado válido.
     * @param name Nome do aluno.
     * @param registration Matrícula do aluno.
     * @param studentGroup Turma do aluno.
     */
    public Student(

        String name,
        String registration,
        StudentGroup studentGroup
    ) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setName(name);
        this.setRegistration(registration);
        this.setStudentGroup(studentGroup);
        this.setStatus(StudentStatus.PRE_REGISTRATION);
    }

    /**
     * Método de negócio:
     * Finaliza o cadastro do aluno no sistema, garantindo que o objeto dele
     * esteja em um estado válido para tornar a conta ativa.
     * @param userAccount Conta de usuário do Aluno.
     */
    public void finalizeRegistration(UserAccount userAccount) {

        /*
         * Regra de negócio: o aluno deve estar pré-cadastrado para poder
         * finalizar um cadastro.
         */
        if(this.status != StudentStatus.PRE_REGISTRATION) {

            throw new DomainException(

                "O aluno não tem o estado válido para ação."
            );
        }

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setUserAccount(userAccount);
        this.setStatus(StudentStatus.ACTIVE);
    }

    /**
     * Método Get.
     * @return ID do aluno.
     */
    public Long getId() {
     
        return id;
    }

    /**
     * Método Set.
     * @param id ID fornecido.
     */
    public void setId(Long id) {
     
        this.id = id;
    }

    /**
     * Método Get.
     * @return Nome do aluno.
     */
    public String getName() {
     
        return name;
    }

    /**
     * Método Set.
     * @param name Nome fornecido.
     */
    public void setName(String name) {
        
        // Regra de negócio: aluno não pode conter nome vazio ou nulo.
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }

        this.name = name;
    }

    /**
     * Método Get.
     * @return Matrícula do aluno.
     */
    public String getRegistration() {
     
        return registration;
    }

    /**
     * Método Set.
     * @param registration Matrícula fornecida.
     */
    public void setRegistration(String registration) {
        
        // Regra de negócio: aluno não pode conter matrícula vazia ou nula.
        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException(

                "O campo matrícula é obrigatório."
            );
        }

        this.registration = registration;
    }

    /**
     * Método Get.
     * @return Conta de usuário do aluno.
     */
    public UserAccount getUserAccount() {

        return userAccount;
    }

    /**
     * Método Set.
     * @param userAccount Conta de usuário fornecida.
     */
    public void setUserAccount(UserAccount userAccount) {

        this.userAccount = userAccount;
    }

    /**
     * Método Get.
     * @return Email da conta do aluno.
     */
    public String getEmail() {
     
        return userAccount.getEmail();
    }

    /**
     * Método Set.
     * @param email Email fornecido.
     */
    public void setEmail(String email) {

        this.userAccount.setEmail(email);
    }

    /**
     * Método Get.
     * @return Senha criptografada da conta do aluno.
     */
    public String getPassword() {
     
        return userAccount.getPassword();
    }

    /**
     * Método Set.
     * @param password Senha fornecida.
     */
    public void setPassword(String password) {
     
        this.userAccount.setPassword(password);
    }

    /**
     * Método Get.
     * @return Status da conta do aluno.
     */
    public StudentStatus getStatus() {
     
        return status;
    }

    /**
     * Método Set.
     * @param status Status fornecido.
     */
    public void setStatus(StudentStatus status) {
        
        // Regra de negócio: aluno não pode conter status nulo.
        if(status == null) {

            throw new DomainException(

                "O campo status é obrigatório."
            );
        }

        this.status = status;
    }

    /**
     * Método Get.
     * @return Turma do aluno.
     */
    public StudentGroup getStudentGroup() {
     
        return studentGroup;
    }

    /**
     * Método Set.
     * @param studentGroup Turma fornecida.
     */
    public void setStudentGroup(StudentGroup studentGroup) {
        
        // Regra de negócio: aluno não pode conter turma nula.
        if(studentGroup == null) {

            throw new DomainException(

                "O campo turma é obrigatório."
            );
        }

        this.studentGroup = studentGroup;
    }
}