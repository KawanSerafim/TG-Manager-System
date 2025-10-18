package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {

    @Test
    @DisplayName("Deve criar conta de usuário com dados válidos.")
    void shouldCreateUserAccountWithValidData() {

        // Act.
        var userAccount = new UserAccount(

            "kawan@teste.com",
            "senha 123"
        );

        // Assert.
        assertNotNull(userAccount);
        assertEquals("kawan@teste.com",  userAccount.getEmail());
        assertEquals("senha 123", userAccount.getPassword());
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando email é nulo.")
    void shouldThrowExceptionWhenEmailIsNull() {

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            new UserAccount(null, "senha 123")
        );

        // Assert.
        assertEquals(

            "O campo email é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando email é vazio.")
    void shouldThrowExceptionWhenEmailIsEmpty() {

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            new UserAccount("", "senha 123")
        );

        // Assert.
        assertEquals(

            "O campo email é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(

        "Deve lançar exceção de domínio quando formato de email é invalido"
    )
    void shouldThrowExceptionWhenEmailFormatIsInvalid() {

        var userAccount = new UserAccount();

        // Act.
        var exception = assertThrows(DomainException.class, () ->

            userAccount.setEmail("kawanteste.com")
        );

        // Assert.
        assertEquals(

            "O formato do campo email é inválido.",
            exception.getMessage()
        );
    }
}