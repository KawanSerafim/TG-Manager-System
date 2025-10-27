package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.CreateCourseCase;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CreateCourseService implements CreateCourseCase {
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    public CreateCourseService(
            CourseRepository courseRepository,
            ProfessorRepository professorRepository
    ) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Output execute(Input input) {
        var tgCoordinator = getProfessor(input.tgCoordinatorRegistration());
        var courseCoordinator = getProfessor(
                input.courseCoordinatorRegistration()
        );
        var course = getCourse(input, tgCoordinator, courseCoordinator);
        var courseSaved = courseRepository.save(course);
        var tgCoordinatorInfo = getCoordinatorInfo(tgCoordinator);
        var courseCoordinatorInfo = getCoordinatorInfo(courseCoordinator);

        return new CreateCourseCase.Output(
                courseSaved.getId(),
                courseSaved.getName(),
                courseSaved.getShift(),
                tgCoordinatorInfo,
                courseCoordinatorInfo
        );
    }

    private Course getCourse(
            Input input,
            Professor tgCoordinator,
            Professor courseCoordinator
    ) {
        Optional<Course> optionalCourse = courseRepository
                .findByNameAndShift(input.name(), input.shift());

        if(optionalCourse.isPresent()) {
            throw new DomainException(
                    "Esse curso já foi cadastrado no sistema."
            );
        }

        return new Course(
                input.name(),
                input.shift(),
                tgCoordinator,
                courseCoordinator
        );
    }

    private CoordinatorInfo getCoordinatorInfo(Professor coordinator) {
        return new CreateCourseCase.CoordinatorInfo(
                coordinator.getId(),
                coordinator.getName()
        );
    }

    private Professor getProfessor(String registration) {
        return professorRepository.findByRegistration(registration)
                .orElseThrow(() -> new DomainException(
                        "O professor com matrícula = " + registration
                        + " não foi encontrado."
                ));
    }
}