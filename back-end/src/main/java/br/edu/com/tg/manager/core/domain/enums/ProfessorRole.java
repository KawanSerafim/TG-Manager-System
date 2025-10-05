package br.edu.com.tg.manager.core.domain.enums;

/**
 * Enumeração de domínio:
 * Representa os cargos dos professores e determina a hierarquia de
 * permissões.
 * Por pertencer ao núcleo (core) da aplicação, este enum é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada um enum
 * puro.
 */
public enum ProfessorRole {

    // Orientador.
    ADVISOR,

    // Coordenador de TG.
    TG_COORDINATOR,

    // Coordenador de curso.
    COURSE_COORDINATOR;

    /**
     * Método de negócio:
     * Verifica se o professor tem permissões de orientador.
     * @return Verdadeiro.
     */
    public boolean hasAdvisorPermissions() {

        return true;
    }

    /**
     * Método de negócio:
     * Verifica se o professor tem permissões de coordenador de TG.
     * @return Verdadeiro ou Falso.
     */
    public boolean hasTgCoordinatorPermissions() {

        return this == TG_COORDINATOR;
    }

    /**
     * Método de negócio:
     * Verifica se o professor tem permissões de coordenador de curso.
     * @return Verdadeiro ou Falso.
     */
    public boolean hasCourseCoordinatorPermissions() {

        return this == COURSE_COORDINATOR;
    }
}