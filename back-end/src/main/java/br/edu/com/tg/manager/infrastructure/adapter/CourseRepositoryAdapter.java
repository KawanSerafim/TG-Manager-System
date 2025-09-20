package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.core.port.repository.CourseRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.CourseMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.
CourseJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Adaptor de repositório.
 * Ele implementa o contrato de domínio do repositório de uma entidade
 * de domínio: o curso.
 * 
 * Anotação @Component: indica ao framework para agir como o 'contêiner' 
 * desta classe, pois será injetado em um construtor que pede o contrato
 * do repositório do curso.
 */
@Component
public class CourseRepositoryAdapter implements CourseRepository {

    /* A interface do Spring Data JPA do curso que executa as 
     * operações no banco.
     */
    private final CourseJpaRepository jpaRepository;

    /**
     * Injeção de dependência via construtor.
     * @param jpaRepository Instância da interface de repositório
     * Spring Boot Data da entidade curso.
     */
    public CourseRepositoryAdapter(CourseJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Course save(Course course) {
        
        /* Pega a entidade de domínio e o converte para um
         * modelo de dados dela.
         */
        var courseData = CourseMapper.toData(course);

        /* Utiliza do JPA para salvar o modelo de dados no banco de dados,
         * inserindo em um novo modelo de dados, com o ID atualizado pelo
         * IDENTITY.
         */
        var saveData = jpaRepository.save(courseData);

        /* Retorna uma entidade de domínio baseada no novo modelo de dados,
         * para que o ID não seja nulo.
         */
        return CourseMapper.toDomain(saveData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Course> findByName(String name) {

        /* Utiliza o JPA para executar a busca pelo modelo de
         * dados do curso. Se encontrar,
         * o converte para uma entidade de domínio e o retorna.
         */
        return jpaRepository.findByName(name)
            .map(CourseMapper::toDomain);
    }
}