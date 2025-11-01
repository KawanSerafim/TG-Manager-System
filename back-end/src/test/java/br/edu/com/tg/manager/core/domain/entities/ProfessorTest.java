package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ProfessorTest {
    private Professor professor;
    private UserAccount mockUserAccount;
    private String message;

    @BeforeEach
    public void setUp() {
        mockUserAccount = mock(UserAccount.class);
    }

    @Test
    @DisplayName("Deve criar professor com dados válidos.")
    void shouldCreateProfessorWithValidData() {
        String name = "Nome de Teste";
        String registration = "Matrícula de Teste";

        var professor = new Professor(
                name,
                registration,
                mockUserAccount,
                ProfessorRole.ADVISOR
        );

        assertNotNull(professor);
        assertEquals(name, professor.getName());
        assertEquals(registration, professor.getRegistration());
        assertEquals(mockUserAccount, professor.getUserAccount());
        assertEquals(ProfessorRole.ADVISOR, professor.getRole());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é nulo."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsNull() {
        professor = new Professor();
        message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setName(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é vazio."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsEmpty() {
        professor = new Professor();
        message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setName(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set registration deve lançar exceção de domínio quando "
            + "matrícula é nula."
    )
    void inSetRegistrationShouldThrowDomainExceptionWhenRegistrationIsNull() {
        professor = new Professor();
        message = "O campo matrícula é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setRegistration(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set registration deve lançar exceção de domínio quando "
            + "matrícula é vazia."
    )
    void inSetRegistrationShouldThrowDomainExceptionWhenRegistrationIsEmpty() {
        professor = new Professor();
        message = "O campo matrícula é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setRegistration(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set user account deve lançar exceção de domínio quando conta de"
            + "usuário é nula."
    )
    void inSetUserAccountShouldThrowDomainExceptionWhenUserAccountIsNull() {
        professor = new Professor();
        message = "O campo conta de usuário é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setUserAccount(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set role deve lançar exceção de domínio quando cargo é vazio."
    )
    void inSetRoleShouldThrowDomainExceptionWhenRoleIsNull() {
        professor = new Professor();
        message = "O campo cargo é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> professor.setRole(null));

        assertEquals(message, exception.getMessage());
    }
}