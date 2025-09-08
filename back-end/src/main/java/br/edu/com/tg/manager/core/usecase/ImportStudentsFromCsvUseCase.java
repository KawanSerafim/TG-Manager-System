package br.edu.com.tg.manager.core.usecase;

import java.io.InputStream;

/**
 * Porta de entrada.
 * Define o contrato para o caso de uso de importar 
 * dados a partir de um arquivo CSV.
 */

public interface ImportStudentsFromCsvUseCase {

    /**
     * Executa o caso de uso de importação.
     * @param inputStream O fluxo do arquivo CSV a ser importado.
     * @param targetClass A classe que representa os dados a serem extraídos.
     * @return Uma lista com os objetos extraídos do arquivo.
     */
    void importFromCsv(InputStream inputStream, String courseName);
}