package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentGroupData;

/**
 * Mapa que linka a entidade de domínio da turma com
 * o seu modelo de dados, formando uma ponte lógica
 * entre a camada core e a de persistence.
 */
public class StudentGroupMapper {

    /* Um cadeado, evitando que a classe seja instanciada,
     * pois ela é puramente de serviço.
     */
    private StudentGroupMapper(){}
    
    /**
     * Método que converte o modelo de dados da turma
     * para a sua entidade domínio.
     * @param data Modelo de dados da turma.
     * @return Entidade de domínio da turma.
     */
    public static StudentGroup toDomain(StudentGroupData data) {

        /* Se não houver nada no modelo de dados,
         * é retornado um null.
         */
        if (data == null) {
            
            return null;
        }

        /* Chama o construtor da entidade de domínio
         * da turma, colocando no valor do curso, ano 
         * e semestre, os mesmos que estão no modelo 
         * de dados.
         */
        var studentGroup = new StudentGroup(
            CourseMapper.toDomain(data.getCourse()), 
            data.getYear(), 
            data.getSemester()
        );

        /* Por estar fora do construtor, o ID é via Set. */
        studentGroup.setId(data.getId());
        
        return studentGroup;
    }

    public static StudentGroupData toData(StudentGroup domain) {

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
        var studentGroupData = new StudentGroupData();
        studentGroupData.setId(domain.getId());
        studentGroupData.setYear(domain.getYear());
        studentGroupData.setSemester(domain.getSemester());
        studentGroupData.setCourse(CourseMapper.toData(domain.getCourse()));
        
        return studentGroupData;
    }
}