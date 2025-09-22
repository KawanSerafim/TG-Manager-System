package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio.
 * Representa um professor, podendo ser um orientador comum, um coordenador de
 * curso ou um coordenador de TG.
 * A classe, por fazer parte do core, é pura.
 */
public class Professor {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private String hashedPassword;
    private ProfessorRole role;

    /**
     * Construtor vazio.
     * Necessário para frameworks de persistência.
     */
    public Professor() {}

    /**
     * Construtor de negócio para criar um novo objeto de Professor.
     * Garante que o objeto seja criado em um estado válido.
     * @param name Nome do professor.
     * @param registration Matrícula do professor.
     * @param email Email da conta professor.
     * @param hashedPassword Senha criptografada do professor.
     * @param role Cargo do professor.
     */
    public Professor(

        String name,
        String registration,
        String email,
        String hashedPassword,
        ProfessorRole role
    ) {

        this.setName(name);
        this.setRegistration(registration);
        this.setEmail(email);
        this.setHashedPassword(hashedPassword);
        this.setRole(role);
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
        
        /* Regra de negócio: professor não pode conter nome vazio ou nulo. */
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
        
        /* Regra de negócio: professor não pode conter matrícula vazia
         * ou nula.
        */
        if(registration == null || registration.trim().isEmpty()) {

            throw new DomainException(

                "O campo matrícula é obrigatório."
            );
        }

        this.registration = registration;
    }

    public String getEmail() {
     
        return email;
    }

    public void setEmail(String email) {
        
        /* Regra de negócio: professor não pode conter email vazio ou nulo. */
        if(email == null ) {

            throw new DomainException(

                "O campo email é obrigatório."
            );
        }

        /* Regra de negócio: professor não pode conter email inváldio. */
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

    public ProfessorRole getRole() {
     
        return role;
    }

    public void setRole(ProfessorRole role) {
        
        /* Regra de negócio: professor não pode conter cargo nulo. */
        if(role == null) {

            throw new DomainException(

                "O campo cargo é obrigatório."
            );
        }

        this.role = role;
    }
}