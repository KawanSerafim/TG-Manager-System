package br.edu.com.tg.manager.infrastructure.gateways.excel;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/*
 * Anotação @Component: indica ao Spring que esta classe deve ser gerenciada
 * pelo framework.
 */

/**
 * Implementador de portão de acesso:
 * Implementa o funcionamento da leitura de dados vindo de um arquivo em Excel.
 * Por pertencer à infraestrutura da aplicação, esta classe utiliza da anotação
 * Component do SpringBoot, permitindo que o framework gerencie a classe, e a
 * biblioteca do Apache POI, que porta as ferramentas para ler e coletar dados
 * de um Excel.
 */
@Component
public class ExcelStudentDataReader implements StudentDataReader {

    /**
     * {@inheritDoc}
     */
    @Override
    public FileData read(InputStream fileStream) {
        
        try(Workbook workbook = new XSSFWorkbook(fileStream)) {
            
            // Pega a primeira planilha do arquivo.
            Sheet sheet = workbook.getSheetAt(0);

            // Pega a primeira linha da planilha. No caso, o cabeçalho.
            Row headerRow = sheet.getRow(0);

            // Pega a célula A1, onde fica o ano+semestre.
            String yearSemesterCell = getCellStringValue(
                
                headerRow.getCell(0)
            );

            // Converte a célula A1 num valor numérico.
            int yearSemesterInt = (int) Integer.parseInt(yearSemesterCell);
            
            /*
             * A divisão de inteiros permite separar o ano do semestre.
             *
             * Exemplo: 20252/10 = 2025,2. O int ignora o 2 em casa decimal e
             * insere apenas o 2025.
             */
            int year = yearSemesterInt / 10;

            /*
             * Aqui explora o mesmo conceito da divisão, porém pegando o
             * resto dela, que sempre será o valor que a divisão de inteiros 
             * ignora: o semestre.
             */
            int semester = yearSemesterInt % 10;

            // Pega a célula C1, onde fica o turno.
            Cell shiftCell = headerRow.getCell(2);

            /*
             * De acordo com o turno na célula, o valor condizente será
             * atribuído ao Enum CourseShift.
             */
            CourseShift shift = getShift(shiftCell);

            // Lista de porta-dados de Student.
            List<StudentData> students = new ArrayList<>();

            // Itera a planilha, adicionando na lista os alunos encontrados.
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {

                // Pega a linha conforme o índice i.
                Row row = sheet.getRow(i);

                // Pula linhas em branco.
                if(row == null) continue;

                // Pega o conteúdo da linha i e coluna A, e insere no nome.
                String name = getCellStringValue(row.getCell(0));

                // Pega o conteúdo da linha i e coluna B, e insere na matrícula.
                String registration = getCellStringValue(
                    
                    row.getCell(1)
                );

                // Só adiciona na lista se nome e matrícula conterem valores.
                if(!name.isBlank() && !registration.isBlank()) {

                    students.add(new StudentData(name, registration));
                }
            }

            // Retorna um novo porta-dados com as informações coletadas.
            return new FileData(year, semester, shift, students);

        } catch (Exception e) {

            // Lança RuntimeException caso alguma falha no fluxo acima ocorra.
            throw new RuntimeException("Falha ao processar o arquivo Excel", e);
        }
    }

    /**
     * Método de aplicação:
     * Retorna um turno abstraído de uma célula da planilha.
     * @param shiftCell Célula da planilha.
     * @return CourseShift.
     */
    private static CourseShift getShift(Cell shiftCell) {

        String shiftString = shiftCell.getStringCellValue().toUpperCase();
        CourseShift shift = null;

        if(shiftString.contains("MANHÃ")) {

            shift = CourseShift.MORNING;
        }

        if(shiftString.contains("TARDE")) {

            shift = CourseShift.AFTERNOON;
        }

        if(shiftString.contains("NOITE")) {

            shift = CourseShift.NIGHT;
        }

        return shift;
    }

    /**
     * Método de aplicação:
     * Lê o valor de uma célula de forma segura, evitando erros de comparação
     * com texto e número.
     * @param cell Célula da planilha.
     * @return String do valor da célula. 
     */
    private String getCellStringValue(Cell cell) {

        if(cell == null) {

            return "";
        }

        if(cell.getCellType() == CellType.NUMERIC) {

            // Garante a formatação correta de números para String.
            return new DataFormatter().formatCellValue(cell);
        }

        return cell.getStringCellValue().trim();
    }
}