package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um professor da instituição, que pode assumir um dos seguintes
 * cargos: Orientador, Coordenador de TG ou Coordenador de Curso. Todo professor
 * é, por padrão, um Orientador, independentemente de acumular ou não uma função
 * de coordenação.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * classe pura.
 */
public class Professor {

    private Long id;
    private String name;
    private String registration;
    private UserAccount userAccount;
    private ProfessorRole role;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public Professor() {}

    /**
     * Construtor de negócio:
     * Cria um novo objeto de Professor e garante que o objeto seja criado num
     * estado válido.
     * @param name Nome do professor.
     * @param registration Matrícula do professor.
     * @param userAccount Conta de usuário do professor.
     * @param role Cargo do professor.
     */
    public Professor(

        String name,
        String registration,
        UserAccount userAccount,
        ProfessorRole role
    ) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setName(name);
        this.setRegistration(registration);
        this.setUserAccount(userAccount);
        this.setRole(role);
    }

    /**
     * Método Get.
     * @return ID do professor.
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
     * @return Nome do professor.
     */
    public String getName() {
     
        return name;
    }

    /**
     * Método Set.
     * @param name Nome fornecido.
     */
    public void setName(String name) {
        
        // Regra de negócio: professor não pode conter nome vazio ou nulo.
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }

        this.name = name;
    }

    /**
     * Método Get.
     * @return Matrícula do professor.
     */
    public String getRegistration() {
     
        return registration;
    }

    /**
     * Método Set.
     * @param registration Matrícula fornecida.
     */
    public void setRegistration(String registration) {

        /*
         * Regra de negócio: professor não pode conter matrícula vazia
         * ou nula.
         */
        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException(

                "O campo matrícula é obrigatório."
            );
        }

        this.registration = registration;
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
     * @return Email da conta de usuário do professor.
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
     * @return Senha da conta de usuário do professor.
     */
    public String getPassword() {
     
        return userAccount.getPassword();
    }

    /**
     * Método Set.
     * @param password Senha fornecida.
     */
    public void setHashedPassword(String password) {

        this.userAccount.setPassword(password);
    }

    /**
     * Método Get.
     * @return Cargo do professor.
     */
    public ProfessorRole getRole() {
     
        return role;
    }

    /**
     * Método Set.
     * @param role Cargo fornecido.
     */
    public void setRole(ProfessorRole role) {
        
        // Regra de negócio: professor não pode conter cargo nulo.
        if(role == null) {

            throw new DomainException(

                "O campo cargo é obrigatório."
            );
        }

        this.role = role;
    }
}