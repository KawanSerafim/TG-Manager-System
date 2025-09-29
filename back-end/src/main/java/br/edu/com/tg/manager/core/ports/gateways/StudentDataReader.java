package br.edu.com.tg.manager.core.ports.gateways;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import java.io.InputStream;
import java.util.List;

/**
 * Portão de acesso de domínio:
 * Define um contrato para a ação de ler dados de alunos e demais informações
 * de turma, todos vindos de um arquivo.
 */
public interface StudentDataReader {

    /**
     * Porta-dados:
     * Carrega os dados dos alunos que estão num arquivo.
     */
    record StudentData(

        String name,
        String registration
    ) {}

    /**
     * Porta-dados:
     * Carrega os demais dados da turma que estão num arquivo. Além disso,
     * instancia uma lista de records de alunos.
     */
    record FileData(

        Integer year,
        Integer semester,
        CourseShift shift,
        List<StudentData> students
    ) {}

    /**
     * Método de contrato de domínio:
     * Lê um fluxo de dados de um arquivo e o converte em FileData.
     * @param fileStream Fluxo de dados do arquivo.
     * @return FileData.
     */
    FileData read(InputStream fileStream);
}