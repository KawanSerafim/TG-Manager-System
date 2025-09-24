package br.edu.com.tg.manager.core.domain.entities;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Teste unitário de domínio.
 * Garante que todas as regras de negócios estão sendo estabelecidas e
 * protegidas pela entidade de domínio Course.
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
public class CourseTest {

    private static final Logger logger = 
    LoggerFactory.getLogger(CourseTest.class);

    @Test
    @DisplayName("Deve criar curso com sucesso com dados válidos.")
    void shouldCreateCourseSucessfullyWithValidData() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);
        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */
    
        logger.info(">>>>>> ACT - Criando um curso com dados válidos.");

        var Course = new Course(name, shift, tgCoordinator, courseCoordinator);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertNotNull(Course);
        logger.info(">>>>>> Curso salvo com sucesso.");

        assertEquals(name, Course.getName());
        logger.info(">>>>>> Integridade do nome garantida.");

        assertEquals(tgCoordinator, Course.getTgCoordinator());
        logger.info(">>>>>> Integridade do coordenador de TG garantida.");

        assertEquals(courseCoordinator, Course.getCourseCoordinator());
        logger.info(
            
            ">>>>>> Integridade do coordenador de curso garantida."
        );
    }

    @Test
    @DisplayName("Deve lançar exceção de domínio quando nome é nulo.")
    void shouldThrowDomainExceptionWhenNameIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);
        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um curso com nome nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(null, shift, tgCoordinator, courseCoordinator);                
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
    @DisplayName("Deve lançar exceção de domínio quando nome é vazio.")
    void shouldThrowDomainExceptionWhenNameIsEmpty() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "";
        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);
        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um curso com nome vazio.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, shift, tgCoordinator, courseCoordinator);
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
    @DisplayName("Deve lançar exceção de domínio quando turno é nulo.")
    void shouldThrowDomainExceptionWhenShiftIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);
        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Criando um curso com turno nulo.");

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, null, tgCoordinator, courseCoordinator);
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo turno é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando coordenador de TG é nulo."
    )
    void shouldThrowDomainExceptionWhenTgCoordinatorIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        CourseShift shift = CourseShift.NIGHT;
        Professor courseCoordinator = mock(Professor.class);

        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um curso com coordenador de TG nulo."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, shift, null, courseCoordinator);
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo coordenador de TG é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando professor não é " +
        "coordenador de TG."
    )
    void shouldThrowDomainExceptionWhenProfessorIsNotTgCoordinator() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.ADVISOR);
        when(courseCoordinator.getRole())
            .thenReturn(ProfessorRole.COURSE_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(
        
            ">>>>>> ACT - Criando um curso com o coordenador de TG com " +
            "cargo de orientador comum."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, shift, tgCoordinator, courseCoordinator);
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O professor não tem permissão de Coordenador de TG."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando coordenador de curso é nulo."
    )
    void shouldThrowDomainExceptionWhenCourseCoordinatorIsNull() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um curso com coordenador de curso nulo."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, shift, tgCoordinator, null);
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O campo coordenador de curso é obrigatório."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }

    @Test
    @DisplayName(
        
        "Deve lançar exceção de domínio quando professor não é " +
        "coordenador de curso."
    )
    void shouldThrowDomainExceptionWhenProfessorIsNotCourseCoordinator() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        String name = "ADS";
        CourseShift shift = CourseShift.NIGHT;
        Professor tgCoordinator = mock(Professor.class);
        Professor courseCoordinator = mock(Professor.class);

        when(tgCoordinator.getRole()).thenReturn(ProfessorRole.TG_COORDINATOR);
        when(courseCoordinator.getRole()).thenReturn(ProfessorRole.ADVISOR);

        /* ----------------------------------------------------------------- */

        logger.info(
            
            ">>>>>> ACT - Criando um curso com o coordenador de curso com " +
            "cargo de orientador comum."
        );

        DomainException exception = assertThrows(
            DomainException.class, () -> {

                new Course(name, shift, tgCoordinator, courseCoordinator);
        });

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        assertEquals(
        
            exception.getMessage(), 
            "O professor não tem permissão de Coordenador de Curso."
        );
        logger.info(">>>>>> Mensagem de exceção correta.");
    }
}