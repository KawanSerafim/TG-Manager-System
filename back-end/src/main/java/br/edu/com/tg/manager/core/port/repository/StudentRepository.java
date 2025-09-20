package br.edu.com.tg.manager.core.port.repository;

import br.edu.com.tg.manager.core.entity.Student;
import java.util.Optional;

/**
 * Porta de saída.
 * Define um contrato para a persistência de dados da entidade de domínio
 * que representa o aluno.
 */
public interface StudentRepository {

    /**
     * Salva uma instância do aluno no banco de dados.
     * @param student Objeto do aluno.
     * @return O objeto do aluno salvo.
     */
    Student save(Student student);

    /**
     * Busca um aluno pelo seu RA.
     * @param registration RA do aluno.
     * @return Um null ou o objeto do aluno.
     */
    Optional<Student> findByRegistration(String registration);
}