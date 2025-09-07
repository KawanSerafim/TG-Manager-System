package br.edu.com.tg.manager.application.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * DTO (Data Transfer Object) que representa uma única 
 * linha do arquivo CSV de alunos.
 * Usamos um 'record' para uma classe de dados imutável e concisa.
 * As anotações @CsvBindByName vêm da biblioteca OpenCSV 
 * e mapeiam as colunas do cabeçalho do CSV para os campos do record.
 */
public record StudentCsvDTO(

    @CsvBindByName(column = "ALUNO", required = true)
    String name,

    @CsvBindByName(column = "RA", required = true)
    String registration
) {
}