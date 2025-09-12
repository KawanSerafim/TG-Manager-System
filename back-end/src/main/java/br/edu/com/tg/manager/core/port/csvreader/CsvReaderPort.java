package br.edu.com.tg.manager.core.port.csvreader;

import java.io.InputStream;
import java.util.List;

/**
 * Porta de saida. 
 * Define um contrato para qualquer componente que saiba
 * ler um CSV e transforma-lo em uma lista de objetos.
 */
public interface CsvReaderPort {

    /**
     * Lê um fluxo de dados CSV e o mapeia para uma lista 
     * de objetos de uma classe alvo.
     * @param inputStream O fluxo de dados do arquivo CSV.
     * @param targetClass A classe para a qual cada linha do 
     * CSV deve ser mapeada.
     * @return Uma lista de objetos da classe alvo.
     */
    <T> List<T> parse(InputStream inputStream, Class<T> targetClass);
}