package br.edu.com.tg.manager.application.service;

import br.edu.com.tg.manager.application.dto.StudentCsvDTO;
import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.exception.DomainException;
import br.edu.com.tg.manager.core.port.repository.CourseRepository;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.core.port.CsvReaderPort;
import br.edu.com.tg.manager.core.usecase.ImportStudentsFromCsvUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ImportStudentsFromCsvService implements ImportStudentsFromCsvUseCase {
    
    private final CsvReaderPort csvReaderPort;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final CourseRepository courseRepository;

    public ImportStudentsFromCsvService(CsvReaderPort csvReaderPort, StudentRepository studentRepository, StudentGroupRepository studentGroupRepository, CourseRepository courseRepository) {

        this.csvReaderPort = csvReaderPort;
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public void importFromCsv(InputStream inputStream, String courseName, Integer year, Integer semester) {

        Course course = courseRepository.findByName(courseName)
            .orElseThrow(() -> new DomainException("O curso '" + courseName + "' não foi encontrado."));

        StudentGroup studentGroup = studentGroupRepository.findByCourseAndYearAndSemester(course, year, semester)
            .orElseThrow(() -> new DomainException("A turma do curso '" + course + "', ano '" + year + "' e semestre '" + semester + "' não foi encontrada."));

        List<StudentCsvDTO> studentDTOs = csvReaderPort.parse(inputStream, StudentCsvDTO.class);

        for(StudentCsvDTO dto : studentDTOs) {

            Optional<Student> existingStudent = studentRepository.findByRegistration(dto.getRegistration());

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