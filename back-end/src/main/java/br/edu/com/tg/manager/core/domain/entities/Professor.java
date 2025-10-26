package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um professor da instituição. O professor é associado a
 * um cargo (role) e a uma conta de usuário para acesso ao sistema.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
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
     * Construtor de domínio:
     * Cria um novo objeto de Professor e garante que o objeto seja
     * criado num estado válido.
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
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setRegistration(registration);
        this.setUserAccount(userAccount);
        this.setRole(role);
    }

    // MÉTODOS DE DOMÍNIO:

    /**
     * Método de domínio:
     * Verifica se o professor tem cargo necessário para ser orientador.
     * @return True ou False.
     */
    public boolean canBeAdvisor() {
        return this.role.hasAdvisorPermissions();
    }

    /**
     * Método de domínio:
     * Verifica se o professor tem cargo necessário para ser coordenador de TG.
     * @return True ou False.
     */
    public boolean canBeTgCoordinator() {
        return this.role.hasTgCoordinatorPermissions();
    }

    /**
     * Método de domínio:
     * Verifica se o professor tem cargo necessário para ser coordenador de
     * curso.
     * @return True ou False.
     */
    public boolean canBeCourseCoordinator() {
        return this.role.hasCourseCoordinatorPermissions();
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
        // Regra de domínio: o campo matrícula é obrigatório.
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
        // Regra de domínio: o campo conta de usuário é obrigatório.
        if(userAccount == null) {
            throw new DomainException(
                    "O campo conta de usuário é obrigatório."
            );
        }
        this.userAccount = userAccount;
    }

    public ProfessorRole getRole() {
        return role;
    }

    public void setRole(ProfessorRole role) {
        // Regra de domínio: o campo cargo é obrigatório.
        if(role == null) {
            throw new DomainException(
                    "O campo cargo é obrigatório."
            );
        }
        this.role = role;
    }

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Email da conta de usuário do professor.
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
     * @return Senha criptografada da conta de usuário do professor.
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