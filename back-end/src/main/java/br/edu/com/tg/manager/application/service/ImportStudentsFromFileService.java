package br.edu.com.tg.manager.application.service;

import br.edu.com.tg.manager.application.dto.StudentFileDTO;
import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.exception.DomainException;
import br.edu.com.tg.manager.core.port.csvreader.CsvReaderPort;
import br.edu.com.tg.manager.core.port.repository.CourseRepository;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.core.usecase.ImportStudentsFromCsvUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Lógica do caso de uso de importar alunos de um arquivo.
 * Classe responsável por atuar como cérebro de um caso de uso específico.
 * Regras de aplicação, ordem dos fatores e outras coisas desse tipo são
 * estabelecidas pelo service.
 * 
 * Anotação @Service: indica ao framework que esta classe é um componente de
 * negócio, permitirndo a injeção de dependência em quem for implementar a
 * porta de saída referente ao caso de uso e referente aos repositórios.
 * 
 * Anotação @Transacional: garante que todas as operações de banco de dados
 * dentro do método do contrato do caso de uso sejam atômicas. Ou seja, ou
 * tudo funciona, ou tudo é desconsiderado.
 */
@Service
public class ImportStudentsFromFileService implements
ImportStudentsFromCsvUseCase {
    
    /* Instâncias das portas de saída. */
    private final CsvReaderPort csvReaderPort;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final CourseRepository courseRepository;

    /**
     * Construtor para injeção de dependência de todas as portas de
     * saída, tanto repositórios quanto a referente ao caso de uso.
     * @param csvReaderPort
     * @param studentRepository
     * @param studentGroupRepository
     * @param courseRepository
     */
    public ImportStudentsFromFileService(
        CsvReaderPort csvReaderPort, 
        StudentRepository studentRepository, 
        StudentGroupRepository studentGroupRepository, 
        CourseRepository courseRepository
    ) {

        this.csvReaderPort = csvReaderPort;
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void importFromCsv(
        InputStream inputStream, 
        String courseName, 
        Integer year, 
        Integer semester
    ) {

        /* Valida a existência do curso. */
        Course course = courseRepository.findByName(courseName)
            .orElseThrow(() -> new DomainException("O curso '" + courseName +
            "' não foi encontrado."));

        /* Valida a existência da turma. */
        StudentGroup studentGroup = studentGroupRepository.
        findByCourseAndYearAndSemester(course, year, semester)
            .orElseThrow(() -> new DomainException("A turma do curso '" +
            courseName + "', ano '" + year + "' e semestre '" + semester + 
            "' não foi encontrada."));

        /* Delega a leitura do arquivo para o adapter, via injeção de
         * dependência.
         */
        List<StudentFileDTO> studentDTOs = csvReaderPort.parse(
            inputStream, 
            StudentFileDTO.class
        );

        /* Itera sobre os dados do molde de aluno. */
        for(StudentFileDTO dto : studentDTOs) {

            Optional<Student> existingStudent = studentRepository.
            findByRegistration(dto.getRegistration());

            /* Apenas insere o aluno se ele não existir no banco de dados. */
            if(existingStudent.isEmpty()) {

                Student newStudent = new Student();
                newStudent.setName(dto.getName());
                newStudent.setRegistration(dto.getRegistration());
                newStudent.setStudentGroup(studentGroup);
                
                studentRepository.save(newStudent);
            }
        }
    }
}