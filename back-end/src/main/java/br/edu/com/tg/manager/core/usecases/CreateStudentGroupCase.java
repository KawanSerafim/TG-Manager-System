package br.edu.com.tg.manager.core.usecases;

import java.io.InputStream;

import br.edu.com.tg.manager.core.domain.enums.Discipline;

/**
 * Caso de uso de domínio.
 * Determina o contrato para a ação de criar uma nova turma.
 */
public interface CreateStudentGroupCase {

    /**
     * Porta-dados.
     * Carrega os dados enviados pelo coordenador de TG, que são:
     * - nome do curso.
     * - disciplina.
     * - arquivo Excel.
     * - senha temporária.
     */
    record Input(

        String courseName,
        Discipline discipline,
        InputStream file,
        String temporaryPassword
    ) {}

    /**
     * Executa a criação de uma nova turma, e a persiste no banco de dados.
     * @param input
     */
    void execute(Input input);
}