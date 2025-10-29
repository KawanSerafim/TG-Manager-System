package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de cadastrar um curso.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface CreateCourseCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param name Nome do curso.
     * @param shift Turno do curso.
     * @param tgCoordinatorRegistration Coordenador de TG do curso.
     * @param courseCoordinatorRegistration Coordenador do curso.
     */
    record Input(
            String name,
            CourseShift shift,
            String tgCoordinatorRegistration,
            String courseCoordinatorRegistration
    ) {}

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados de informações dos coordenadores do curso.
     * @param id ID do coordenador.
     * @param name Nome do coordenador.
     */
    record CoordinatorInfo(
            Long id,
            String name
    ) {}

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados resposta a requisição POST.
     * @param id ID do curso.
     * @param name Nome do curso.
     * @param shift Turno do curso.
     * @param tgCoordinator Coordenador de TG do curso.
     * @param courseCoordinator Coordenador do curso.
     */
    record Output(
            Long id,
            String name,
            CourseShift shift,
            CoordinatorInfo tgCoordinator,
            CoordinatorInfo courseCoordinator
    ) {}

    /**
     * Método de contrato de núcleo:
     * Executa o cadastro de um curso no sistema.
     * @param input Porta-dados da requisição POST.
     * @return Output.
     */
    Output execute(Input input);
}