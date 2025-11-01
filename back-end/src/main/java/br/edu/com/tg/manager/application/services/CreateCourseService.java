package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.CourseParameters;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.CreateCourseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CreateCourseService implements CreateCourseCase {
    private final CourseRepository courseRepository;
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;

    public CreateCourseService(
            CourseRepository courseRepository,
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository
    ) {
        this.courseRepository = courseRepository;
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Output execute(Input input) {
        validateAdminPermission(input.executorEmail());

        var tgCoordinator = getProfessor(input.tgCoordinatorRegistration());
        var courseCoordinator = getProfessor(
                input.courseCoordinatorRegistration()
        );
        var parameters = new CourseParameters(
                input.availableShifts(),
                input.availableDisciplines()
        );

        var course = getCourse(
                input,
                parameters,
                tgCoordinator,
                courseCoordinator
        );
        var courseSaved = courseRepository.save(course);
        var tgCoordinatorInfo = getCoordinatorInfo(tgCoordinator);
        var courseCoordinatorInfo = getCoordinatorInfo(courseCoordinator);

        return new CreateCourseCase.Output(
                courseSaved.getId(),
                courseSaved.getName(),
                courseSaved.getAvailableShifts(),
                courseSaved.getAvailableDisciplines(),
                tgCoordinatorInfo,
                courseCoordinatorInfo
        );
    }

    private void validateAdminPermission(String executorEmail) {
        if(executorEmail == null || executorEmail.isBlank()) {
            throw new DomainException(
                    "Acesso negado: falha em tentar validá-lo."
            );
        }

        var administrator = administratorRepository
                .findByEmail(executorEmail);

        if(administrator.isEmpty()) {
            throw new DomainException(
                    "Acesso negado: apenas Admins podem executar esta ação!"
            );
        }
    }

    private Course getCourse(
            Input input,
            CourseParameters parameters,
            Professor tgCoordinator,
            Professor courseCoordinator
    ) {
        Optional<Course> optionalCourse = courseRepository
                .findByName(input.name());

        if(optionalCourse.isPresent()) {
            throw new DomainException(
                    "Esse curso já foi cadastrado no sistema."
            );
        }

        return new Course(
                input.name(),
                parameters,
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