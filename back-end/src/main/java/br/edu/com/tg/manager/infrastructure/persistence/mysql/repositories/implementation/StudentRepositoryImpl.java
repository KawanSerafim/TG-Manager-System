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

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final SpringStudentRepository springRepository;
    private final StudentMapper studentMapper;

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
        var studentModel = studentMapper.toModel(student);
        springRepository.save(studentModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findByRegistration(String registration) {
        Optional<StudentModel> optionalStudentModel = springRepository
                .findByRegistration(registration);

        return optionalStudentModel.map(studentMapper::toDomain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findByEmail(String email) {
        Optional<StudentModel> optionalStudentModel = springRepository
                .findByUserAccountEmail(email);

        return optionalStudentModel.map(studentMapper::toDomain);
    }
}