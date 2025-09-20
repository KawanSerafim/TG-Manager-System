package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.core.port.repository.StudentGroupRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.
StudentGroupMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.
StudentGroupJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Adaptor de repositório.
 * Ele implementa o contrato de domínio do repositório de uma entidade
 * de domínio: a turma.
 * 
 * Anotação @Component: indica ao framework para agir como o 'contêiner'
 * desta classe, pois será injetado em um construtor que pede o contrato
 * do repositório da turma.
 */
@Component
public class StudentGroupRepositoryAdapter implements StudentGroupRepository {

    /* A interface do Spring Data JPA da turma que executa as 
     * operações no banco.
     */
    private final StudentGroupJpaRepository jpaRepository;

    /**
     * Injeção de dependência via construtor.
     * @param jpaRepository Instância da interface de repositório
     * Spring Boot Data da entidade turma.
     */
    public StudentGroupRepositoryAdapter(
        StudentGroupJpaRepository jpaRepository
    ) {

        this.jpaRepository = jpaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentGroup save(StudentGroup studentGroup) {

        /* Pega a entidade de domínio e o converte para um
         * modelo de dados dela.
         */
        var studentGroupData = StudentGroupMapper.toData(studentGroup);
        
        /* Utiliza do JPA para salvar o modelo de dados no banco de dados,
         * inserindo em um novo modelo de dados, com o ID atualizado pelo
         * IDENTITY.
         */
        var savedData = jpaRepository.save(studentGroupData);
        
        /* Retorna uma entidade de domínio baseada no novo modelo de dados,
         * para que o ID não seja nulo.
         */
        return StudentGroupMapper.toDomain(savedData);
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
        
        /* Coleta um modelo de dados da entidade de domínio do curso,
         * para que seja usada na busca do repositório JPA.
         */
        var courseData = CourseMapper.toData(course);
        
        /* Utiliza o JPA para executar a busca pelo modelo de dados
         * do curso. Se encontrar, o converte para uma entidade de 
         * domínio e o retorna.
         */
        return jpaRepository.findByCourseAndYearAndSemester(courseData, year, semester)
            .map(StudentGroupMapper::toDomain);
    }   
}