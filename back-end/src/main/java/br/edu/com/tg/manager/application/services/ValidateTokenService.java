package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.ports.gateways.TokenCache;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.ValidateTokenCase;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenService implements ValidateTokenCase {

    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final TokenCache tokenCache;


    public ValidateTokenService(

        AdministratorRepository administratorRepository,
        ProfessorRepository professorRepository,
        StudentRepository studentRepository,
        TokenCache tokenCache
    ) {

        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.tokenCache = tokenCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Input input) {


    }
}