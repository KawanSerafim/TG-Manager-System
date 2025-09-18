package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Teste unitário para a entidade de domínio StudentGroup.
 * O objetivo é garantir que as regras de negócios da entidade estão sendo
 * protegidas.
 */
public class StudentGroupTest {
    
    @Test
    @DisplayName("Deve criar um StudentGroup com sucesso com dados válidos.")
    void shouldCreateStudentGroupSuccessfullyWithValidData() {

        /* Arrange - Inserção dos dados de entrada. */
        var course = new Course(
            "ADS - Análise e Desenvolvimento de  Sistemas"
        );
        var year = 2025;
        var semester = 1;

        /* Act - Execução da lógica que queremos testar. */
        var studentGroup = new StudentGroup(course, year, semester);

        /* Assert - Verificação do resultado. 
         * 
         * assertNotNull(): Garante que o objeto não é nulo.
         * assertEquals(): Compara o dado no objeto com o da variável.
        */
        assertNotNull(studentGroup);
        assertEquals(course, studentGroup.getCourse());
        assertEquals(year, studentGroup.getYear());
        assertEquals(semester, studentGroup.getSemester());
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com semestre inválido."
    )
    void shouldThrowDomainExceptionWhenSemesterIsInvalid() {
        
        /* Arrange - Inserção dos dados de entrada, com semestre errado. */
        var course = new Course(
            "ADS - Análise e Desenvolvimento de  Sistemas"
        );
        var year = 2025;
        var invalidSemester = 3;

        /* Act - Execução da lógica que queremos testar.
         * 
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
        */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(course, year, invalidSemester);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara o dado no objeto com o da variável.
         */
        assertEquals(
            "O semestre fornecido é inválido. Deve ser 1 ou 2.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com ano inválido."
    )
    void shouldThrowDomainExceptionWhenYearIsInvalid() {

        /* Arrange - Inserção dos dados de entrada, com ano errado. */
        var course = new Course(
            "ADS - Análise e Desenvolvimento de  Sistemas"
        );
        var year = 2024;
        var invalidSemester = 2;

        /* Act - Execução da lógica que queremos testar.
         * 
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
        */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(course, year, invalidSemester);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "O ano fornecido é inválido. Deve ser um número " +
            "a partir do ano atual.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar sem curso."
    )
    void shouldThrowDomainExceptionWhenCourseIsNull() {

        /* Arrange - Inserção dos dados de entrada, com curso nulo. */
        Course nullCourse = null;
        var year = 2024;
        var invalidSemester = 2;

        /* Act - Execução da lógica que queremos testar.
         * 
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
        */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(nullCourse, year, invalidSemester);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals("A turma deve estar associada a um curso.", 
        exception.getMessage());
    }
}