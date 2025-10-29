package br.edu.com.tg.manager.core.usecases;

/**
 * Caso de uso de núcleo:
 * Define um contrato abstrato para a camada de aplicação
 * implementar a lógica de finalizar cadastro de aluno.
 * Por pertencer ao núcleo (core) da aplicação, esta interface é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma interface pura.
 */
public interface CompleteStudentRegistrationCase {
    /**
     * Porta-dados de contrato de núcleo:
     * Carrega os dados enviados pela requisição POST.
     * @param registration Matrícula do aluno.
     * @param email Email do aluno.
     * @param password Senha do aluno.
     */
    record Input(String registration, String email, String password) {}

    /**
     * Método de contrato de núcleo:
     * Executa a finalização do cadastro do aluno.
     * @param input Porta-dados da requisição POST.
     */
    void execute(Input input);
}