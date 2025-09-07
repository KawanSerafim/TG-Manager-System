package br.edu.com.tg.manager.application.dto;

import com.opencsv.bean.CsvBindByName;

/**
 * DTO (Data Transfer Object) que representa uma única 
 * linha do arquivo CSV de alunos.
 * Usamos um 'record' para uma classe de dados imutável e concisa.
 * As anotações @CsvBindByName vêm da biblioteca OpenCSV 
 * e mapeiam as colunas do cabeçalho do CSV para os campos do record.
 */
public class StudentCsvDTO {

    @CsvBindByName(column = "ALUNO", required = true)
    private String name;

    @CsvBindByName(column = "RA", required = true)
    private String registration;

    public StudentCsvDTO() {}
    
    public String getName() {
    
        return name;
    }

    public void setName(String name) {
    
        this.name = name;
    }

    public String getRegistration() {
    
        return registration;
    }

    public void setRegistration(String registration) {
    
        this.registration = registration;
    }
}