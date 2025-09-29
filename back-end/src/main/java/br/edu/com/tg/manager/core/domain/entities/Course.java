package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um curso num determinado turno (manhã, tarde ou noite).
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * classe pura.
 */
public class Course {

    private Long id;
    private String name;
    private CourseShift shift;
    private Professor tgCoordinator;
    private Professor courseCoordinator;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public Course() {}

    /**
     * Construtor de negócio:
     * Cria um novo objeto de Course e garante que o objeto seja criado num
     * estado válido.
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

        // Delega as validações dos parâmetros aos seus devidos métodos Set.
        this.setName(name);
        this.setShift(shift);
        this.setTgCoordinator(tgCoordinator);
        this.setCourseCoordinator(courseCoordinator);
    }

    /**
     * Método Get.
     * @return ID do curso.
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
     * @return Nome do curso.
     */
    public String getName() {
        
        return name;
    }

    /**
     * Método Set.
     * @param name Nome fornecido.
     */
    public void setName(String name) {
        
        // Regra de negócio: curso não pode conter nome vazio ou nulo.
        if(name == null || name.trim().isEmpty()) {

            throw new DomainException(

                "O campo nome é obrigatório."
            );
        }

        this.name = name;
    }

    /**
     * Método Get.
     * @return Turno do curso.
     */
    public CourseShift getShift() {
     
        return shift;
    }

    /**
     * Método Set.
     * @param shift Turno fornecido.
     */
    public void setShift(CourseShift shift) {
        
        // Regra de negócio: curso não pode conter turno nulo.
        if(shift == null) {

            throw new DomainException(
                
                "O campo turno é obrigatório."
            );
        }

        this.shift = shift;
    }

    /**
     * Método Get.
     * @return Coordenador de TG do curso.
     */
    public Professor getTgCoordinator() {
     
        return tgCoordinator;
    }

    /**
     * Método Set.
     * @param tgCoordinator Coordenador de TG fornecido.
     */
    public void setTgCoordinator(Professor tgCoordinator) {
        
        // Regra de negócio: curso não pode conter coordenador de TG nulo.
        if(tgCoordinator == null) {

            throw new DomainException(
                
                "O campo coordenador de TG é obrigatório."
            );
        }

        /*
         * Regra de negócio: o professor deve ter permissões de coordenador
         * de TG.
         */
        if(!(tgCoordinator.getRole().hasTgCoordinatorPermissions())) {

            throw new DomainException(

                "O professor não tem permissão de Coordenador de TG."
            );
        }

        this.tgCoordinator = tgCoordinator;
    }

    /**
     * Método Get.
     * @return Coordenador do curso.
     */
    public Professor getCourseCoordinator() {
     
        return courseCoordinator;
    }

    /**
     * Método Set.
     * @param courseCoordinator Coordenador de curso fornecido.
     */
    public void setCourseCoordinator(Professor courseCoordinator) {

        // Regra de negócio: curso não pode conter coordenador de curso nulo.
        if(courseCoordinator == null) {

            throw new DomainException(
                
                "O campo coordenador de curso é obrigatório."
            );
        }

        /*
         * Regra de negócio: o professor deve ter permissões de coordenador
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