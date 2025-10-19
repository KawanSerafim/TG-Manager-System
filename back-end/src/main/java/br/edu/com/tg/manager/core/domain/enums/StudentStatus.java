package br.edu.com.tg.manager.core.domain.enums;

/**
 * Enumeração de domínio:
 * Representa os status das contas dos alunos.
 * Por pertencer ao núcleo (core) da aplicação, este enum é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada um enum
 * puro.
 */
public enum StudentStatus {

    // Pré-Cadastro.
    PRE_REGISTRATION,

    // Verificação de email pendente.
    PENDING_VERIFICATION,

    // Email confirmado.
    EMAIL_CONFIRMED,

    // Coordenador de TG confirma acesso ao aluno.
    ACTIVE,

    // Conta inativo.
    INACTIVE
}