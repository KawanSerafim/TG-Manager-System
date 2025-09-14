package br.edu.com.tg.manager.infrastructure.persistence.mapper;

import br.edu.com.tg.manager.core.entity.Student;
import br.edu.com.tg.manager.infrastructure.persistence.model.StudentData;

/**
 * Mapa que linka a entidade de domínio do aluno com o seu modelo de dados,
 * formando uma ponte lógica entre a camada core e a de persistence.
 */
public class StudentMapper {

    /* Um cadeado, evitando que a classe seja instanciada, pois ela
     * é puramente de serviço.
     */
    private StudentMapper(){}

    /**
     * Método que converte o modelo de dados do aluno para a
     * sua entidade domínio.
     * @param data Modelo de dados do aluno.
     * @return Entidade de domínio do aluno.
     */
    public static Student toDomain(StudentData data) {

        /* Se não houver nada no modelo de dados, é retornado um null.*/
        if(data == null) {

            return null;
        }

        /* Chama o construtor da entidade de domínio do aluno, colocando no
         * valor do nome, matrícula (RA), email, senha e turma, os 
         * mesmos que estão no modelo de dados.
         */
        var student = new Student(
            data.getName(),
            data.getRegistration(),
            data.getEmail(),
            data.getPassword(),
            StudentGroupMapper.toDomain(data.getStudentGroup())
        );

        /* Por estar fora do construtor, o ID é via Set. */
        student.setId(data.getId());

        return student;
    }

    public static StudentData toData(Student domain) {

        /* Se não houver nada na entidade de domínio, é retornado um null. */
        if(domain == null) {

            return null;
        }

        /* Como o construtor no modelo de dados é vazio, os valores são
         * inseridos apenas via métodos sets.
         */
        var studentData = new StudentData();
        studentData.setId(domain.getId());
        studentData.setName(domain.getName());
        studentData.setRegistration(domain.getRegistration());
        studentData.setEmail(domain.getEmail());
        studentData.setPassword(domain.getPassword());

        studentData.setStudentGroup(StudentGroupMapper
        .toData(domain.getStudentGroup()));
    
        return studentData;
    }
}