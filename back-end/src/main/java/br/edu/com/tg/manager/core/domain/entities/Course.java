package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;

import java.util.List;

/**
 * Entidade de domínio:
 * Representa um curso da instituição, com seus respectivos
 * parâmetros (turnos e disciplinas de TG) e professores coordenadores.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public class Course {
    private Long id;
    private String name;
    private CourseParameters parameters;
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
     * @param parameters Parâmetros do curso (turnos e disciplinas).
     * @param tgCoordinator Coordenador de TG do curso.
     * @param courseCoordinator Coordenador do curso.
     */
    public Course(
            String name,
            CourseParameters parameters,
            Professor tgCoordinator,
            Professor courseCoordinator
    ) {
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setName(name);
        this.setParameters(parameters);
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

    public CourseParameters getParameters() {
        return parameters;
    }

    public void setParameters(CourseParameters parameters) {
        // Regra de domínio: o campo parâmetros é obrigatório.
        if(parameters == null) {
            throw new DomainException(
                    "O campo parâmetros (turnos/disciplinas) são obrigatórios."
            );
        }
        this.parameters = parameters;
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

    // MÉTODOS DE DELEGAÇÃO.

    /**
     * Método Get (DELEGAÇÃO).
     * @return Lista de turnos disponíveis.
     */
    public List<CourseShift> getAvailableShifts() {
        return this.parameters.getAvailableShifts();
    }

    /**
     * Método Get (DELEGAÇÃO).
     * @return Lista de disciplinas de TG disponíveis.
     */
    public List<Discipline> getAvailableDisciplines() {
        return this.parameters.getAvailableDisciplines();
    }
}