package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CourseParametersTest {
    private final List<CourseShift> availableShifts = new ArrayList<>();
    private final List<Discipline> availableDisciplines = new ArrayList<>();

    @Test
    @DisplayName("Deve criar parâmetros de curso com dados válidos.")
    void shouldCreateCourseParametersWithValidData() {
        availableShifts.add(CourseShift.NIGHT);
        availableDisciplines.add(Discipline.TG1);

        var courseParameters = new CourseParameters(
                availableShifts,
                availableDisciplines
        );

        assertNotNull(courseParameters);
        assertEquals(availableShifts, courseParameters.getAvailableShifts());
        assertEquals(
                availableDisciplines,
                courseParameters.getAvailableDisciplines()
        );
    }

    @Test
    @DisplayName(
            "Em set available shifts deve lançar exceção de domínio quando "
            + "turnos disponíveis estão nulos."
    )
    void inSetAvailableShiftsShouldThrowDomainExceptionWhenAvailableShiftsAreNull() {
        var courseParameters = new CourseParameters();
        String message = "O curso deve ter pelo menos um turno disponível.";

        var exception = assertThrows(DomainException.class,
                () -> courseParameters.setAvailableShifts(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set available shifts deve lançar exceção de domínio quando "
            + "turnos disponíveis estão vazios."
    )
    void inSetAvailableShiftsShouldThrowDomainExceptionWhenAvailableShiftsAreEmpty() {
        var courseParameters = new CourseParameters();
        String message = "O curso deve ter pelo menos um turno disponível.";

        var exception = assertThrows(DomainException.class,
                () -> courseParameters.setAvailableShifts(availableShifts));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set available disciplines deve lançar exceção de domínio "
            + "quando disciplinas disponíveis estão nulos."
    )
    void inSetAvailableDisciplinesShouldThrowDomainExceptionWhenAvailableDisciplinesAreNull() {
        var courseParameters = new CourseParameters();
        String message = "O curso deve ter pelo menos uma disciplina "
                + "disponível.";

        var exception = assertThrows(DomainException.class,
                () -> courseParameters.setAvailableDisciplines(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set available disciplines deve lançar exceção de domínio "
            + "quando disciplinas disponíveis estão vazios."
    )
    void inSetAvailableDisciplinesShouldThrowDomainExceptionWhenAvailableDisciplinesAreEmpty() {
        var courseParameters = new CourseParameters();
        String message = "O curso deve ter pelo menos uma disciplina "
                + "disponível.";

        var exception = assertThrows(DomainException.class,
                () -> courseParameters.setAvailableDisciplines(
                        availableDisciplines
                ));

        assertEquals(message, exception.getMessage());
    }
}