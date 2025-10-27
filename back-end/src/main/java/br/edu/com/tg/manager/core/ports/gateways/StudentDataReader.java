package br.edu.com.tg.manager.core.ports.gateways;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import java.io.InputStream;
import java.util.List;

/**
 * Portão de acesso de núcleo:
 * Define um contrato para a ação de ler dados de alunos e demais
 * informações de turma, todos vindos de um arquivo.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public interface StudentDataReader {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados de um único aluno que estão num arquivo.
     */
    record StudentData(
            String name,
            String registration
    ) {}

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os metadados da turma que estão no arquivo (ano,
     * semestre, turno) e a lista de alunos (StudentData).
     */
    record FileData(
            Integer year,
            Integer semester,
            CourseShift shift,
            List<StudentData> students
    ) {}

    /**
     * Método de contrato de núcleo:
     * Lê um fluxo de dados de um arquivo e o converte em FileData.
     * @param fileStream Fluxo de dados do arquivo.
     * @return FileData.
     */
    FileData read(InputStream fileStream);
}