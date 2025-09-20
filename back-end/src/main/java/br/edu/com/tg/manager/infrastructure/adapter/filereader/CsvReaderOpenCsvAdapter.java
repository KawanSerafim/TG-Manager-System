package br.edu.com.tg.manager.infrastructure.adapter.filereader;

import br.edu.com.tg.manager.core.exception.FileParsingException;
import br.edu.com.tg.manager.core.port.filereader.FileReaderPort;
import com.opencsv.bean.CsvToBeanBuilder;
//import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@Component
public class CsvReaderOpenCsvAdapter implements FileReaderPort {

    @Override
    public <T> List<T> parse(InputStream inputStream, Class<T> targetClass) {
        
        try(InputStreamReader reader = new InputStreamReader(
            inputStream, StandardCharsets.UTF_8)) {
            
            return new CsvToBeanBuilder<T>(reader)
                .withType(targetClass)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .withSkipLines(1)
                .build()
                .parse();

        } catch (Exception e) {

            throw new FileParsingException(
                "Falha ao interpretar o arquivo CSV com a  " +
                "biblioteca OpenCSV", e
            );
        }
    }
}