package br.edu.com.tg.manager.core.port.filereader;

import java.io.InputStream;
import java.util.List;

/**
 * Porta de saida. 
 * Define um contrato para qualquer componente que saiba ler um arquivo
 * e transforma-lo em uma lista de objetos.
 */
public interface FileReaderPort {

    /**
     * Lê um fluxo de dados do arquivo e o mapeia para uma lista 
     * de objetos de uma classe alvo.
     * @param inputStream O fluxo de dados do arquivo.
     * @param targetClass A classe para a qual cada linha do arquivo 
     * deve ser mapeada.
     * @return Uma lista de objetos da classe alvo.
     */
    <T> List<T> parse(InputStream inputStream, Class<T> targetClass);
}