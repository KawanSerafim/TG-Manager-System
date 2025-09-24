package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Teste unitário de domínio.
 * Garante que todas as regras de negócios estão sendo estabelecidas e
 * protegidas pela entidade de domínio Professor.
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
public class ProfessorTest {
    
    private static final Logger logger = 
    LoggerFactory.getLogger(ProfessorTest.class);

    @Test
    @DisplayName("Deve criar um professor com sucesso com dados válidos.")
    void shouldCreateAdvisorSucessfullyWithValidData() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um orientador com dados válidos."
        );

        var professor = new Professor(

            name,
            registration,
            email,
            hashedPassword,
            role
        );
        
        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados.");

        assertNotNull(professor);
        logger.info(">>>>>> Pré-aluno salvo com sucesso.");

        assertEquals(name, professor.getName());
        logger.info(">>>>>> Integridade do nome garantida.");

        assertEquals(registration, professor.getRegistration());
        logger.info(">>>>>> Integridade da matrícula garantida.");

        assertEquals(email, professor.getEmail());
        logger.info(">>>>>> Integridade do email garantida.");

        assertEquals(hashedPassword, professor.getHashedPassword());
        logger.info(">>>>>> Integridade da senha garantida.");

        assertEquals(role, professor.getRole());
        logger.info(">>>>>> Integridade do cargo garantida.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando nome é nulo.")
    void shouldThrowDomainExceptionWhenNameIsNull() { 

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String registration = "111111";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com nome nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    null,
                    registration,
                    email,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");
        
        assertEquals(
        
            exception.getMessage(), 
            "O campo nome é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando nome é vazio.")
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "";
        String registration = "111111";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com nome vazio.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    email,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo nome é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando matrícula é nula.")
    void shouldThrowDomainExceptionWhenRegistrationIsNull() { 

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um professor com matrícula nula."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    null,
                    email,
                    hashedPassword,
                    role
                );         
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo matrícula é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando matrícula é vazia.")
    void shouldThrowDomainExceptionWhenRegistrationIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um professor com matrícula vazia."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    email,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo matrícula é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando email é nulo.")
    void shouldThrowDomainExceptionWhenEmailIsNull() { 

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com email nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    null,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo email é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando email é vazio.")
    void shouldThrowDomainExceptionWhenEmailIsEmpty() { 

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com email vazio.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    email,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo email é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando email não tem @.")
    void shouldThrowDomainExceptionWhenEmailDoesNotHaveAt() { 

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "testegmail.com";
        String hashedPassword = "senha criptografada.";
        ProfessorRole role = ProfessorRole.ADVISOR;

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com email sem @.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    email,
                    hashedPassword,
                    role
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O formato do campo email é inválido."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar uma exceção de domínio quando cargo é nulo")
    void shouldThrowDomainExceptionWhenRoleIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "Fulano";
        String registration = "111111";
        String email = "teste@gmail.com";
        String hashedPassword = "senha criptografada.";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um professor com cargo nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Professor(

                    name,
                    registration,
                    email,
                    hashedPassword,
                    null
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo cargo é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }
}