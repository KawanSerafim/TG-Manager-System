package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;

public class CreateStudentGroupService implements CreateStudentGroupCase {

    private final CourseRepository courseRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;

    /**
     * Construtor de injeção de dependência.
     * Injeta a dependência de quem for instanciar o caso de uso:
     * CreateStudentGroupCase
     * @param courseRepository Repositório de domínio do curso.
     * @param studentGroupRepository Repositório de domínio da turma.
     * @param studentRepository Repositório de domínio do aluno.
     */
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
    public void execute(Input input) {
        
    }
}