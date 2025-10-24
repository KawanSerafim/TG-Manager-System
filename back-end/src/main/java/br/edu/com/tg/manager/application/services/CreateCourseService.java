package br.edu.com.tg.manager.application.services;

import br.edu.com.tg.manager.core.domain.entities.Course;
import br.edu.com.tg.manager.core.domain.entities.Professor;
import br.edu.com.tg.manager.core.domain.exceptions.DomainException;
import br.edu.com.tg.manager.core.ports.repositories.CourseRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.usecases.CreateCourseCase;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Implementador de caso de uso:
 * Implementa o funcionamento e a lógica do caso de uso de criar um curso e
 * o persistir no banco de dados. Por pertencer à infraestrutura da aplicação,
 * esta classe utiliza da anotação Service do SpringBoot, que é uma
 * especialização da anotação Component, permitindo que o framework gerencie
 * a classe.
 */
@Service
public class CreateCourseService implements CreateCourseCase {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    /**
     * Construtor de injeção de dependência:
     * Realiza, através do Spring Boot, a injeção de dependência dos
     * repositórios de domínio e injeta a dependência que, quando
     * CreateCourseCase é instanciado por outra classe, a implementação da
     * interface é assumida por esta classe aqui.
     * @param courseRepository Repositório de domínio do curso.
     * @param professorRepository Repositório de domínio do professor.
     */
    public CreateCourseService(

        CourseRepository courseRepository,
        ProfessorRepository professorRepository
    ) {

        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Output execute(Input input) {

        // Captura o coordenador de TG.
        var tgCoordinator = getProfessor(input.tgCoordinatorRegistration());

        // Captura o coordenador de curso.
        var courseCoordinator = getProfessor(

            input.courseCoordinatorRegistration()
        );

        // Captura um objeto de curso com as informações obtidas.
        var course = getCourse(

            input,
            tgCoordinator,
            courseCoordinator
        );

        // Salva o curso no banco de dados.
        var courseSaved = courseRepository.save(course);

        // Captura os records de exibição dos coordenadores.
        var tgCoordinatorInfo = getCoordinatorInfo(tgCoordinator);
        var courseCoordinatorInfo = getCoordinatorInfo(courseCoordinator);

        // Retorna o output obtido com o curso persistido.
        return new CreateCourseCase.Output(

            courseSaved.getId(),
            courseSaved.getName(),
            courseSaved.getShift(),
            tgCoordinatorInfo,
            courseCoordinatorInfo
        );
    }

    /**
     * Método de aplicação:
     * Tenta encontrar um curso com o nome e o turno fornecido, e se não achar,
     * insere num objeto Course.
     * @param input Porta-dados da requisição.
     * @param tgCoordinator Coordenador de TG do curso.
     * @param courseCoordinator Coordenador do Curso.
     * @return Course.
     */
    private Course getCourse(

        Input input,
        Professor tgCoordinator,
        Professor courseCoordinator
    ) {

        /*
         * Insere num Optional, o curso que será buscado pelo repositório
         * usando o seu nome e seu turno.
         */
        Optional<Course> optionalCourse = courseRepository
            .findByNameAndShift(input.name(), input.shift());

        if(optionalCourse.isPresent()) {

            throw new DomainException(

                "Esse curso já foi cadastrado no sistema."
            );
        }

        return new Course(

            input.name(),
            input.shift(),
            tgCoordinator,
            courseCoordinator
        );
    }

    /**
     * Método de aplicação:
     * Constroi um record com as informações de coordenador que serão exibidas
     * na requisição.
     * @param coordinator Professor com cargo de coordenador.
     * @return CoordinatorInfo.
     */
    private CoordinatorInfo getCoordinatorInfo(Professor coordinator) {

        // Retorna o record construído.
        return new CreateCourseCase.CoordinatorInfo(

            coordinator.getId(),
            coordinator.getName()
        );
    }

    /**
     * Método de aplicação.
     * Tenta encontrar um professor com a matrícula fornecida, se não achar,
     * insere num objeto Professor.
     * @param registration Matrícula do professor.
     * @return Professor.
     */
    private Professor getProfessor(String registration) {

        // Tenta buscar um professor através do repositório.
        return professorRepository
            .findByRegistration(registration)
            .orElseThrow(() -> new DomainException(

                // Se não achar, lança exceção de domínio.
                "O professor com matrícula = " + registration +
                " não foi encontrado."
            ));
    }
}