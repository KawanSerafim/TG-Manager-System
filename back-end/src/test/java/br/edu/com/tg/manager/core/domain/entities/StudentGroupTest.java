package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentGroupTest {
    private Course mockCourse;
    private Integer year;
    private Integer semester;

    @BeforeEach
    public void setUp() {
        mockCourse = mock(Course.class);
        year = 2025;
        semester = 2;
    }

    @Test
    @DisplayName("Deve criar turma com dados válidos.")
    void shouldCreateStudentGroupWithValidData() {
        when(mockCourse.getAvailableDisciplines())
                .thenReturn(Collections.singletonList(Discipline.TG1));
        when(mockCourse.getAvailableShifts())
                .thenReturn(Collections.singletonList(CourseShift.NIGHT));

        StudentGroup studentGroup = new StudentGroup(
                mockCourse,
                Discipline.TG1,
                year,
                semester,
                CourseShift.NIGHT
        );

        assertNotNull(studentGroup);
        assertEquals(mockCourse, studentGroup.getCourse());
        assertEquals(Discipline.TG1, studentGroup.getDiscipline());
        assertEquals(year, studentGroup.getYear());
        assertEquals(semester, studentGroup.getSemester());
        assertEquals(CourseShift.NIGHT, studentGroup.getCourseShift());
    }

    @Test
    @DisplayName(
            "Em set course deve lançar exceção de domínio quando curso é nulo."
    )
    void inSetCourseShouldThrowDomainExceptionWhenCourseIsNull() {
        var studentGroup = new StudentGroup();
        String message = "O campo curso é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setCourse(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set discipline deve lançar exceção de domínio quando curso é "
                    + "nulo."
    )
    void inSetDisciplineShouldThrowDomainExceptionWhenCourseIsNull() {
        var studentGroup = new StudentGroup();
        String message = "Impossível validar a disciplina com curso nulo.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setDiscipline(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set discipline deve lançar exceção de domínio quando disciplina"
                    + " é nula."
    )
    void inSetDisciplineShouldThrowDomainExceptionWhenDisciplineIsNull() {
        var studentGroup = new StudentGroup();
        var course = new Course();
        studentGroup.setCourse(course);

        String message = "O campo disciplina de TG é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setDiscipline(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set discipline deve lançar exceção de domínio quando disciplina"
                    + " não é suportada."
    )
    void inSetDisciplineShouldThrowDomainExceptionWhenDisciplineIsNotSupported() {
        var studentGroup = new StudentGroup();
        studentGroup.setCourse(mockCourse);

        String message = "A disciplina [" + Discipline.TG1 + "] não é oferecida"
                + " pelo curso.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setDiscipline(Discipline.TG1));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set year deve lançar exceção de domínio quando ano é nulo."
    )
    void inSetYearShouldThrowDomainExceptionWhenYearIsNull() {
        var studentGroup = new StudentGroup();
        String message = "O campo ano é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setYear(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set year deve lançar exceção de domínio quando ano não é o "
                    + "atual."
    )
    void inSetYearShouldThrowDomainExceptionWhenYearIsNotTheCurrentOne() {
        var studentGroup = new StudentGroup();
        String message = "O ano não pode ser diferente do atual.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setYear(2018));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set semester deve lançar exceção de domínio quando semestre é "
                    + "nulo."
    )
    void inSetSemesterShouldThrowDomainExceptionWhenSemesterIsNull() {
        var studentGroup = new StudentGroup();
        String message = "O campo semestre é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setSemester(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set semester deve lançar exceção de domínio quando semestre "
                    + "não é o atual."
    )
    void inSetSemesterShouldThrowDomainExceptionWhenSemesterIsNotTheCurrentOne() {
        var studentGroup = new StudentGroup();
        String message = "O semestre foge do padrão: 1 ou 2.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setSemester(3));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set course shift deve lançar exceção de domínio quando curso é "
                    + "nulo."
    )
    void inSetCourseShiftShouldThrowDomainExceptionWhenCourseIsNull() {
        var studentGroup = new StudentGroup();
        String message = "Impossível validar o turno com curso nulo.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setCourseShift(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set course shift deve lançar exceção de domínio quando "
                    + "curso é nulo."
    )
    void inSetCourseShiftShouldThrowDomainExceptionWhenCourseShiftIsNull() {
        var studentGroup = new StudentGroup();
        var course = new Course();
        studentGroup.setCourse(course);

        String message = "O campo turno do curso é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setCourseShift(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set course shift deve lançar exceção de domínio quando "
                    + "curso não é suportado."
    )
    void inSetCourseShiftShouldThrowDomainExceptionWhenCourseShiftIsNotSupported() {
        var studentGroup = new StudentGroup();
        studentGroup.setCourse(mockCourse);

        String message = "O turno [" + CourseShift.NIGHT + "] não é oferecido"
                + " pelo curso.";

        var exception = assertThrows(DomainException.class,
                () -> studentGroup.setCourseShift(CourseShift.NIGHT));

        assertEquals(message, exception.getMessage());
    }
}