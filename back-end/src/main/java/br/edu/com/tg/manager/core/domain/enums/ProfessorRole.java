package br.edu.com.tg.manager.core.domain.enums;

/**
 * Enumeração de domínio.
 * Representa os cargos dos professores, também determinando hierarquia de
 * permissões.
 */
public enum ProfessorRole {

    ADVISOR,
    TG_COORDINATOR,
    COURSE_COORDINATOR;

    /**
     * Verifica se o professor tem permissões de orientador.
     * Como todos possuem, tem apenas um tipo de retorno.
     * @return Verdadeiro.
     */
    public boolean hasAdvisorPermissions() {

        return true;
    }

    /**
     * Verifica se o professor tem permissões de coordenador de TG.
     * Vai pegar o cargo do professor (this) e vai comparar (==) com o cargo
     * determinado (TG_COORDINATOR).
     * @return Verdadeiro ou Falso.
     */
    public boolean hasTgCoordinatorPermissions() {

        return this == TG_COORDINATOR;
    }

    /**
     * Verifica se o professor tem permissões de coordenador de curso.
     * Vai pegar o cargo do professor (this) e vai comparar (==) com o cargo
     * determinado (COURSE_COORDINATOR).
     * @return Verdadeiro ou Falso.
     */
    public boolean hasCourseCoordinatorPermissions() {

        return this == COURSE_COORDINATOR;
    }
}