package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste unitário para a entidade de domínio Course.
 * O objetivo é garantir que as regras de negócios da entidade estão sendo
 * protegidas.
 */
public class CourseTest {

    @Test
    @DisplayName("Deve criar um Course com sucesso com dados válidos.")
    void shouldCreateCourseSuccessfullyWithValidData() {

        /* Arrange - Inserção dos dados de entrada. */
        var name = "ADS - Análise e Desenvolvimento de  Sistemas";

        /* Act - Execução da lógica que vai ser testada. */
        var course = new Course(name);

        /* Assert - Verificação do resultado.
         *
         * assertNotNull(): Garante que o objeto não é nulo.
         * assertEquals(): Compara o dado no objeto com o da variável.
         */
        assertNotNull(course);
        assertEquals(name, course.getName());
    }

    @Test
    @DisplayName("Deve lançar DomainException ao tentar criar com nome nulo")
    void shouldThrowDomainExceptionWhenNameIsNull() {

        /* Act - Execução da lógica que vai ser testada.
         *
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
         */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(null);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara o dado no objeto com o da variável.
         */
        assertEquals(
            "O nome do curso é obrigatório.",
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Deve lançar DomainException ao tentar criar com nome vazio")
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        /* Arrange - Inserção dos dados de entrada. */
        var name = "";

        /* Act - Execução da lógica que vai ser testada.
         *
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
         */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara o dado no objeto com o da variável.
         */
        assertEquals(
            "O nome do curso é obrigatório.",
            exception.getMessage()
        );
    }
} 