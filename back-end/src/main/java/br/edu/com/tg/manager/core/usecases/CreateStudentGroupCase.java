package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import java.util.List;

/**
 * Caso de uso de domínio:
 * Define um contrato abstrato para a camada de aplicação implementar a
 * lógica de criar uma turma.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma
 * interface pura.
 */
public interface CreateStudentGroupCase {

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição.
     */
    record Input(

        String courseName,
        Discipline discipline,
        StudentDataReader.FileData fileData
    ) {

        public CourseShift shift() {

            return this.fileData.shift();
        }

        public Integer year() {

            return this.fileData.year();
        }

        public Integer semester() {

            return this.fileData.semester();
        }

        public List<StudentDataReader.StudentData> students() {

            return this.fileData.students();
        }
    }

    /**
     * Porta-dados de domínio:
     * Carrega os dados recebidos pela requisição que foram salvos.
     * @param studentGroup
     * @param students
     */
    record CreateStudentGroupResult (

        StudentGroup studentGroup,
        List<Student> students
    ) {

        public String courseName() {

            return this.studentGroup.getCourseName();
        }

        public CourseShift courseShift() {

            return this.studentGroup.getCourseShift();
        }

        public Discipline discipline() {

            return this.studentGroup.getDiscipline();
        }

        public Integer year() {

            return this.studentGroup.getYear();
        }

        public Integer semester() {

            return this.studentGroup.getSemester();
        }
    }

    /**
     * Método de contrato de domínio:
     * Executa a criação de uma nova turma, e a persiste no banco de dados.
     * @param input Porta-dados da requisição.
     * @return Lista de alunos.
     */
    CreateStudentGroupResult execute(Input input);
}