package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.enums.ProfessorRole;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.ListTgCoordinatorsCase;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListTgCoordinatorsService implements ListTgCoordinatorsCase {
    private final ProfessorRepository professorRepository;

    public ListTgCoordinatorsService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Output> execute() {
        List<Professor> tgCoordinators = professorRepository
                .findAllByRole(ProfessorRole.TG_COORDINATOR);

        return tgCoordinators.stream()
                .map(tgCoordinator -> new Output(
                        tgCoordinator.getName(),
                        tgCoordinator.getRegistration()
                ))
                .toList();
    }
}