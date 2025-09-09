package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.StudentGroup;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentGroupData;

/**
 * Mapper responsável por converter a entidade de domínio
 * StudentGroup para o modelo de dados StudentGroupData,  
 * e vice-versa.
 */
public class StudentGroupMapper {

    /*
     * Construtor privado para que a classe não seja
     * instanciada, pois conterá apenas métodos estáticos.
     */
    private StudentGroupMapper(){}
    
    /**
     * Converte um modelo de dados (JPA) para uma entidade domínio.
     * @param data O objeto vindo do banco de dados.
     * @return A entidade de domínio pura.
     */
    public static StudentGroup toDomain(StudentGroupData data) {

        if(data == null) {

            return null;
        }

        var studentGroup = new StudentGroup(data.getCourseName());
        studentGroup.setId(data.getId());
        
        return studentGroup;
    }

    /**
     * Converte uma entidade de domínio para um modelo de dados (JPA).
     * @param domain A entidade de domínio.
     * @return O objeto de dados pronto para ser salvo no banco.
     */
    public static StudentGroupData toData(StudentGroup domain) {

        if(domain == null) {

            return null;
        }

        var studentGroupData = new StudentGroupData();
        studentGroupData.setId(domain.getId());
        studentGroupData.setCourseName(domain.getCourseName());
        
        return studentGroupData;
    }
}