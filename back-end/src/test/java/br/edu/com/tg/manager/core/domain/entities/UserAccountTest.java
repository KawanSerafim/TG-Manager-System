package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {
    @Test
    @DisplayName("Deve criar conta de usuário com dados válidos.")
    void shouldCreateUserAccountWithValidData() {
        String email = "email@teste";
        String password = "password";

        var userAccount = new UserAccount(email, password);

        assertNotNull(userAccount);
        assertEquals(email, userAccount.getEmail());
        assertEquals(password, userAccount.getPassword());
    }

    @Test
    @DisplayName(
            "Em validar formato de email deve lançar exceção de domínio "
            + "quando email é nulo."
    )
    void inValidateEmailFormatShouldThrowDomainExceptionWhenEmailIsNull() {
        String message = "O campo email é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> UserAccount.validateEmailFormat(null));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em validar formato de email deve lançar exceção de domínio "
            + "quando email é vazio."
    )
    void inValidateEmailFormatShouldThrowDomainExceptionWhenEmailIsEmpty() {
        String message = "O campo email é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> UserAccount.validateEmailFormat(""));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em validar formato de email deve lançar exceção de domínio "
            + "quando email tem formato inválido."
    )
    void inValidateEmailFormatShouldThrowDomainExceptionWhenEmailHasInvalidFormat() {
        String message = "O formato do campo email é inválido.";

        var exception = assertThrows(DomainException.class,
                () -> UserAccount.validateEmailFormat("email"));

        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName(
            "Em set status deve lançar exceção de domínio quando status é nulo."
    )
    void inSetStatusShouldThrowDomainExceptionWhenStatusIsNull() {
        var userAccount = new UserAccount();
        String message = "O campo status é obrigatório.";

        var exception = assertThrows(DomainException.class,
                () -> userAccount.setStatus(null));

        assertEquals(message, exception.getMessage());
    }
}