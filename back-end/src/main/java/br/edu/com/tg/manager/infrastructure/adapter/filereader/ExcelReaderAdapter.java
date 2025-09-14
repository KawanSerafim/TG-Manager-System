package br.edu.com.tg.manager.infrastructure.adapter.filereader;

import br.edu.com.tg.manager.application.dto.StudentFileDTO;
import br.edu.com.tg.manager.core.exception.FileParsingException;
import br.edu.com.tg.manager.core.exception.UnsupportedFileTypeException;
import br.edu.com.tg.manager.core.port.filereader.FileReaderPort;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Adaptador de leitor de arquivo Excel.
 * Ele implementa a porta FileReaderPort usando a biblioteca Apache POI para
 * ler arquivos no formato .xlsx, que são as planilhas do Excel da Microsoft.
 * 
 * Anotação @Primary: indica ao framework que a implementação desse adaptador
 * tem a prioridade em relação aos outros.
 * 
 * Anotação @Component: indica ao framework para agir como o 'contêiner'
 * desta classe, pois será injetado em um construtor que pede o contrato
 * do repositório da turma.
 */
@Primary
@Component
public class ExcelReaderAdapter implements FileReaderPort {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> parse(InputStream inputStream, Class<T> targetClass) {
        
        /* Cláusula de guarda que garante que a classe alvo é apenas aluno */
        if(!targetClass.equals(StudentFileDTO.class)) {

            throw new UnsupportedFileTypeException(
                "Este adaptador de Excel, atualmente só suporta " +
                "a criação de alunos."
            );
        }

        /* O XSSFWorkbook permite o fluxo de dados bruto seja aberto para
         * construir um objeto principal que representa o arquivo Excel.
         */
        try(Workbook workbook = new XSSFWorkbook(inputStream)) {

            List<StudentFileDTO> students = new ArrayList<>();

            /* Indica a biblioteca que a planilha escolhida é a primeira. */
            Sheet sheet = workbook.getSheetAt(0);

            /* Marcador de página que se moverá de linha em linha no Excel. Ele
             * está começando pela primeira linha.
             */
            Iterator<Row> rowIterator = sheet.iterator();
            
            /* Pula as duas primeiras linhas. */
            if(rowIterator.hasNext()) rowIterator.next();
            if(rowIterator.hasNext()) rowIterator.next();

            /* Iteração que flui até o final da planilha. */
            while (rowIterator.hasNext()) {
                
                Row dataRow = rowIterator.next();

                /* Pega os dados da coluna A (0), onde estará o nome, e coloca
                 * no objeto que representa a célula.
                 */
                Cell nameCell = dataRow.getCell(0);

                /* Pega os dados da coluna B (1), onde estará a matrícula, e 
                 * coloca no objeto que representa a célula.
                 */
                Cell registrationCell = dataRow.getCell(1);

                /* Só salva no molde da entidade, se houver nome e RA juntos. */
                if(nameCell != null && registrationCell != null) {

                    StudentFileDTO dto = new StudentFileDTO();

                    dto.setName(nameCell.getStringCellValue());
                    dto.setRegistration(
                        registrationCell.getStringCellValue()
                    );

                    students.add(dto);
                }
            }

            return (List<T>) students;
        } catch(Exception e) {

            throw new FileParsingException(
                "Falha ao interpretar o arquivo XLSX", e
            );
        }
    }
}