package br.edu.com.tg.manager.core.usecase;

import java.io.InputStream;

/**
 * Porta de entrada.
 * Contrato referente ao caso de uso de importar
 * alunos vindo de um CSV.
 */
public interface ImportStudentsFromCsvUseCase {

    /**
     * Determina o método de execução do caso de uso.
     * @param inputStream O fluxo do arquivo CSV a ser importado.
     * @param targetClass A classe que representa os dados a serem extraídos.
     * @return Uma lista com os objetos extraídos do arquivo.
     */
    void importFromCsv(InputStream inputStream, String courseName, Integer year, Integer semester);
}