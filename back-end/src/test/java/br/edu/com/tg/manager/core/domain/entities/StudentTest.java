package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentTest {
    private String name;
    private String registration;

    @BeforeEach
    void setUp() {
        name = "Nome de Teste";
        registration = "Matrícula  de teste";
    }

    @Test
    @DisplayName("Deve criar aluno com dados válidos.")
    void shouldCreateStudentWithValidData() {
        List<StudentGroup> studentGroups = new ArrayList<>();
        var studentGroup = new StudentGroup();
        studentGroups.add(studentGroup);

        var student = new Student(name, registration, studentGroup);

        assertNotNull(student);
        assertEquals(name, student.getName());
        assertEquals(registration, student.getRegistration());
        assertEquals(studentGroups, student.getStudentGroups());
    }

    @Test
    @DisplayName("Deve completar cadastro com dados válidos.")
    void shouldCompleteRegistrationWithValidData() {
        var student = new Student();
        var userAccount = mock(UserAccount.class);

        when(userAccount.getStatus())
                .thenReturn(UserAccountStatus.EMAIL_CONFIRMED);

        student.completeRegistration(userAccount);

        assertEquals(userAccount, student.getUserAccount());
        assertEquals(
                UserAccountStatus.EMAIL_CONFIRMED,
                student.getUserAccountStatus()
        );
        assertEquals(StudentStatus.REGISTERED, student.getStatus());
    }

    @Test
    @DisplayName(
            "Em matricular em turma deve lançar exceção de domínio quando "
            + "turma é vazia."
    )
    void inEnrollInGroupShouldThrowDomainExceptionWhenStudentGroupIsNull() {
        String message = "O campo turma é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> new Student(name, registration, null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em matricular em turma deve lançar exceção de domínio quando "
            + "turma é repetida."
    )
    void inEnrollInGroupShouldThrowDomainExceptionWhenStudentGroupIsRepeated() {
        var studentGroup = new StudentGroup();
        var student = new Student(name, registration, studentGroup);
        String message = "O aluno já está matrículado nesta turma.";

        var exception = assertThrows(DomainException.class,
                () -> student.enrollInGroup(studentGroup));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em completar cadastro deve lançar exceção de domínio quando "
            + "status da conta de usuário não é email confirmado."
    )
    void inCompleteRegistrationShouldThrowDomainExceptionWhenUserAccountStatusIsNotEmailConfirmed() {
        var student = new Student();
        var mockUserAccount = mock(UserAccount.class);
        String message = "A conta de usuário não tem o estado válido para a "
                + "ação.";

        when(mockUserAccount.getStatus()).thenReturn(UserAccountStatus.ACTIVE);

        var exception = assertThrows(DomainException.class,
                () -> student.completeRegistration(mockUserAccount));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é nulo."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsNull() {
        var student = new Student();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setName(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é vazio."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsEmpty() {
        var student = new Student();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setName(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set registration deve lançar exceção de domínio quando "
            + " matrícula é nula."
    )
    void inSetRegistrationShouldThrowDomainExceptionWhenRegistrationIsNull() {
        var student = new Student();
        String message = "O campo matrícula é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setRegistration(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set registration deve lançar exceção de domínio quando "
            + " matrícula é vazia."
    )
    void inSetRegistrationShouldThrowDomainExceptionWhenRegistrationIsEmpty() {
        var student = new Student();
        String message = "O campo matrícula é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setRegistration(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set status deve lançar exceção de domínio quando status é nulo."
    )
    void inSetStatusShouldThrowDomainExceptionWhenStatusIsNull() {
        var student = new Student();
        String message = "O campo status é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setStatus(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set student group deve lançar exceção de domínio quando turma"
            + " é nula."
    )
    void inSetStudentGroupShouldThrowDomainExceptionWhenStudentGroupIsNull() {
        var student = new Student();
        String message = "O campo turmas é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> student.setStudentGroups(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set email deve lançar exceção de domínio quando conta de "
            + "usuário é nula."
    )
    void inSetEmailShouldThrowDomainExceptionWhenUserAccountIsNull() {
        var student = new Student();
        String message = "Ação impossível com conta de usuário nula.";

        var exception = assertThrows(DomainException.class,
                () -> student.setEmail("email"));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set password deve lançar exceção de domínio quando conta de "
            + "usuário é nula."
    )
    void inSetPasswordShouldThrowDomainExceptionWhenUserAccountIsNull() {
        var student = new Student();
        String message = "Ação impossível com conta de usuário nula.";

        var exception = assertThrows(DomainException.class,
                () -> student.setPassword("email"));

        assertEquals(message, exception.getMessage());
    }
}