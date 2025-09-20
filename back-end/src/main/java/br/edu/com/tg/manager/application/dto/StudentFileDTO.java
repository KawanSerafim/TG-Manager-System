package br.edu.com.tg.manager.application.dto;

/**
 * Molde de dados.
 * Uma classe, também podendo ser um record, que tem o objetivo de carregar
 * dados entre as camadas, especialmente na fronteira da aplicação, recebendo
 * dados de requisições ou de arquivos.
 * Cada campo da DTO será o campo que ele irá receber. Ou seja, quem determina
 * o que ele irá carregar, é justamente o mensageiro, mesmo que ele não
 * represente totalmente a entidade de domínio inspirada.
 */
public class StudentFileDTO {

    private String name;
    private String registration;

    /* Construtor vazio, necessário para as camadas exteriores que irão
     * utilizá-lo.
     */
    public StudentFileDTO() {}
    
    /* Getter e Setters. */

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