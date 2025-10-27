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

@Component
public class ExcelStudentDataReader implements StudentDataReader {
    /**
     * {@inheritDoc}
     */
    @Override
    public FileData read(InputStream fileStream) {
        try(Workbook workbook = new XSSFWorkbook(fileStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            String yearSemesterCell = getCellStringValue(
                    headerRow.getCell(0)
            );

            int yearSemesterInt = (int) Integer.parseInt(yearSemesterCell);
            int year = yearSemesterInt / 10;
            int semester = yearSemesterInt % 10;

            Cell shiftCell = headerRow.getCell(2);
            CourseShift shift = getShift(shiftCell);

            List<StudentData> students = new ArrayList<>();

            for(int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if(row == null) continue;

                String name = getCellStringValue(row.getCell(1));
                String registration = getCellStringValue(row.getCell(0));

                if(!name.isBlank() && !registration.isBlank()) {
                    students.add(new StudentData(name, registration));
                }
            }
            return new FileData(year, semester, shift, students);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao processar o arquivo Excel", e);
        }
    }

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

    private String getCellStringValue(Cell cell) {
        if(cell == null) {
            return "";
        }

        if(cell.getCellType() == CellType.NUMERIC) {
            return new DataFormatter().formatCellValue(cell);
        }
        return cell.getStringCellValue().trim();
    }
}