package br.edu.com.tg.manager.core.ports.gateways;

import java.io.InputStream;
import java.util.List;

import br.edu.com.tg.manager.core.domain.entities.enums.CourseShift;

/**
 * Portão de acesso.
 * Define um contrato para a ação de ler dados de alunos e demais informações
 * de turma, vindos de um arquivo.
 */
public interface StudentDataReader {

    /**
     * Porta-dados.
     * Carrega os dados dos alunos que estão dentro de um arquivo, que são:
     * - nome do aluno.
     * - RA do aluno.
     */
    record StudentData(

        String name,
        String registration
    ) {}

    /**
     * Porta-dados.
     * Carrega os demais dados da turma no arquivo, que são:
     * - ano.
     * - semestre.
     * - turno.
     * Além disso, instancia uma lista de records de alunos.
     */
    record FileData(

        Integer year,
        Integer semester,
        CourseShift shift,
        List<StudentData> students
    ) {}

    /**
     * Lê um fluxo de dados de um arquivo e o converte em FileData.
     * @param fileStream Fluxo de dados do arquivo.
     * @return FileData.
     */
    FileData read(InputStream fileStream);
}