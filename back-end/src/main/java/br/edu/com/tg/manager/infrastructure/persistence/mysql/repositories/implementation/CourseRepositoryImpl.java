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
 * Implementador de repositório:
 * Implementa o repositório de domínio da entidade Course, executando os métodos
 * do contrato com o repositório Spring JPA. Por pertencer à infraestrutura da
 * aplicação, esta classe utiliza da anotação Repository do Spring Boot,
 * permitindo que o framework manipule o banco de dados com os métodos do
 * contrato.
 */
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final SpringCourseRepository springRepository;
    private final CourseMapper courseMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta a dependência que, quando CourseRepository é instanciado por outra
     * classe, a implementação da interface é assumida por esta classe aqui.
     * @param springRepository Repositório Spring JPA do curso.
     * @param courseMapper Mapeador do curso.
     */
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

        /*
         * Converte a entidade de domínio para um modelo de dados, para poder
         * persistir no banco de dados.
         */
        var courseModel = courseMapper.toModel(course);
        springRepository.save(courseModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Course> findByNameAndShift(String name, CourseShift shift) {

        // Tenta encontrar um curso no banco com o nome e o turno fornecido.
        Optional<CourseModel> optionalCourseModel = springRepository
            .findByNameAndShift(name, shift);

        /*
         * Se achar o curso, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalCourseModel.map(courseMapper::toDomain);
    }
}