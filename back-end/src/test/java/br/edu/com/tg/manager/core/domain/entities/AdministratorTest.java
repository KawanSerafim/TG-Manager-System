package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AdministratorTest {
    @Test
    @DisplayName("Deve criar administrador com dados válidos.")
    void shouldCreateAdministratorWithValidData() {
        var mockUserAccount = mock(UserAccount.class);
        String name = "Nome de Teste";

        var administrator = new Administrator(name, mockUserAccount);

        assertNotNull(administrator);
        assertEquals(name, administrator.getName());
        assertEquals(mockUserAccount, administrator.getUserAccount());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é "
                    + "nulo."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsNull() {
        var administrator = new Administrator();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> administrator.setName(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set name deve lançar exceção de domínio quando nome é "
                    + "vazio."
    )
    void inSetNameShouldThrowDomainExceptionWhenNameIsEmpty() {
        var administrator = new Administrator();
        String message = "O campo nome é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> administrator.setName(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set user account deve lançar exceção de domínio quando conta de"
                    + "usuário é nula."
    )
    void inSetUserAccountShouldThrowDomainExceptionWhenUserAccountIsNull() {
        var administrator = new Administrator();
        String message = "O campo conta de usuário é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> administrator.setUserAccount(null));

        assertEquals(message, exception.getMessage());
    }
}