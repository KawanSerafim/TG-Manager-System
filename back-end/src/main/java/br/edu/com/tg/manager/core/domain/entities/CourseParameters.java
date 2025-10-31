package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import java.util.List;

/**
 * Entidade de domínio:
 * Contém os parâmetros específicos de um curso, como turnos
 * disponíveis e disciplinas de TG aplicáveis.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public class CourseParameters {
    private List<CourseShift> availableShifts;
    private List<Discipline> availableDisciplines;

    /**
     * Construtor vazio:
     * Necessário para frameworks de persistência.
     */
    public CourseParameters() {}

    /**
     * Construtor de domínio:
     * Cria um novo objeto CourseParameters e garante que as listas
     * sejam válidas.
     * @param availableShifts Lista de turnos disponíveis.
     * @param availableDisciplines Lista de disciplinas de TG disponíveis.
     */
    public CourseParameters(
            List<CourseShift> availableShifts,
            List<Discipline> availableDisciplines
    ) {
        // Delega as validações dos parâmetros aos seus devidos setters.
        this.setAvailableShifts(availableShifts);
        this.setAvailableDisciplines(availableDisciplines);
    }

    // MÉTODOS GETTERS E SETTERS.

    public List<CourseShift> getAvailableShifts() {
        return availableShifts;
    }

    public void setAvailableShifts(List<CourseShift> availableShifts) {
        if(availableShifts == null ||  availableShifts.isEmpty()) {
            // Regra de domínio: o curso deve ter pelo menos um turno.
            throw new DomainException(
                    "O curso deve ter pelo menos um turno disponível."
            );
        }
        this.availableShifts = availableShifts;
    }

    public List<Discipline> getAvailableDisciplines() {
        return availableDisciplines;
    }

    public void setAvailableDisciplines(List<Discipline> availableDisciplines) {
        // Regra de domínio: o curso deve ter pelo menos uma disciplina.
        if(availableDisciplines == null ||  availableDisciplines.isEmpty()) {
            throw new DomainException(
                    "O curso deve ter pelo menos uma disciplina disponível."
            );
        }
        this.availableDisciplines = availableDisciplines;
    }
}