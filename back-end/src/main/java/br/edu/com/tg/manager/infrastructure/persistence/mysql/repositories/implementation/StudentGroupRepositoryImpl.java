package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
.implementation;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
.StudentGroupMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models.StudentGroupModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
.SpringStudentGroupRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementação de repositório.
 * Implementa o repositório de domínio, executando os métodos do contrato
 * com o repositório Spring JPA.
 */
@Repository
public class StudentGroupRepositoryImpl implements StudentGroupRepository {

    private final SpringStudentGroupRepository springRepository; 
    private final StudentGroupMapper studentGroupMapper;
    private final CourseMapper courseMapper;

    public StudentGroupRepositoryImpl(
        
        SpringStudentGroupRepository springRepository,
        StudentGroupMapper studentGroupMapper,
        CourseMapper courseMapper
    ) {

        this.springRepository = springRepository;
        this.studentGroupMapper = studentGroupMapper;
        this.courseMapper = courseMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(StudentGroup studentGroup) {
        
        var studentGroupModel = studentGroupMapper.toModel(studentGroup);
        springRepository.save(studentGroupModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<StudentGroup> findByCourseAndYearAndSemester(
    
        Course course,
        Integer year,
        Integer semester
    ) {
        
        Optional<StudentGroupModel> optionalStudentGroupModel = springRepository
            .findByCourseAndYearAndSemester(
                
                courseMapper.toModel(course),
                year,
                semester
            );
        
        return optionalStudentGroupModel.map(studentGroupMapper::toDomain);
    }
}