package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.StudentGroupMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.StudentGroupJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

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
    public Optional<StudentGroup> findByCourseAndYearAndSemester(Course course, Integer year, Integer semester) {
        
        var courseData = CourseMapper.toData(course);
        
        return jpaRepository.findByCourseAndYearAndSemester(courseData, year, semester)
            .map(StudentGroupMapper::toDomain);
    }   
}