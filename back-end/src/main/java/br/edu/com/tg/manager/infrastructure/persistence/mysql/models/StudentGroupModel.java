package br.edu.com.tg.manager.infrastructure.persistence.mysql.models;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
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
 * Determina um modelo da entidade de domínio StudentGroup, que será manipulado
 * pelo Spring JPA. Por pertencer à infraestrutura da aplicação, esta classe
 * utiliza das anotações persistence, as quais permitem ao framework manipular
 * os dados no SGBD.
 */
@Entity
@Table(name = "student_groups")
public class StudentGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nota: sempre que for precisar do curso através da turma, utilize o get.
     * O método de busca LAZY indica que ao buscar os dados da turma, o
     * objeto do curso não estará inicializado. A tentativa de utilizar o curso
     * sem o get resultará em LazyInitializationException.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseModel course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Discipline discipline;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer semester;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public StudentGroupModel() {}

    /**
     * Método Get.
     * @return ID salvo no modelo de dados da turma.
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
     * @return Curso salvo no modelo de dados da turma.
     */
    public CourseModel getCourse() {
     
        return course;
    }

    /**
     * Método Set.
     * @param course Curso fornecido.
     */
    public void setCourse(CourseModel course) {
     
        this.course = course;
    }

    /**
     * Método Get.
     * @return Disciplina salva no modelo de dados da turma.
     */
    public Discipline getDiscipline() {
     
        return discipline;
    }

    /**
     * Método Set.
     * @param discipline Disciplina fornecida.
     */
    public void setDiscipline(Discipline discipline) {
     
        this.discipline = discipline;
    }

    /**
     * Método Get.
     * @return Ano salvo no modelo de dados da turma.
     */
    public Integer getYear() {
     
        return year;
    }

    /**
     * Método Set.
     * @param year Ano fornecido.
     */
    public void setYear(Integer year) {
     
        this.year = year;
    }

    /**
     * Método Get.
     * @return Semestre salvo no modelo de dados da turma.
     */
    public Integer getSemester() {
     
        return semester;
    }

    /**
     * Método Set.
     * @param semester Semestre fornecido.
     */
    public void setSemester(Integer semester) {
     
        this.semester = semester;
    }
}