package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.core.usecases.CreateStudentGroupCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementador de caso de uso:
 * Implementa o funcionamento e a lógica do caso de uso de criar uma turma e
 * a persistir no banco de dados. Por pertencer à infraestrutura da aplicação,
 * esta classe utiliza da anotação Service do SpringBoot, que é uma
 * especialização da anotação Component, permitindo que o framework gerencie
 * a classe.
 */
@Service
public class CreateStudentGroupService implements CreateStudentGroupCase {

    private final CourseRepository courseRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final StudentRepository studentRepository;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência dos
     * repositórios de domínio e injeta a dependência que, quando
     * CreateStudentGroupCase é instanciado por outra classe, a implementação da
     * interface é assumida por esta classe aqui.
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
    public CreateStudentGroupResult execute(Input input) {

        /*
         * Salva numa instância o curso que achar com o nome e o turno
         * fornecido pelo coordenador de TG. Se não achar, sobe um
         * DomainException de entidade não encontrada.
         */
        Course course = courseRepository.findByNameAndShift(

            input.courseName(),
            input.shift()
        ).orElseThrow(() -> new DomainException(

            "O curso com nome = " + input.courseName() + ", turno = " +
            input.shift() + " não foi encontrado."
        ));
        
        StudentGroup studentGroup;

        // Insere num Optional, a turma que será buscada pelo repositório.
        Optional<StudentGroup> optionalGroup = studentGroupRepository
            .findByCourseAndYearAndSemester(
                course,
                input.year(),
                input.semester()
            );

        /*
         * Verifica se a turma com o curso, ano e semestre fornecido já existe
         * no banco de dados. Caso exista, reutiliza a mesma turma.
         */
        if(optionalGroup.isPresent()) {

            studentGroup = optionalGroup.get();
        } else {

            /*
             * Se não, cria uma nova turma, atribui nela os valores capturados
             * pela requisição, e o salva no banco de dados.
             */
            var newStudentGroup = new StudentGroup(

                course,
                input.discipline(),
                input.year(),
                input.semester()
            );

            studentGroup = studentGroupRepository.save(newStudentGroup);
        }

        List<Student> students = new ArrayList<>();

        for(var studentData : input.students()) {

            // Insere num Optional, o aluno que será buscado pelo repositório.
            Optional<Student> optionalStudent = studentRepository
            .findByRegistration(studentData.registration());

            /*
             * Se o Optional for vazio, um novo aluno é criado e salvo no banco
             * de dados. Isso garante que não haja alunos duplicados no sistema.
             */
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

        return new CreateStudentGroupResult(studentGroup, students);
    }
}