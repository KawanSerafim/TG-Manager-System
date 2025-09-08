package br.edu.com.tg.manager.core.port.repository;

import br.edu.com.tg.manager.core.entity.StudentGroup;
import java.util.Optional;

/**
 * Porta de saída.
 * Define um contrato para a persistência de dados da entidade StudentGroup.
 */
public interface StudentGroupRepository {

    /**
     * Salva uma instância de StudentGroup no banco de dados.
     * Pode ser usado tanto para criar um novo grupo quanto para atualizar um existente.
     * @param studentGroup Objeto da turma.
     * @return A entidade StudentGroup salva.
     */
    StudentGroup save(StudentGroup studentGroup);

    /**
     * Busca um StudentGroup pelo seu nome.
     * Optional serve para indicar que um grupo com aquele nome pode não existir.
     * @param courseName O nome do curso da turma a ser buscado.
     * @return Um Optional contendo o StudentGroup se encontrado, ou um Optional vazio.
     */
    Optional<StudentGroup> findByCourseName(String courseName);
}