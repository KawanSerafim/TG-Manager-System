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

/**
 * Modelo de dados.
 * Representa a entidade de domínio Course, sendo a sua versão no banco de
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
 * - Anotação @OneToOne: indica ao JPA que esta classe faz parte de uma
 * relação de um para um.
 * 
 * - Anotação @JoinColumn: indica ao JPA aonde alocar o ID na outra tabela 
 * relacionada. A variável 'name' determina o nome da coluna em que o ID
 * será salvo.
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

    public CourseModel() {}

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
     
        this.name = name;
    }

    public CourseShift getShift() {
     
        return shift;
    }

    public void setShift(CourseShift shift) {
     
        this.shift = shift;
    }

    public ProfessorModel getTgCoordinator() {
     
        return tgCoordinator;
    }

    public void setTgCoordinator(ProfessorModel tgCoordinator) {
     
        this.tgCoordinator = tgCoordinator;
    }

    public ProfessorModel getCourseCoordinator() {
     
        return courseCoordinator;
    }

    public void setCourseCoordinator(ProfessorModel courseCoordinator) {
     
        this.courseCoordinator = courseCoordinator;
    }
}