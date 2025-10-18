package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class StudentTest {

    private StudentGroup mockStudentGroup;

    @BeforeEach
    void setUp() {

        mockStudentGroup = mock(StudentGroup.class);
    }

    @Test
    @DisplayName("Deve criar pré-aluno com dados válidos.")
    void shouldCreatePreStudentWithValidData() {

        // Act.
        var student = new Student(

            "Kawan",
            "111111",
            mockStudentGroup
        );

        // Assert.
        assertNotNull(student);
        assertNull(student.getUserAccount());
        assertEquals("Kawan", student.getName());
        assertEquals("111111", student.getRegistration());
        assertEquals(mockStudentGroup, student.getStudentGroup());
        assertEquals(StudentStatus.PRE_REGISTRATION, student.getStatus());
    }

    @Test
    @DisplayName("Deve finalizar cadastro com dados válidos.")
    void shouldFinalizeRegistrationWithValidData() {

        // Arrange.
        var student = new Student(

            "Kawan",
            "111111",
            mockStudentGroup
        );

        var userAccount = new UserAccount(

            "kawan@teste.com",
            "senha 123"
        );

        // Act.
        student.finalizeRegistration(userAccount);

        // Assert.
        assertNotNull(student.getUserAccount());
        assertEquals(StudentStatus.ACTIVE, student.getStatus());
    }

    @Test
    @DisplayName(

        "Deve lançar exceção de domínio quando finaliza cadastro com status " +
        "inválido."
    )
    void shouldThrowDomainExceptionWhenFinalizeRegistrationWithInvalidStatus() {

        // Arrange.
        var student = new Student();
        var userAccount = mock(UserAccount.class);

        var exception = assertThrows(DomainException.class, () ->

            student.finalizeRegistration(userAccount)
        );

        assertEquals(

            "O aluno não tem o estado válido para ação.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve pegar e definir ID.")
    void shouldGetAndSetId() {

        // Arrange.
        var student = new Student();

        // Act.
        student.setId(1L);
        Long id = student.getId();

        // Assert.
        assertEquals(id, student.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando nome é nulo.")
    void shouldThrowDomainExceptionWhenNameIsNull() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setName(null)
        );

        // Assert.
        assertEquals(
            "O campo nome é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando nome é vazio.")
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setName("")
        );

        // Assert.
        assertEquals(

            "O campo nome é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando matrícula é nula.")
    void shouldThrowDomainExceptionWhenRegistrationIsNull() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setRegistration(null)
        );

        // Assert.
        assertEquals(

            "O campo matrícula é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando matrícula é vazia.")
    void shouldThrowDomainExceptionWhenRegistrationIsEmpty() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setRegistration("")
        );

        // Assert.
        assertEquals(

            "O campo matrícula é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(

        "Deve lançar exceção de domínio quando conta de usuário é nulo."
    )
    void shouldThrowDomainExceptionWhenUserAccountIsNull() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setUserAccount(null)
        );

        // Assert.
        assertEquals(

            "O campo conta de usuário é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve pegar e definir email.")
    void shouldGetEmail() {

        // Arrange.
        var student = new Student();
        var userAccount = new UserAccount();
        student.setUserAccount(userAccount);

        // Act.
        student.setEmail("kawan@teste.com");
        String email = student.getEmail();

        // Assert.
        assertEquals(email, student.getEmail());
    }

    @Test
    @DisplayName("Deve pegar e definir senha.")
    void shouldGetAndSetPassword() {

        // Arrange.
        var student = new Student();
        var userAccount = new UserAccount();
        student.setUserAccount(userAccount);

        // Act.
        student.setPassword("senha 123");
        var password = student.getPassword();

        // Assert.
        assertEquals(password, student.getPassword());
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando status é nulo.")
    void shouldThrowDomainExceptionWhenStatusIsNull() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setStatus(null)
        );

        // Assert.
        assertEquals(

            "O campo status é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando turma é nula.")
    void shouldThrowDomainExceptionWhenStudentGroupIsNull() {

        // Arrange.
        var student = new Student();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            student.setStudentGroup(null)
        );

        // Assert.
        assertEquals(

            "O campo turma é obrigatório.",
            exception.getMessage()
        );
    }
}