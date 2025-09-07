package br.edu.com.tg.manager.application.service;

import br.edu.com.tg.manager.core.port.CsvReaderPort;
import br.edu.com.tg.manager.core.usecase.ReadCsvUseCase;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReadCsvService implements ReadCsvUseCase{

    private static final Logger logger = LoggerFactory.getLogger(ReadCsvService.class);
    private final CsvReaderPort csvReaderPort;

    public ReadCsvService(CsvReaderPort csvReaderPort) {

        this.csvReaderPort = csvReaderPort;
    }

    @Override
    public <T> List<T> importFromCsv(InputStream inputStream, Class<T> targetClass) {
        
        logger.info("Iniciando a importação de dados do arquivo CSV para o tipo {}", targetClass.getSimpleName());

        List<T> records = csvReaderPort.parse(inputStream, targetClass);

        logger.info("{} registros foram lidos com sucesso do arquivo CSV.", records.size());

        return records;
    }
}