package br.edu.com.tg.manager.core.domain.enums;

/**
 * Enumeração de domínio:
 * Representa os status das contas de usuário.
 * Por pertencer ao núcleo (core) da aplicação, este enum é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada um enum
 * puro.
 */
public enum UserAccountStatus {

    // Email está esperando ser confirmado.
    PENDING_VERIFICATION,

    // Email foi confirmado.
    EMAIL_CONFIRMED,

    // Conta está ativa.
    ACTIVE,

    // Conta está inativa.
    INACTIVE
}