package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.port.repository.CourseRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.CourseJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseJpaRepository jpaRepository;

    public CourseRepositoryAdapter(CourseJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public Course save(Course course) {
        
        var courseData = CourseMapper.toData(course);
        var saveData = jpaRepository.save(courseData);

        return CourseMapper.toDomain(saveData);
    }

    @Override
    public Optional<Course> findByName(String name) {

        return jpaRepository.findByName(name)
            .map(CourseMapper::toDomain);
    }
}