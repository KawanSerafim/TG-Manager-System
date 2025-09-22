package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;
import java.util.Optional;

public class CreateStudentGroupService implements CreateStudentGroupCase {

    private final CourseRepository courseRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;
    private final StudentDataReader studentDataReader;

    /**
     * Construtor de injeção de dependência.
     * Injeta a dependência de quem for instanciar o caso de uso:
     * CreateStudentGroupCase
     * @param courseRepository Repositório de domínio do curso.
     * @param studentGroupRepository Repositório de domínio da turma.
     * @param studentRepository Repositório de domínio do aluno.
     * @param studentDataReader Contrato para ler dados de aluno e demais
     * informações de turma, vindos de um arquivo.
     */
    public CreateStudentGroupService(

        CourseRepository courseRepository,
        StudentGroupRepository studentGroupRepository,
        StudentRepository studentRepository,
        StudentDataReader studentDataReader
    ) {

        this.courseRepository = courseRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.studentRepository = studentRepository;
        this.studentDataReader = studentDataReader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Input input) {

        /* Salva numa instância, um record que traduz os dados vindo
         * do arquivo.
        */
        var fileData = studentDataReader.read(input.file());

        /* Salva numa instância, o curso que achar com o nome e o turno
         * fornecido pelo coordenador de TG. Se não achar, sobe um
         * DomainException de entidade não encontrada.
        */
        Course course = courseRepository.findByNameAndShift(

            input.courseName(),
            fileData.shift()
        ).orElseThrow(() -> new DomainException(
            
            "O curso com nome = " + input.courseName() + ", turno = " +
            fileData.shift() + " não foi encontrado."
        ));

        /* Cria um objeto de turma com os dados capturados. */
        var studentGroup = new StudentGroup(

            course,
            input.discipline(),
            fileData.year(),
            fileData.semester(),
            input.temporaryPassword()
        );

        studentGroupRepository.save(studentGroup);

        for(var students : fileData.students()) {

            /* Insere num Optional de Student, buscando o objeto pelo método de
             * busca do repositório.
             */
            Optional<Student> optionalStudent = studentRepository
            .findByRegistration(students.registration());

            /* Verifica se o Optional retornou vazio. Se sim, o Student é
             * persistido no banco.
             */
            if(optionalStudent.isEmpty()) {

                var student = new Student(

                    students.name(),
                    students.registration(),
                    studentGroup
                );

                studentRepository.save(student);
            }
        }
    }
}