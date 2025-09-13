package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.Course;
import br.edu.com.tg.manager.infrastructure.persistence.model.CourseData;

/**
 * Mapa que linka a entidade de domínio do curso com
 * o seu modelo de dados, formando uma ponte lógica
 * entre a camada core e a de persistence.
 */
public class CourseMapper {

    /* Um cadeado, evitando que a classe seja instanciada,
     * pois ela é puramente de serviço.
     */
    private CourseMapper(){}

    /**
     * Método que converte o modelo de dados do curso
     * para a sua entidade domínio.
     * @param data Modelo de dados do curso.
     * @return Entidade de domínio do curso.
     */
    public static Course toDomain(CourseData data) {
        
        /* Se não houver nada no modelo de dados,
         * é retornado um null.
         */
        if (data == null) {
        
            return null;
        }
        
        /* Chama o construtor da entidade de domínio
         * do curso, colocando no valor do nome, o 
         * mesmo que está no modelo de dados.
         */
        var course = new Course(data.getName());

        /* Por estar fora do construtor, o ID é via Set. */
        course.setId(data.getId());
        
        return course;
    }

    public static CourseData toData(Course domain) {
        
        /* Se não houver nada na entidade de domínio,
         * é retornado um null.
         */
        if (domain == null) {
        
            return null;
        }
        
        /* Como o construtor no modelo de dados é
         * vazio, os valores são inseridos apenas 
         * via métodos sets.
         */
        var courseData = new CourseData();
        courseData.setId(domain.getId());
        courseData.setName(domain.getName());
        
        return courseData;
    }
}