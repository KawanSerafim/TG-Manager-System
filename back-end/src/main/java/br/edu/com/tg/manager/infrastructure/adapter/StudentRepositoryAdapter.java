package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.StudentMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.StudentJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class StudentRepositoryAdapter implements StudentRepository {

    private final StudentJpaRepository jpaRepository;

    public StudentRepositoryAdapter(StudentJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    @Override
    public Student save(Student student) {
        
        var studentData = StudentMapper.toData(student);
        var savedData = jpaRepository.save(studentData);

        return StudentMapper.toDomain(savedData);
    }

    @Override
    public Optional<Student> findByRegistration(String registration) {
        
        return jpaRepository.findByRegistration(registration)
            .map(StudentMapper::toDomain);
    }
}