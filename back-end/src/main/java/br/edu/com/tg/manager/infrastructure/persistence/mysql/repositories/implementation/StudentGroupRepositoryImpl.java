package br.edu.com.tg.manager.infrastructure.persistence.mysql.repositories
        .implementation;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
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

/**
 * Implementador de repositório:
 * Implementa o repositório de domínio da entidade Turma, executando os métodos
 * do contrato com o repositório Spring JPA. Por pertencer à infraestrutura da
 * aplicação, esta classe utiliza da anotação Repository do Spring Boot,
 * permitindo que o framework manipule o banco de dados com os métodos do
 * contrato.
 */
@Repository
public class StudentGroupRepositoryImpl implements StudentGroupRepository {

    private final SpringStudentGroupRepository springRepository; 
    private final StudentGroupMapper studentGroupMapper;
    private final CourseMapper courseMapper;

    /**
     * Construtor de injeção de dependência:
     * Injeta a dependência que, quando StudentGroupRepository é instanciado por
     * outra classe, a implementação da interface é assumida por esta classe
     * aqui.
     * @param springRepository Repositório Spring JPA da turma.
     * @param studentGroupMapper Mapeador da turma.
     * @param courseMapper Mapeador do curso.
     */
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
     * @return 
     */
    @Override
    public StudentGroup save(StudentGroup studentGroup) {

        /*
         * Converte a entidade de domínio para um modelo de dados, para poder
         * persistir no banco de dados.
         */
        var studentGroupModel = studentGroupMapper.toModel(studentGroup);
        springRepository.save(studentGroupModel);

        /*
         * Ao salvar, retorna uma entidade de domínio utilizando do modelo de
         * dados obtido, para que o ID seja retornado com o valor atualizado.
         */
        return studentGroupMapper.toDomain(studentGroupModel);
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

        // Tenta encontrar a turma com o curso, ano e semestre fornecido.
        Optional<StudentGroupModel> optionalStudentGroupModel = springRepository
            .findByCourseAndYearAndSemester(
                
                courseMapper.toModel(course),
                year,
                semester
            );

        /*
         * Se achar a turma, converte o modelo de dados para entidade de
         * domínio, para poder retornar o tipo certo.
         */
        return optionalStudentGroupModel.map(studentGroupMapper::toDomain);
    }
}