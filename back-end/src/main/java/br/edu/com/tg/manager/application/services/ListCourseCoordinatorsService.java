package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.ListCourseCoordinatorsCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListCourseCoordinatorsService implements
        ListCourseCoordinatorsCase {
    private final ProfessorRepository professorRepository;

    public ListCourseCoordinatorsService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Output> execute() {
        List<Professor> courseCoordinators = professorRepository
                .findAllByRole(ProfessorRole.COURSE_COORDINATOR);

        return courseCoordinators.stream()
                .map(courseCoordinator -> new Output(
                        courseCoordinator.getName(),
                        courseCoordinator.getRegistration()
                ))
                .toList();
    }
}