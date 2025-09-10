package br.edu.com.tg.manager.application.service;

import br.edu.com.tg.manager.application.dto.StudentCsvDTO;
import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.exception.DomainException;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.core.port.CsvReaderPort;
import br.edu.com.tg.manager.core.usecase.ImportStudentsFromCsvUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.util.List;

@Service
public class ImportStudentsFromCsvService implements ImportStudentsFromCsvUseCase {

    private static final Logger logger = LoggerFactory
    .getLogger(ImportStudentsFromCsvService.class);
    
    private final CsvReaderPort csvReaderPort;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    public ImportStudentsFromCsvService(CsvReaderPort csvReaderPort, 
                                        StudentRepository studentRepository,
                                        StudentGroupRepository studentGroupRepository) {

        this.csvReaderPort = csvReaderPort;
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    @Transactional
    public void importFromCsv(InputStream inputStream, String courseName) {
        
        logger.info("Iniciando importação de alunos para a turma de '{}'", courseName);

        StudentGroup studentGroup = studentGroupRepository.findByCourseName(courseName)
            .orElseThrow(() -> {


                
                logger.error("Tentativa de importação de uma turma inexistente: {}",
                courseName);

                return new DomainException("A turma de '" + courseName + "' não foi encontrada. É necessário ter essa turma no banco de dados.");
            });
        
        List<StudentCsvDTO> studentDTOs = csvReaderPort
            .parse(inputStream, StudentCsvDTO.class);

        for(StudentCsvDTO dto : studentDTOs) {

            studentRepository.findByRegistration(dto.getRegistration())
                .ifPresentOrElse((existingStudent) -> logger.warn("Aluno com RA {} ({}) já existe. Ignorando.", dto.getRegistration(), dto.getName()),

                () -> {

                    Student newStudent = new Student(dto.getName(), dto.getRegistration());
                
                    newStudent.setStudentGroup(studentGroup);
                    studentRepository.save(newStudent);
                    
                    logger.info("Aluno com RA {} ({}) cadastrado com sucesso no grupo '{}'.", 
                        dto.getRegistration(), 
                        dto.getName(), 
                        studentGroup.getCourseName());
                }
            );
        }

        logger.info("Processo de importação para o grupo '{}' finalizado. {} registros lidos.", courseName, studentDTOs.size());
    }
}