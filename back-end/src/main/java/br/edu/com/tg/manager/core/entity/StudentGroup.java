package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;

/**
 * Entidade de domínio.
 * Representa uma classe de domínio que representa a turma
 * da FATEC.
 * A classe, por fazer parte do core, é pura.
 */
public class StudentGroup {

    private Long id;
    private Integer year;
    private Integer semester;
    private Course course;

    /* Construtor vazio. Necessário para os Mappers. */
    public StudentGroup(){}

    /**
     * Construtor para construir uma turma.
     * @param course O objeto que representa um curso.
     * @param year A variável que representa o ano da turma.
     * @param semester A variável que representa o semestre da turma.
     */
    public StudentGroup(Course course, Integer year, Integer semester) {

        /* Delega a inserção do objeto 'course' e das
         * variáveis 'year' e 'semester' aos sets
         * responsáveis.
         */
        this.setCourse(course);
        this.setYear(year);
        this.setSemester(semester);
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Integer getYear() {
     
        return year;
    }

    public void setYear(Integer year) {
        
        /* Regra de negócio: o ano da turma não pode ser vazio
         * e não pode ser anterior à 2025.
         */
        if(year == null || year < 2025) {

            throw new DomainException("Ano da turma inválido.");
        }

        this.year = year;
    }

    public Integer getSemester() {

        return semester;
    }

    public void setSemester(Integer semester) {
        
        /* Regra de negócio: o semestre da turma não pode ser nada
         * além de '1' ou '2'.
         */
        if(semester == null || (semester != 1 && semester != 2)) {

            throw new DomainException("Semestre da turma inválido.");
        }

        this.semester = semester;
    }

    public Course getCourse() {
     
        return course;
    }
    
    public void setCourse(Course course) {
        
        /* Regra de negócio: o curso não pode ser nulo. A turma necessita
         * estar associada a um curso.
         */
        if(course == null) {

            throw new DomainException("A turma deve estar associada a um curso.");
        }

        this.course = course;
    }   
}