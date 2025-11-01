package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseTest {
    private CourseParameters mockParameters;
    private Professor mockTgCoordinator;
    private Professor mockCourseCoordinator;

    @BeforeEach
    void setUp() {
        mockParameters = mock(CourseParameters.class);
        mockTgCoordinator = mock(Professor.class);
        mockCourseCoordinator = mock(Professor.class);
    }

    @Test
    @DisplayName("Deve criar curso com dados válidos.")
    void shouldCreateCourseWithValidData() {
        String name = "Nome de Teste";

        when(mockTgCoordinator.canBeTgCoordinator()).thenReturn(true);
        when(mockCourseCoordinator.canBeCourseCoordinator())
                .thenReturn(true);

        var course = new Course(
                name,
                mockParameters,
                mockTgCoordinator,
                mockCourseCoordinator
        );

        assertNotNull(course);
        assertEquals(name, course.getName());
        assertEquals(mockParameters, course.getParameters());
        assertEquals(mockTgCoordinator, course.getTgCoordinator());
        assertEquals(mockCourseCoordinator, course.getCourseCoordinator());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é "
                    + "nulo"
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsNull() {
        var course = new Course();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> course.setName(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é "
                    + "vazio"
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsEmpty() {
        var course = new Course();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> course.setName(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set parameters deve lançar exceção de domínio quando parâmetros"
                    + "são nulos."
    )
    void inSetParametersShouldThrowDomainExceptionWhenParametersAreNull() {
        var course = new Course();
        String message = "O campo parâmetros (turnos/disciplinas) são "
                + "obrigatórios.";

        var exception = assertThrows(DomainException.class,
                () -> course.setParameters(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set tg coordinator deve lançar exceção de domínio quando "
                    + "professor de TG é nulo."
    )
    void inSetTgCoordinatorShouldThrowDomainExceptionWhenTgCoordinatorIsNull() {
        var course = new Course();
        String message = "O campo coordenador de TG é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> course.setTgCoordinator(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set tg coordinator deve lançar exceção de domínio quando "
                    + "professor não é coordenador de TG."
    )
    void inSetTgCoordinatorShouldThrowDomainExceptionWhenProfessorIsNotTgCoordinator() {
        var course = new Course();
        String message = "O professor não tem permissão de Coordenador de TG.";

        when(mockTgCoordinator.canBeTgCoordinator()).thenReturn(false);

        var exception = assertThrows(DomainException.class,
                () -> course.setTgCoordinator(mockTgCoordinator));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set course coordinator deve lançar exceção de domínio quando "
                    + "professor de curso é nulo."
    )
    void inSetCourseCoordinatorShouldThrowDomainExceptionWhenCourseCoordinatorIsNull() {
        var course = new Course();
        String message = "O campo coordenador de curso é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> course.setCourseCoordinator(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set course coordinator deve lançar exceção de domínio quando "
                    + "professor não é coordenador de curso."
    )
    void inSetCourseCoordinatorShouldThrowDomainExceptionWhenProfessorIsNotCourseCoordinator() {
        var course = new Course();
        String message = "O professor não tem permissão de Coordenador de "
                + "Curso.";

        when(mockCourseCoordinator.canBeCourseCoordinator())
                .thenReturn(false);

        var exception = assertThrows(DomainException.class,
                () -> course.setCourseCoordinator(mockCourseCoordinator));

        assertEquals(message, exception.getMessage());
    }
}