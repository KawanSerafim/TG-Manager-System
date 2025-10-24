package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.enums.CourseShift;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a
 * lógica de criar um curso.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface CreateCourseCase {

    record Input(

        String name,
        CourseShift shift,
        String tgCoordinatorRegistration,
        String courseCoordinatorRegistration
    ) {}

    record CoordinatorInfo(

        Long id,
        String name
    ) {}

    record Output(

        Long id,
        String name,
        CourseShift shift,
        CoordinatorInfo tgCoordinator,
        CoordinatorInfo courseCoordinator
    ) {}

    /**
     * Executa a criação de um novo curso, e o persiste no banco de dados.
     * @param input Porta-dados da requisição.
     * @return Porta-dados da resposta da requisição.
     */
    Output execute(Input input);
}