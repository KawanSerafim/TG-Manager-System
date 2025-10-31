package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateStudentGroupService implements CreateStudentGroupCase {
    private final CourseRepository courseRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;

    public CreateStudentGroupService(
            CourseRepository courseRepository,
            StudentGroupRepository studentGroupRepository,
            StudentRepository studentRepository
    ) {
        this.courseRepository = courseRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Output execute(Input input) {
        Course course = courseRepository.findByName(input.courseName())
                .orElseThrow(() -> new DomainException(
                        "O curso com nome = " + input.courseName() + ", "
                        + "não foi encontrado."
                ));
        
        StudentGroup studentGroup;

        Optional<StudentGroup> optionalGroup = studentGroupRepository
                .findByCourseAndYearAndSemesterAndCourseShiftAndDiscipline(
                        course,
                        input.year(),
                        input.semester(),
                        input.shift(),
                        input.discipline()
                );

        if(optionalGroup.isPresent()) {
            studentGroup = optionalGroup.get();
        } else {
            var newStudentGroup = new StudentGroup(
                    course,
                    input.discipline(),
                    input.year(),
                    input.semester(),
                    input.shift()
            );
            studentGroup = studentGroupRepository.save(newStudentGroup);
        }

        List<Student> students = new ArrayList<>();

        for(var studentData : input.students()) {
            Optional<Student> optionalStudent = studentRepository
                    .findByRegistration(studentData.registration());

            if(optionalStudent.isEmpty()) {
                var student = new Student(
                        studentData.name(),
                        studentData.registration(),
                        studentGroup
                );
                studentRepository.save(student);
                students.add(student);
            }
        }
        return new Output(studentGroup, students);
    }
}