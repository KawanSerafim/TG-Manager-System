package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.StudentStatus;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Teste unitário de domínio.
 * Garante que todas as regras de negócios estão sendo estabelecidas e
 * protegidas pela entidade de domínio Student.
 * 
 * -------------------
 * 
 * Métodos asserts:
 * 
 * - assertNotNull(): verifica se o parâmetro não é nulo.
 *        
 * - assertEquals(): verifica se um parâmetro possui o mesmo valor do outro.
 *        
 * - assertThrows(): verifica se a exceção lançada é do tipo desejado.
 */
public class StudentTest {

    private static final Logger logger = 
    LoggerFactory.getLogger(StudentTest.class);

    @Test
    @DisplayName("Deve criar um pré-aluno com sucesso com dados válidos.")
    void shouldCreatePreStudentSucessfullyWithValidData() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        StudentGroup studentGroup = mock(StudentGroup.class);

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um pré-aluno com os dados válidos."
        );

        var student = new Student(name, registration, studentGroup);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");
        
        assertNotNull(student);
        logger.info(">>>>>> Pré-aluno salvo com sucesso.");

        assertEquals(name, student.getName());
        logger.info(">>>>>> Integridade do nome garantida.");

        assertEquals(registration, student.getRegistration());
        logger.info(">>>>>> Integridade do RA garantida.");

        assertEquals(studentGroup, student.getStudentGroup());
        logger.info(">>>>>> Integridade da turma garantida.");
    }

    @Test
    @DisplayName(
        
        "Deve finalizar o cadastro do aluno com sucesso com dados válidos."
    )
    void shouldFinalizeStudentRegistrationSucessfullyWithValidData() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");
        
        String name = "Fulano";
        String registration = "111111";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada";

        StudentGroup studentGroup = mock(StudentGroup.class);
        var student = new Student(name, registration, studentGroup);
        
        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Finalizando o cadastro de um aluno com os " +
            "dados válidos."
        );
        
        student.finalizeRegistration(email, hashedPassword);
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(email, student.getEmail());
        logger.info(">>>>>> Integridade do email garantida.");

        assertEquals(hashedPassword, student.getHashedPassword());
        logger.info(">>>>>> Integridade da senha garantida.");

        assertEquals(student.getStatus(), StudentStatus.ACTIVE);
        logger.info(">>>>>> Status alterado para ACTIVE com sucesso.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando status não é pré-registro."
    )
    void shouldThrowDomainExceptionWhenStatusIsNotPreRegistration() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada";

        var student = new Student();

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Finalizando o cadastro de um aluno com o " +
            "aluno vazio."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.finalizeRegistration(email, hashedPassword); 
        });

        /* ----------------------------------------------------------------- */
    
        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(
            
            exception.getMessage(), 
            "O aluno não tem o estado válido para ação."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando status é ativo."
    )
    void shouldThrowDomainExceptionWhenStatusIsActive() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada";

        var student = new Student();
        student.setStatus(StudentStatus.ACTIVE);

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Finalizando o cadastro de um aluno com o " +
            "status ativo."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.finalizeRegistration(email, hashedPassword); 
        });

        /* ----------------------------------------------------------------- */
    
        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(
            
            exception.getMessage(), 
            "O aluno não tem o estado válido para ação."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando nome é nulo.")
    void shouldThrowDomainExceptionWhenNameIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String registration = "111111";
        StudentGroup studentGroup = mock(StudentGroup.class);
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um pré-aluno com o nome nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(null, registration, studentGroup); 
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo nome é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando nome é vazio.")
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "";
        String registration = "111111";
        StudentGroup studentGroup = mock(StudentGroup.class);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um pré-aluno com o nome vazio.");
        
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, registration, studentGroup); 
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo nome é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando RA é nulo.")
    void shouldThrowDomainExceptionWhenRegistrationIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        StudentGroup studentGroup = mock(StudentGroup.class);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um pré-aluno com RA nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, null, studentGroup); 
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo RA é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando RA é vazio.")
    void shouldThrowDomainExceptionWhenRegistrationIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "";
        StudentGroup studentGroup = mock(StudentGroup.class);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um pré-aluno com RA nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(name, registration, studentGroup); 
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo RA é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando email é nulo.")
    void shouldThrowDomainExceptionWhenEmailIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String hashedPassword = "senha criptografada";

        StudentGroup studentGroup = mock(StudentGroup.class);
        var student = new Student(name, registration, studentGroup);

        /* ----------------------------------------------------------------- */

        logger.info(
        
            ">>>>>> ACT - Acionando o método de finalizar cadastro com " +
            "email nulo."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.finalizeRegistration(null, hashedPassword);
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo email é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando email é vazio.")
    void shouldThrowDomainExceptionWhenEmailIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "";
        String hashedPassword = "senha criptografada";

        StudentGroup studentGroup = mock(StudentGroup.class);
        var student = new Student(name, registration, studentGroup);

        /* ----------------------------------------------------------------- */

        logger.info(
        
            ">>>>>> ACT - Acionando o método de finalizar cadastro com " +
            "email nulo."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.finalizeRegistration(email, hashedPassword);
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo email é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando email não tem @.")
    void shouldThrowDomainExceptionWhenEmailDoesNotHaveAt() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "testegmail.com";
        String hashedPassword = "senha criptografada";

        StudentGroup studentGroup = mock(StudentGroup.class);
        var student = new Student(name, registration, studentGroup);

        /* ----------------------------------------------------------------- */

        logger.info(
        
            ">>>>>> ACT - Acionando o método de finalizar cadastro com " +
            "email sem @."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.finalizeRegistration(email, hashedPassword);
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(
        
            exception.getMessage(), 
            "O formato do campo email é inválido."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando status é nulo.")
    void shouldThrowDomainExceptionWhenStatusIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        var student = new Student();

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Inserindo null no status do aluno.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                student.setStatus(null);;
        });

        /* ----------------------------------------------------------------- */
    
        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(exception.getMessage(), "O campo status é obrigatório.");
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando turma é nula.")
    void shouldThrowDomainExceptionWhenStudentGroupIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um pré-aluno com turma nula.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Student(
                
                    name, 
                    registration, 
                    null
                );
        });
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertEquals(
        
            exception.getMessage(), 
            "O campo turma é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }
}