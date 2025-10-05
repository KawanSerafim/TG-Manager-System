package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Anotações do JPA:
 *
 * - Anotação @Entity: indica ao Spring JPA que esta classe é uma entidade.
 *
 * - Anotação @Table: indica ao Spring JPA que esta classe é uma tabela do banco
 * de dados. Dentro dos parênteses, o valor atribuído a variável 'name' será o
 * nome da tabela.
 *
 * - Anotação @Id: indica ao Spring JPA que a variável determina o ID da tabela.
 *
 * - Anotação @GeneratedValue: indica ao Spring JPA como o valor da variável
 * será gerada.
 *
 * - Anotação @Column: indica ao Spring JPA que esta variável será uma coluna da
 * tabela. Dentro dos parênteses, o valor booleano 'nullable' determina se a
 * coluna poderá conter valores nulos ou não. E na 'unique', determina se o
 * valor da coluna pode ou não se repetir.
 *
 * - Anotação @Enumerated: indica ao Spring JPA que o campo é do tipo Enum e
 * deve ser persistido no banco de dados. Porém, por padrão, ele é organizado
 * mediante uma enumeração numérica, o que gera um risco em potencial caso os
 * números sejam alterados futuramente. Logo, o tipo (EnumType) deve ser
 * determinado como um String.
 *
 * - Anotação @ManyToOne: indica ao Spring JPA que esta classe faz parte de uma
 * relação de muitos para um, onde esta classe é o muitos, e a instância o um.
 *
 * - Anotação @JoinColumn: indica ao Spring JPA o nome da coluna de chave
 * estrangeira nesta tabela. A variável 'name' determina o nome da coluna em que
 * o ID será salvo.
 */

/**
 * Modelo de dados:
 * Determina um modelo da entidade de domínio Student, que será manipulado pelo
 * Spring JPA. Por pertencer à infraestrutura da aplicação, esta classe utiliza
 * das anotações persistence, as quais permitem ao framework manipular os dados
 * no SGBD.
 */
@Entity
@Table(name = "students")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String registration;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;

    /*
     * Nota: sempre que for precisar da turma através do aluno, utilize o get.
     * O método de busca LAZY indica que ao buscar os dados do aluno, o objeto
     * da turma não estará inicializado. A tentativa de utilizar a turma sem o
     * get resultará em LazyInitializationException.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_group_id", nullable = false)
    private StudentGroupModel studentGroup;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public StudentModel() {}

    /**
     * Método Get.
     * @return ID salvo no modelo de dados do aluno.
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
     * @return Nome salvo no modelo de dados do aluno.
     */
    public String getName() {
     
        return name;
    }

    /**
     * Método Set.
     * @param name Nome fornecido.
     */
    public void setName(String name) {
     
        this.name = name;
    }

    /**
     * Método Get.
     * @return Matrícula salva no modelo de dados do aluno.
     */
    public String getRegistration() {
     
        return registration;
    }

    /**
     * Método Set.
     * @param registration Matrícula fornecida.
     */
    public void setRegistration(String registration) {
     
        this.registration = registration;
    }

    /**
     * Método Get.
     * @return Email salvo no modelo de dados do aluno.
     */
    public String getEmail() {
     
        return email;
    }

    /**
     * Método Set.
     * @param email Email fornecido.
     */
    public void setEmail(String email) {
     
        this.email = email;
    }

    /**
     * Método Get.
     * @return Senha criptografada salva no modelo de dados do aluno.
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
     * @return Status salvo no modelo de dados do aluno.
     */
    public StudentStatus getStatus() {
     
        return status;
    }

    /**
     * Método Set.
     * @param status Status fornecido.
     */
    public void setStatus(StudentStatus status) {
     
        this.status = status;
    }

    /**
     * Método Get.
     * @return Turma salva no modelo de dados do aluno.
     */
    public StudentGroupModel getStudentGroup() {
     
        return studentGroup;
    }

    /**
     * Método Set.
     * @param studentGroup Turma fornecida.
     */
    public void setStudentGroup(StudentGroupModel studentGroup) {
     
        this.studentGroup = studentGroup;
    }
}