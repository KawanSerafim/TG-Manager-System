package br.edu.com.tg.manager.core.domain.enums;

/**
 * Enumeração de domínio:
 * Representa os status das contas de usuário.
 * Por pertencer ao núcleo (core) da aplicação, este enum é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada um enum puro.
 */
public enum UserAccountStatus {
    PENDING_VERIFICATION,
    EMAIL_CONFIRMED,
    ACTIVE,
    INACTIVE
}