package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.StudentGroupMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.StudentGroupJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StudentGroupRepositoryAdapter implements StudentGroupRepository {

    private final StudentGroupJpaRepository jpaRepository;

    public StudentGroupRepositoryAdapter(StudentGroupJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    @Override
    public StudentGroup save(StudentGroup studentGroup) {

        var studentGroupData = StudentGroupMapper.toData(studentGroup);
        var savadData = jpaRepository.save(studentGroupData);
        
        return StudentGroupMapper.toDomain(savadData);
    }

    @Override
    public Optional<StudentGroup> findByCourseName(String courseName) {

        return jpaRepository.findByCourseName(courseName)
        .map(StudentGroupMapper::toDomain);
    }
}