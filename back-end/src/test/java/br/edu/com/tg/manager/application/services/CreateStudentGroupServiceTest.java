package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Teste unitário de aplicação.
 * Garante que a lógica do caso de uso para criar uma nova turma esteja
 * funcionando corretamente e que esteja protegida pelas regras de negócios.
 * 
 * Anotação @ExtendWith: ativa a integração do JUnit com o Mockito, permitindo
 * que as anotaçãoes Mock e InjectMocks funcionem.
 * 
 * Anotação @Mock: indica ao Mockito que a instância é apenas um dublê com o único
 * propósito de serem usadas em um teste, isolando o teste unitário de possíveis
 * erros por acoplamento.
 * 
 * Anotação @InjectMocks: indica ao Mockito para criar uma instância real e
 * injetar todos os Mocks determinados no teste, assim simulando a injeção de
 * dependência que ocorre na aplicação.
 */
@ExtendWith(MockitoExtension.class)
public class CreateStudentGroupServiceTest {

    private static final Logger logger = 
    LoggerFactory.getLogger(CreateStudentGroupServiceTest.class);

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentGroupRepository studentGroupRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentDataReader studentDataReader;

    /* Simulando a injeção de dependência de todos os ports. */
    @InjectMocks
    private CreateStudentGroupService service;

    @Test
    @DisplayName("Deve criar turma e pré-cadastro do aluno com sucesso.")
    void shouldCreateStudentGroupAndPreRegisterStudentsSuccessfully() {

        logger.info(">>>>>> ARRANGE - Organizando os dados de entrada.");

        InputStream inputStream = mock(InputStream.class);

        var useCaseInput = new CreateStudentGroupCase.Input(

            "ADS",
            Discipline.TG1,
            inputStream,
            "senha123"
        );

        var studentDataList = List.of(
            
            new StudentDataReader.StudentData(
                
                "Aluno 1", 
                "RA1"
            ),
            new StudentDataReader.StudentData(
            
                "Aluno 2", 
                "RA 2"
            )
        );

        var fileData = new StudentDataReader.FileData(

            2025, 1, CourseShift.NIGHT, studentDataList
        );

        var course = new Course();

        /* Simulação da captura do arquivo com os dados dos alunos e demais
         * informações de curso.
        */
        when(studentDataReader.read(any(InputStream.class)))
            .thenReturn(fileData);

        /* Simulação do repositório buscando um curso pelo nome e turno. */
        when(courseRepository.findByNameAndShift("ADS", CourseShift.NIGHT))
            .thenReturn(Optional.of(course));

        /* Simulação do repositório buscando um aluno pelo seu RA. */
        when(studentRepository.findByRegistration(anyString()))
            .thenReturn(Optional.empty());

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ACT - Executando o caso de uso.");

        service.execute(useCaseInput);

        /* ----------------------------------------------------------------- */

        logger.info(">>>>>> ASSERT - Verificando os resultados. ");

        /* Verifica se o método save do repositório foi chamado só uma vez. */
        verify(studentGroupRepository, times(1))
            .save(any(StudentGroup.class));

        /* Verifica se o método save do repositório foi chamado duas vezes. */
        verify(studentRepository, times(2))
            .save(any(Student.class));
    }
}