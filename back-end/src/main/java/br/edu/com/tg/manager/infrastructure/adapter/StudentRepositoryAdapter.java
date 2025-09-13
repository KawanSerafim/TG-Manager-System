package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.core.port.repository.StudentRepository;
import br.edu.com.tg.manager.infrastructure.persistence.mapper.StudentMapper;
import br.edu.com.tg.manager.infrastructure.persistence.repository.StudentJpaRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Adaptor de repositório.
 * Ele implementa o contrato de domínio do repositório de
 * uma entidade de domínio: o aluno.
 * 
 * Anotação @Component: indica ao framework para agir como
 * o 'contêiner' desta classe, pois será injetado em um
 * construtor que pede o contrato do repositório do
 * aluno.
 */
@Component
public class StudentRepositoryAdapter implements StudentRepository {

    /* A interface do Spring Data JPA da aluno que executa as 
     * operações no banco.
     */
    private final StudentJpaRepository jpaRepository;

    /**
     * Injeção de dependência via construtor.
     * @param jpaRepository Instância da interface de repositório
     * Spring Boot Data da entidade aluno.
     */
    public StudentRepositoryAdapter(StudentJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student save(Student student) {
        
        /* Pega a entidade de domínio e o converte
         * para um modelo de dados dela.
         */
        var studentData = StudentMapper.toData(student);
        
        /* Utiliza do JPA para salvar o modelo de dados
         * no banco de dados, inserindo em um novo
         * modelo de dados, com o ID atualizado pelo
         * IDENTITY.
         */
        var savedData = jpaRepository.save(studentData);

        /* Retorna uma entidade de domínio baseada no
         * novo modelo de dados, para que o ID não seja
         * nulo.
         */
        return StudentMapper.toDomain(savedData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findByRegistration(String registration) {
        
        /* Utiliza o JPA para executar a busca pelo
         * modelo de dados do aluno. Se encontrar,
         * o converte para uma entidade de domínio
         * e o retorna.
         */
        return jpaRepository.findByRegistration(registration)
            .map(StudentMapper::toDomain);
    }
}