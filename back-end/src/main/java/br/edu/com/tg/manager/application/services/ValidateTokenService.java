package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.ValidateTokenCase;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenService implements ValidateTokenCase {

    private AdministratorRepository administratorRepository;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;

    public ValidateTokenService(

        AdministratorRepository administratorRepository,
        ProfessorRepository professorRepository,
        StudentRepository studentRepository
    ) {

        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void execute(Input input) {


    }
}