package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import java.util.List;

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
     * @param availableShifts Lista de turnos disponíveis.
     * @param availableDisciplines Lista de disciplinas de TG disponíveis.
     * @param tgCoordinatorRegistration Coordenador de TG do curso.
     * @param courseCoordinatorRegistration Coordenador do curso.
     * @param executorEmail Email do executor da ação.
     */
    record Input(
            String name,
            List<CourseShift> availableShifts,
            List<Discipline> availableDisciplines,
            String tgCoordinatorRegistration,
            String courseCoordinatorRegistration,
            String executorEmail
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
     * @param availableShifts Lista de turnos disponíveis.
     * @param availableDisciplines Lista de disciplinas de TG disponíveis.
     * @param tgCoordinator Coordenador de TG   do curso.
     * @param courseCoordinator Coordenador do curso.
     */
    record Output(
            Long id,
            String name,
            List<CourseShift> availableShifts,
            List<Discipline> availableDisciplines,
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