package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .implementation;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.ports.repositories.StudentGroupRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
        .CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.mappers
        .StudentGroupMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.models
        .StudentGroupModel;
import br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .SpringStudentGroupRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

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
    public StudentGroup save(StudentGroup studentGroup) {
        var studentGroupModel = studentGroupMapper.toModel(studentGroup);
        springRepository.save(studentGroupModel);

        return studentGroupMapper.toDomain(studentGroupModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<StudentGroup> findByCourseAndYearAndSemesterAndCourseShiftAndDiscipline(
            Course course,
            Integer year,
            Integer semester,
            CourseShift courseShift,
            Discipline discipline
    ) {
        Optional<StudentGroupModel> optionalStudentGroupModel = springRepository
                .findByCourseAndYearAndSemesterAndCourseShiftAndDiscipline(
                        courseMapper.toModel(course),
                        year,
                        semester,
                        courseShift,
                        discipline
                );

        return optionalStudentGroupModel.map(studentGroupMapper::toDomain);
    }
}