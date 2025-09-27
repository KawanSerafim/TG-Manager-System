package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
.implementation;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
.CourseModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
.SpringCourseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementação de repositório.
 * Implementa o repositório de domínio, executando os métodos do contrato
 * com o repositório Spring JPA.
 */
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final SpringCourseRepository springRepository;
    private final CourseMapper courseMapper;

    public CourseRepositoryImpl(

        SpringCourseRepository springRepository,
        CourseMapper courseMapper
    ) {

        this.springRepository = springRepository;
        this.courseMapper = courseMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Course course) {

        var courseModel = courseMapper.toModel(course);
        springRepository.save(courseModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Course> findByNameAndShift(String name, CourseShift shift) {

        Optional<CourseModel> optionalCourseModel = springRepository
            .findByNameAndShift(name, shift);

        return optionalCourseModel.map(courseMapper::toDomain);
    }
}