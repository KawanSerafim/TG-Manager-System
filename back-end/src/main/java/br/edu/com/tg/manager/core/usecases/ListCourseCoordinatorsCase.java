package br.edu.com.tg.manager.core.usecases;

import java.util.List;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de listar coordenadores de curso.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface ListCourseCoordinatorsCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados resposta a requisição POST.
     * @param name Nome do coordenador de curso.
     * @param registration Matrícula do coordenador de curso.
     */
    record Output(String name, String registration) {}

    /**
     * Método de contráto de núcleo:
     * Executa a listagem de coordenadores de curso.
     * @return Output.
     */
    List<ListCourseCoordinatorsCase.Output> execute();
}