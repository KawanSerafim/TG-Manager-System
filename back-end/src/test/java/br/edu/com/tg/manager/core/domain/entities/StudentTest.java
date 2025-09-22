package br.edu.com.tg.manager.core.domain.entities;

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

        logger.info(">>>>>> ACT - Criando um aluno com os dados válidos.");

        var student = new Student(name, registration, studentGroup);

        logger.info(">>>>>> ASSERT - Verificando os resultados.");
        
        assertNotNull(student);
        logger.info(">>>>>> Aluno salvo com sucesso.");

        assertEquals(name, student.getName());
        logger.info(">>>>>> Integridade do nome garantida.");

        assertEquals(registration, student.getRegistration());
        logger.info(">>>>>> Integridade do RA garantida.");

        assertEquals(studentGroup, student.getStudentGroup());
        logger.info(">>>>>> Integridade da turma garantida.");
    }
}