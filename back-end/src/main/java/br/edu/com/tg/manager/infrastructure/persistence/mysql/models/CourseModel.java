package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
 * - Anotação @OneToOne: indica ao Spring JPA que esta classe faz parte de uma
 * relação de um para um.
 * 
 * - Anotação @JoinColumn: indica ao Spring JPA o nome da coluna de chave
 * estrangeira nesta tabela. A variável 'name' determina o nome da coluna em que
 * o ID será salvo.
 */

/**
 * Modelo de dados:
 * Determina um modelo da entidade de domínio Course, que será manipulado pelo
 * Spring JPA. Por pertencer à infraestrutura da aplicação, esta classe utiliza
 * das anotações persistence, as quais permitem ao framework manipular os dados
 * no SGBD.
 */
@Entity
@Table(name = "courses")
public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseShift shift;

    @OneToOne
    @JoinColumn(name = "tg_coordinator_id", nullable = false)
    private ProfessorModel tgCoordinator;

    @OneToOne
    @JoinColumn(name = "course_coordinator_id", nullable = false)
    private ProfessorModel courseCoordinator;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public CourseModel() {}

    /**
     * Método Get.
     * @return ID salvo no modelo de dados do curso.
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
     * @return Nome salvo no modelo de dados do curso.
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
     * @return Turno salvo no modelo de dados do curso.
     */
    public CourseShift getShift() {
     
        return shift;
    }

    /**
     * Método Set.
     * @param shift Turno fornecido.
     */
    public void setShift(CourseShift shift) {
     
        this.shift = shift;
    }

    /**
     * Método Get.
     * @return Coordenador de TG salvo no modelo de dados do curso.
     */
    public ProfessorModel getTgCoordinator() {
     
        return tgCoordinator;
    }

    /**
     * Método Set.
     * @param tgCoordinator Coordenador de TG fornecido.
     */
    public void setTgCoordinator(ProfessorModel tgCoordinator) {
     
        this.tgCoordinator = tgCoordinator;
    }

    /**
     * Método Get.
     * @return Coordenador de curso salvo no modelo de dados do curso.
     */
    public ProfessorModel getCourseCoordinator() {
     
        return courseCoordinator;
    }

    /**
     * Método Set.
     * @param courseCoordinator Coordenador de curso fornecido.
     */
    public void setCourseCoordinator(ProfessorModel courseCoordinator) {
     
        this.courseCoordinator = courseCoordinator;
    }
}