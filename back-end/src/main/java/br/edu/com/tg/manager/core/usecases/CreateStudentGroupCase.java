package br.edu.com.tg.manager.core.usecases;

import br.edu.com.tg.manager.core.domain.entities.Student;
import br.edu.com.tg.manager.core.domain.entities.StudentGroup;
import br.edu.com.tg.manager.core.domain.enums.CourseShift;
import br.edu.com.tg.manager.core.domain.enums.Discipline;
import br.edu.com.tg.manager.core.ports.gateways.StudentDataReader;
import java.util.List;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de cadastrar uma turma.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface CreateStudentGroupCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param courseName Nome do curso.
     * @param discipline Disciplina do curso.
     * @param fileData Porta-dados dos metadados da turma.
     * @param executorEmail Email do executor da ação.
     */
    record Input(
            String courseName,
            Discipline discipline,
            StudentDataReader.FileData fileData,
            String executorEmail
    ) {
        /**
         * Método Get (DELEGAÇÃO).
         * @return Turno do curso.
         */
        public CourseShift shift() {
            return this.fileData.shift();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Ano da turma.
         */
        public Integer year() {
            return this.fileData.year();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Semestre da turma.
         */
        public Integer semester() {
            return this.fileData.semester();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Lista de alunos.
         */
        public List<StudentDataReader.StudentData> students() {
            return this.fileData.students();
        }
    }

    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados de informações dos coordenadores do curso.
     * @param studentGroup Turma.
     * @param students Lista de alunos.
     */
    record Output(
            StudentGroup studentGroup,
            List<Student> students
    ) {
        /**
         * Método Get (DELEGAÇÃO).
         * @return Nome do curso.
         */
        public String courseName() {
            return this.studentGroup.getCourseName();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Turno do curso.
         */
        public CourseShift courseShift() {
            return this.studentGroup.getCourseShift();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Disciplina da turma.
         */
        public Discipline discipline() {
            return this.studentGroup.getDiscipline();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Ano da turma.
         */
        public Integer year() {
            return this.studentGroup.getYear();
        }

        /**
         * Método Get (DELEGAÇÃO).
         * @return Semestre da turma.
         */
        public Integer semester() {
            return this.studentGroup.getSemester();
        }
    }

    /**
     * Método de contrato de núcleo:
     * Executa o cadastro de uma turma no sistema.
     * @param input Porta-dados da requisição POST.
     * @return Output.
     */
    Output execute(Input input);
}