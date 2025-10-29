package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

/**
 * Entidade de domínio:
 * Representa um curso da instituição, com seu respectivo turno e
 * professores coordenadores.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
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
     * Construtor de domínio:
     * Cria um novo objeto de Course e garante que o objeto seja
     * criado num estado válido.
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
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setShift(shift);
        this.setTgCoordinator(tgCoordinator);
        this.setCourseCoordinator(courseCoordinator);
    }

    // MÉTODOS GETTERS E SETTERS.

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
        // Regra de domínio: o campo nome é obrigatório.
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
        // Regra de domínio: o campo turno é obrigatório.
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
        // Regra de domínio: campo coordenador de TG é obrigatório.
        if(tgCoordinator == null) {
            throw new DomainException(
                    "O campo coordenador de TG é obrigatório."
            );
        }

        /*
         * Regra de domínio: o professor deve ter permissões de coordenador
         * de TG.
         */
        if(!(tgCoordinator.canBeTgCoordinator())) {
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
        // Regra de domínio: o campo coordenador de curso é obrigatório.
        if(courseCoordinator == null) {
            throw new DomainException(
                    "O campo coordenador de curso é obrigatório."
            );
        }

        /*
         * Regra de domínio: o professor deve ter permissões de coordenador
         * de curso.
         */
        if(!(courseCoordinator.canBeCourseCoordinator())) {
            throw new DomainException(
                    "O professor não tem permissão de Coordenador de Curso."
            );
        }
        this.courseCoordinator = courseCoordinator;
    }
}