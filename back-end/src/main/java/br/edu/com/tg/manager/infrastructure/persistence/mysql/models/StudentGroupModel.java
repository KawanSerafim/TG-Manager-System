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

/**
 * Modelo de dados.
 * Representa a entidade de domínio StudentGroup, sendo a sua versão no banco de
 * dados, que será manipulado pelo JPA.
 * A classe, por fazer parte da infrastructure, permite influência de
 * ferramentas externas.
 * 
 * -------------------
 * 
 * Anotações do JPA:
 * 
 * - Anotação @Entity: indica ao JPA que esta classe é uma entidade.
 * 
 * - Anotação @Table: indica ao JPA que esta classe é uma tabela do banco
 * de dados. Dentro do parênteses, o valor atruído a variável 'name' será o
 * nome da tabela.
 * 
 * - Anotação @Id: indica ao JPA que a variável determina o ID da tabela.
 * 
 * - Anotação @GeneratedValue: indica ao JPA como o valor da variável
 * será gerada.
 * 
 * - Anotação @Column: indica ao JPA que esta variável será uma coluna da
 * tabela. Dentro do parênteses, o valor booleano a variável 'nullable' 
 * determina se a coluna poderá conter valores nulos ou não. E na
 * 'unique', determina se o valor da coluna não pode se repetir.
 * 
 * - Anotação @Enumerated: indica ao JPA que o campo é do tipo Enum e deve
 * ser persistido no banco de dados. Porém, por padrão, ele é organizado através
 * de uma enumeração numérica, o que gera um risco em potencial caso os números
 * sejam alterados futuramente. Logo, o tipo (EnumType) deve ser determinado
 * como um String.
 * 
 * - Anotação @ManyToOne: indica ao JPA que esta classe faz parte de uma
 * relação de muitos para um, onde a classe é o muitos da relação, e a
 * instância o um.
 * 
 * - Anotação @JoinColumn: indica ao JPA o nome da coluna de chave estrangeira 
 * nesta tabela. A variável 'name' determina o nome da coluna em que o ID será 
 * salvo.
 */
@Entity
@Table(name = "student_groups")
public class StudentGroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* IMPORTANTE! Sempre que for precisar do curso através da turma, utilize
     * o get, pois o método de busca LAZY indica que ao buscar os dados da
     * turma, o objeto do curso não esteja inicializado. Uma tentativa de 
     * utilizar o curso sem o get resulta em LazyInitializationException.
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

    /* Necessário para manter o snake_case na nomeação das colunas. */
    @Column(name = "temporary_password", nullable = false)
    private String temporaryPassword;

    public StudentGroupModel() {}

    public Long getId() {
     
        return id;
    }

    public void setId(Long id) {
     
        this.id = id;
    }

    public CourseModel getCourse() {
     
        return course;
    }

    public void setCourse(CourseModel course) {
     
        this.course = course;
    }

    public Discipline getDiscipline() {
     
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
     
        this.discipline = discipline;
    }

    public Integer getYear() {
     
        return year;
    }

    public void setYear(Integer year) {
     
        this.year = year;
    }

    public Integer getSemester() {
     
        return semester;
    }

    public void setSemester(Integer semester) {
     
        this.semester = semester;
    }

    public String getTemporaryPassword() {
     
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
     
        this.temporaryPassword = temporaryPassword;
    }
}