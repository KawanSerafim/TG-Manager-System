package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio.
 * Representa um curso em determinado turno, podendo ser manhã, tarde ou noite.
 * A classe, por fazer parte do core, é pura.
 */
public class Course {

    private Long id;
    private String name;
    private CourseShift shift;
    private Professor tgCoordinator;
    private Professor courseCoordinator;

    /**
     * Construtor vazio.
     * Necessário para frameworks de persistência.
     */
    public Course() {}

    /**
     * Construtor de negócio para criar um novo objeto de Course.
     * Garante que o objeto seja criado em um estado válido.
     * @param name Nome do curso.
     * @param shift Turno do curso.
     * @param tgCoordinator Coordenador de TG do curso.
     * @param courseCoordinator Coordenador do curso.
     */
    public Course(
        
        String name, 
        CourseShift shift,
        Professor tgCoordinator,
        Professor courseCoordinator
    ) {
        
        this.setName(name);
        this.setShift(shift);
        this.setTgCoordinator(tgCoordinator);
        this.setCourseCoordinator(courseCoordinator);
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
        
        /* Regra de negócio: curso não pode conter nome vazio ou nulo. */
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }

        this.name = name;
    }

    public CourseShift getShift() {
     
        return shift;
    }

    public void setShift(CourseShift shift) {
        
        /* Regra de negócio: curso não pode conter turno nulo. */
        if(shift == null) {

            throw new DomainException(
                
                "O campo turno é obrigatório."
            );
        }

        this.shift = shift;
    }

    public Professor getTgCoordinator() {
     
        return tgCoordinator;
    }

    public void setTgCoordinator(Professor tgCoordinator) {
        
        /* Regra de negócio: curso não pode conter coordenador de TG nulo. */
        if(tgCoordinator == null) {

            throw new DomainException(
                
                "O campo coordenador de TG é obrigatório."
            );
        }

        /* Regra de negócio: o professor deve ter permissões de coordenador
         * de TG.
         */
        if(!(tgCoordinator.getRole().hasTgCoordinatorPermissions())) {

            throw new DomainException(

                "O professor não tem permissão de Coordenador de TG."
            );
        }

        this.tgCoordinator = tgCoordinator;
    }

    public Professor getCourseCoordinator() {
     
        return courseCoordinator;
    }

    public void setCourseCoordinator(Professor courseCoordinator) {
        
        /* Regra de negócio: curso não pode conter coordenador de curso nulo. */
        if(courseCoordinator == null) {

            throw new DomainException(
                
                "O campo coordenador de curso é obrigatório."
            );
        }

        /* Regra de negócio: o professor deve ter permissões de coordenador
         * de curso.
         */
        if(!(courseCoordinator.getRole().hasCourseCoordinatorPermissions())) {

            throw new DomainException(
            
                "O professor não tem permissão de Coordenador de Curso."
            );
        }

        this.courseCoordinator = courseCoordinator;
    }
}