package br.edu.com.tg.manager.core.port.repository;

import br.edu.com.tg.manager.core.entity.Student;
import java.util.Optional;

/**
 * Porta de saída.
 * Define um contrato para a persistência de dados da entidade Student.
 */
public interface StudentRepository {

    /**
     * Salva uma instância de Student no banco de dados.
     * @param student Objeto do aluno.
     * @return A entidade Student salva.
     */
    Student save(Student student);

    /**
     * Busca um Student pela sua matrícula (RA).
     * Optional serve para indicar que um grupo com aquele nome pode não existir.
     * @param registration A matrícula a ser buscada.
     * @return Um Optional contendo o Student se encontrado, ou um Optional vazio.
     */
    Optional<Student> findByRegistration(String registration);
}