package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .implementation;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
        .StudentMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .SpringStudentRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementador de repositório:
 * Implementa o repositório de domínio da entidade Aluno, executando os métodos
 * do contrato com o repositório Spring JPA. Por pertencer à infraestrutura da
 * aplicação, esta classe utiliza da anotação Repository do Spring Boot,
 * permitindo que o framework manipule o banco de dados com os métodos do
 * contrato.
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final SpringStudentRepository springRepository;
    private final StudentMapper studentMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta a dependência que, quando StudentRepository é instanciado por
     * outra classe, a implementação da interface é assumida por esta classe
     * aqui.
     * @param springRepository Repositório Spring JPA do aluno.
     * @param studentMapper Mapeador do aluno.
     */
    public StudentRepositoryImpl(

        SpringStudentRepository springRepository,
        StudentMapper studentMapper
    ) {

        this.springRepository = springRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Student student) {

        /*
         * Converte a entidade de domínio para um modelo de dados, para poder
         * persistir no banco de dados.
         */
        var studentModel = studentMapper.toModel(student);
        springRepository.save(studentModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findByRegistration(String registration) {

        // Tenta encontrar o aluno com a matrícula fornecida.
        Optional<StudentModel> optionalStudentModel = springRepository
            .findByRegistration(registration);

        /*
         * Se achar o aluno, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalStudentModel.map(studentMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findByEmail(String email) {

        // Tenta encontrar o aluno com o email fornecido.
        Optional<StudentModel> optionalStudentModel = springRepository
            .findByUserAccountEmail(email);

        /*
         * Se achar o aluno, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalStudentModel.map(studentMapper::toDomain);
    }
}