package br.edu.com.tg.manager.core.entity;

import br.edu.com.tg.manager.core.exception.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste unitário para a entidade de domínio Student.
 * O objetivo é garantir que as regras de negócios da entidade estão sendo
 * protegidas.
 */
public class StudentTest {

    @Test
    @DisplayName("Deve criar um pré-Student com sucesso com dados válidos.")
    void shouldCreatePreStudentSuccessfullyWithValidData() {
        /* Arrange - Inserção dos dados de entrda. */
        StudentGroup studentGroup = mock(StudentGroup.class);
        var name = "Test name";
        var registration = "111111";

        /* Act - Execução da lógica que vai ser testada. */
        var student = new Student(name, registration, studentGroup);

        /* Assert - Verificação do resultado. 
         * 
         * assertNotNull(): Garante que o objeto não é nulo.
         * assertEquals(): Compara o dado no objeto com o da variável.
         */
        assertNotNull(student);
        assertEquals(name, student.getName());
        assertEquals(registration, student.getRegistration());
        assertNotNull(student.getStudentGroup());
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com nome nulo."
    )
    void shouldThrowDomainExceptionWhenNameIsNull() {

        /* Arrange - Inserção dos dados de entrada. */
        StudentGroup studentGroup = mock(StudentGroup.class);
        var registration = "111111";

        /* Act - Execução da lógica que vai ser testada. */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(null, registration, studentGroup);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "O nome de, pelo menos um aluno, está vazio. Verifique " + 
            "sua fonte de dados.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com nome vazio."
    )
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        /* Arrange - Inserção dos dados de entrada. */
        StudentGroup studentGroup = mock(StudentGroup.class);
        var name = "";
        var registration = "111111";

        /* Act - Execução da lógica que vai ser testada. */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, registration, studentGroup);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "O nome de, pelo menos um aluno, está vazio. Verifique " + 
            "sua fonte de dados.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com RA nulo."
    )
    void shouldThrowDomainExceptionWhenRegistrationIsNull() {
        
        /* Arrange - Inserção dos dados de entrada. */
        StudentGroup studentGroup = mock(StudentGroup.class);
        var name = "Test name";

        /* Act - Execução da lógica que vai ser testada. */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, null, studentGroup);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "A matrícula (RA) de, pelo menos um aluno, está vazia. " +
            "Verifique sua fonte de dados.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com RA vazio."
    )
    void shouldThrowDomainExceptionWhenRegistrationIsEmpty() {
        
        /* Arrange - Inserção dos dados de entrada. */
        StudentGroup studentGroup = mock(StudentGroup.class);
        var registration = "";
        var name = "Test name";

        /* Act - Execução da lógica que vai ser testada. */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, registration, studentGroup);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "A matrícula (RA) de, pelo menos um aluno, está vazia. " +
            "Verifique sua fonte de dados.", 
            exception.getMessage()
        );
    }

    @Test
    @DisplayName(
        "Deve lançar DomainException ao tentar criar com grupo nulo."
    )
    void shouldThrowDomainExceptionWhenStudentGroupIsNull() {
        
        /* Arrange - Inserção dos dados de entrada. */
        var name = "Test name";
        var registration = "111111";

        /* Act - Execução da lógica que vai ser testada. */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, registration, null);
        });

        /* Assert - Verificação do resultado.
         *
         * assertEquals(): Compara dois parâmetros se são iguais.
         */
        assertEquals(
            "O aluno deve pertencer a uma turma.",
            exception.getMessage()
        );       
    }
}