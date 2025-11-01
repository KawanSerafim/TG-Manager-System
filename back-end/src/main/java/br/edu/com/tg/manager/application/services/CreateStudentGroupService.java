package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CreateStudentGroupService implements CreateStudentGroupCase {
    private final CourseRepository courseRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public CreateStudentGroupService(
            CourseRepository courseRepository,
            StudentGroupRepository studentGroupRepository,
            StudentRepository studentRepository,
            ProfessorRepository professorRepository
    ) {
        this.courseRepository = courseRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
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

        validateTgCoordinatorPermission(input.executorEmail(), course);

        var studentGroup = getStudentGroup(input, course);
        List<Student> newStudents = new ArrayList<>();

        for(var studentData : input.students()) {
            Optional<Student> optionalStudent = studentRepository
                    .findByRegistration(studentData.registration());

            Student studentToProcess;

            if(optionalStudent.isEmpty()) {
                studentToProcess = new Student(
                        studentData.name(),
                        studentData.registration(),
                        studentGroup
                );

                newStudents.add(studentToProcess);
            } else {
                studentToProcess = optionalStudent.get();
                studentToProcess.enrollInGroup(studentGroup);
            }

            studentRepository.save(studentToProcess);
        }

        return new Output(studentGroup, newStudents);
    }

    private void validateTgCoordinatorPermission(
            String executorEmail, Course course
    ) {
        if(executorEmail == null || executorEmail.isBlank()) {
            throw new DomainException(
                    "Acesso negado: falha em tentar validá-lo."
            );
        }

        Professor executor = professorRepository.findByEmail(executorEmail)
                .orElseThrow(() -> new DomainException(
                        "Acesso negado: apenas o coordenador de TG do curso "
                        + course.getName() + "pode executar esta ação."
                ));

        if(!executor.canBeTgCoordinator()) {
            throw new DomainException(
                    "Acesso negado: apenas o coordenador de TG do curso "
                    + course.getName() + "pode executar esta ação."
            );
        }

        var courseTgCoordinator = course.getTgCoordinator();

        if(!Objects.equals(executor.getId(), courseTgCoordinator.getId())) {
            throw new DomainException(
                    "Acesso negado: apenas o coordenador de TG do curso "
                    + course.getName() + "pode executar esta ação."
            );
        }
    }

    private StudentGroup getStudentGroup(Input input, Course course) {
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

        return studentGroup;
    }
}