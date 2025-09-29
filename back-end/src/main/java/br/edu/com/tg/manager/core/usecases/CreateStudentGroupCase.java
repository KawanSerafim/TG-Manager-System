package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.io.InputStream;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a
 * lógica de criar uma turma.
 */
public interface CreateStudentGroupCase {

    /**
     * Porta-dados:
     * Carrega os dados recebidos pela requisição.
     */
    record Input(

        String courseName,
        Discipline discipline,
        InputStream file,
        String temporaryPassword
    ) {}

    /**
     * Método de contrato de domínio:
     * Executa a criação de uma nova turma, e a persiste no banco de dados.
     * @param input Porta-dados da requisição.
     */
    void execute(Input input);
}