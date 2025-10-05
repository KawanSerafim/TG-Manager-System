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
    private String email;
    private String hashedPassword;
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
     * @param email Email da conta do professor.
     * @param hashedPassword Senha criptografada da conta do professor.
     * @param role Cargo do professor.
     */
    public Professor(

        String name,
        String registration,
        String email,
        String hashedPassword,
        ProfessorRole role
    ) {

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setName(name);
        this.setRegistration(registration);
        this.setEmail(email);
        this.setHashedPassword(hashedPassword);
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
     * Método Get.
     * @return Email da conta do professor.
     */
    public String getEmail() {
     
        return email;
    }

    /**
     * Método Set.
     * @param email Email fornecido.
     */
    public void setEmail(String email) {
        
        // Regra de negócio: professor não pode conter email vazio ou nulo.
        if(email == null || email.trim().isEmpty()) {

            throw new DomainException(

                "O campo email é obrigatório."
            );
        }

        // Regra de negócio: professor não pode conter email inváldio.
        if(!(email.contains("@"))) {

            throw new DomainException(

                "O formato do campo email é inválido."
            );
        }

        this.email = email;
    }

    /**
     * Método Get.
     * @return Senha criptografada da conta do professor.
     */
    public String getHashedPassword() {
     
        return hashedPassword;
    }

    /**
     * Método Set.
     * @param hashedPassword Senha fornecida.
     */
    public void setHashedPassword(String hashedPassword) {
     
        this.hashedPassword = hashedPassword;
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