package br.edu.com.tg.manager.infrastructure.adapter;

import br.edu.com.tg.manager.core.exception.CsvParsingException;
import br.edu.com.tg.manager.core.port.CsvReaderPort;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvReaderOpenCsvAdapter implements CsvReaderPort {

    private static final Logger logger = LoggerFactory.getLogger(CsvReaderOpenCsvAdapter.class);

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

            logger.error(">>>>>> ERRO ORIGINAL DA BIBLIOTECA OPENCVSV: ", e);

            throw new CsvParsingException("Falha ao interpretar o arquivo CSV com a biblioteca OpenCSV", e);
        }
    }
}