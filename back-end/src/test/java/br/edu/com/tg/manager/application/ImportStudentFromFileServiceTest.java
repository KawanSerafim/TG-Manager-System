package br.edu.com.tg.manager.application;

import br.edu.com.tg.manager.application.dto.StudentFileDTO;
import br.edu.com.tg.manager.application.service.ImportStudentsFromFileService;
import br.edu.com.tg.manager.core.port.filereader.FileReaderPort;
import br.edu.com.tg.manager.core.port.repository.CourseRepository;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.exception.DomainException;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste unitário para o service do caso de uso de importar aluno de um arquivo.
 * O objetivo é garantir que a lógica aplicada pelo cérebro do caso de uso está 
 * sendo protegida.
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
public class ImportStudentFromFileServiceTest {
    
    @Mock
    private FileReaderPort fileReaderPort;
    
    @Mock
    private StudentRepository studentRepository;
    
    @Mock
    private StudentGroupRepository studentGroupRepository;
    
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private ImportStudentsFromFileService service;

    @Test
    @DisplayName("Deve importar alunos com sucesso com dados válidos.")
    void shouldImportNewStudentsSuccessfullyWithValidData() {

        /* Arrange - Inserção dos dados de entrada. */
        var inputStream = mock(InputStream.class);
        var courseName = "ADS - Análise e Desenvolvimento de  Sistemas";
        var year = 2025;
        var semester = 2;
        
        /* Uma simulação de curso que existe no banco de dados. */
        var course = new Course(courseName);
        course.setId(1L);

        /* Uma simulação de turma que existe no banco de dados. */
        var studentGroup = new StudentGroup(course, year, semester);
        studentGroup.setId(1L);

        /* Uma simulação de uma lista de DTOs que o leitor "retornou". */
        var studentDto1 = new StudentFileDTO();
        studentDto1.setName("Kawan");
        studentDto1.setRegistration("111111");

        var studentDto2 = new StudentFileDTO();
        studentDto2.setName("Thiago");
        studentDto2.setRegistration("222222");

        var dtoList = List.of(studentDto1, studentDto2);

        /* Programação do comportamento dos Mocks. */

        /* Simulação do repositório buscando curso pelo nome. */
        when(courseRepository.findByName(courseName))
            .thenReturn(Optional.of(course));

        /* Simulação do repositório buscando a turma pelo curso, ano
         * e semestre.
         */
        when(studentGroupRepository
            .findByCourseAndYearAndSemester(course, year, semester))
            .thenReturn(Optional.of(studentGroup));
        
        /* Simulação do leitor retornando uma lista de DTOs através da
         * dependência com a porta de saída.
         */
        when(fileReaderPort
            .parse(inputStream, StudentFileDTO.class))
            .thenReturn(dtoList);

        /* Simulação de um banco de dados sem os alunos que serão inseridos. */
        when(studentRepository.findByRegistration(anyString()))
            .thenReturn(Optional.empty());

        /* Act - Execução da lógica que vai ser testada. */
        service.execute(inputStream, courseName, year, semester);

        /* Assert - Verificação do resultado.
         *
         * verify():
         */

        /* Verifica se o save do repositório de aluno foi usado 2 vezes. */
        verify(studentRepository, times(2))
            .save(any(Student.class));

        /* Verifica se o save do repositório de turma nunca foi usado. */
        verify(studentGroupRepository, never())
            .save(any(StudentGroup.class));

        /* Verifica se o save do repositório de curso nunca foi usado. */
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    @DisplayName("Deve lançar DomainException quando o curso não é encontrado.")
    void shouldThrowDomainExceptionWhenCourseIsNotFound() {

        /* Arrange - Inserção dos dados de entrada. */
        var inputStream = mock(InputStream.class);
        var courseName = "Curso que não existe";
        var year = 2025;
        var semester = 2;

        when(courseRepository.findByName(courseName))
            .thenReturn(Optional.empty());
        
        /* Act - Execução da lógica que vai ser testada.
         * 
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
         */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                service.execute(inputStream, courseName, year, semester);
        });

        /* Assert - Verificação do resultado.
         * 
         * assertEquals(): Compara o dado no objeto com o da variável.
         * 
         * verify():
         */
        String message = "O curso '" + courseName + "' não foi encontrado.";

        assertEquals(message, exception.getMessage());

        /* Verifica se não teve uma única interação com o repositório. */
        verify(studentGroupRepository, never())
            .findByCourseAndYearAndSemester(any(), any(), any());

        verify(studentRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar DomainException quando a turma não é encontrada.")
    void shouldThrowDomainExceptionWhenStudentGroupIsNotFound() {

        /* Arrange - Inserção dos dados de entrada. */
        var inputStream = mock(InputStream.class);
        var courseName = "ADS - Análise e Desenvolvimento de  Sistemas";
        var year = 2025;
        var semester = 2;

        /* Simulação de que o curso existe. */
        var course = new Course(courseName);

        when(courseRepository.findByName(courseName))
            .thenReturn(Optional.of(course));

        /* Simulação de que a turma com o dados informados não existe. */
        when(studentGroupRepository
            .findByCourseAndYearAndSemester(course, year, semester))
            .thenReturn(Optional.empty());
        
        /* Act - Execução da lógica que vai ser testada.
         * 
         * assertThrows(): Garante que a execução do lambda lance uma exceção
         * do tipo esperado.
         */
        DomainException exception = assertThrows(
            DomainException.class, () -> {

                service.execute(inputStream, courseName, year, semester);
        });

        /* Assert - Verificação do resultado.
         * 
         * assertEquals(): Compara o dado no objeto com o da variável.
         * 
         * verify():
         */
        String message = "A turma do curso '" + courseName + "', ano '" +
        year + "' e semestre '" + semester + "' não foi encontrada.";
        
        assertEquals(message, exception.getMessage());

        /* Verifica se não salvou aluno sem turma. */
        verify(studentRepository, never()).save(any());
    }
}