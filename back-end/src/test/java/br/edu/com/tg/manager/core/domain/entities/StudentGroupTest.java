package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.time.Year;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Teste unitário de domínio.
 * Garante que todas as regras de negócios estão sendo estabelecidas e
 * protegidas pela entidade de domínio StudentGroup.
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
public class StudentGroupTest {

    private static final Logger logger = 
    LoggerFactory.getLogger(StudentGroup.class);

    @Test
    @DisplayName("Deve criar turma com sucesso com dados válidos.")
    void shouldCreateStudentGroupSucessfullyWithValidData() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");
        
        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        Integer semester = 2;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando uma turma com dados válidos.");

        var studentGroup = new StudentGroup(
            
            course,
            discipline,
            year,
            semester,
            temporaryPassword
        );

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertNotNull(studentGroup);
        logger.info(">>>>>> Turma salva com sucesso.");

        assertEquals(course, studentGroup.getCourse());
        logger.info(">>>>>> Integridade do curso garantida.");

        assertEquals(discipline, studentGroup.getDiscipline());
        logger.info(">>>>>> Integridade da disciplina garantida.");

        assertEquals(year, studentGroup.getYear());
        logger.info(">>>>>> Integridade do ano garantida.");

        assertEquals(semester, studentGroup.getSemester());
        logger.info(">>>>>> Integridade do semestre garantida.");

        assertEquals(temporaryPassword, studentGroup.getTemporaryPassword());
        logger.info(">>>>>> Integridade da senha temporária garantida.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando curso é nulo.")
    void shouldThrowDomainExceptionWhenCourseIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        Integer semester = 2;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando turma com curso nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(
                    
                    null,
                    discipline,
                    year,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo curso é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando disciplina é nula.")
    void shouldThrowDomainExceptionWhenDisciplineIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Integer year = Year.now().getValue();
        Integer semester = 2;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando turma com disciplina nula.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(
                    
                    course,
                    null,
                    year,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo disciplina é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando ano é nulo.")
    void shouldThrowDomainExceptionWhenYearIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer semester = 2;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando turma com ano nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(
                    
                    course,
                    discipline,
                    null,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo ano é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando ano é diferente do atual."
    )
    void shouldThrowDomainExceptionWhenYearIsDifferentFromCurrentOne() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = (Year.now().getValue()) - 1;
        Integer semester = 2;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando turma com ano diferente do atual"
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(

                    course,
                    discipline,
                    year,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O ano não pode ser diferente do atual."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando semestre é nulo.")
    void shouldThrowDomainExceptionWhenSemesterIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando turma com disciplina nula.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(
                    
                    course,
                    discipline,
                    year,
                    null,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo semestre é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
     
        "Deve lançar exceção de domínio quando semestre é diferente do padrão."
    )
    void shouldThrowDomainExceptionWhenSemesterIsDifferentFromStandard() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        Integer semester = 3;
        String temporaryPassword = "senha temporária";

        /* ----------------------------------------------------------------- */

        logger.info(
        
            ">>>>>> ACT - Criando turma com disciplina diferente do padrão."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(

                    course,
                    discipline,
                    year,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O semestre foge do padrão: 1 ou 2."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando senha temporária é nula."
    )
    void shouldThrowDomainExceptionWhenTemporaryPasswordIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        Integer semester = 2;

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando turma com senha temporária nula."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(
                    
                    course,
                    discipline,
                    year,
                    semester,
                    null
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo senha temporária é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando senha temporária é vazia."
    )
    void shouldThrowDomainExceptionWhenTemporaryPasswordIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        Course course = mock(Course.class);
        Discipline discipline = Discipline.TG1;
        Integer year = Year.now().getValue();
        Integer semester = 2;
        String temporaryPassword = "";

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando turma com senha temporária vazia."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new StudentGroup(

                    course,
                    discipline,
                    year,
                    semester,
                    temporaryPassword
                );
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo senha temporária é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }
}