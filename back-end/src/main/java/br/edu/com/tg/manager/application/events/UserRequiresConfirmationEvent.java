package br.edu.com.tg.manager.application.events;

/**
 * Anunciante do Observer:
 * Representa um evento em que um novo usuário está sendo criado e agora requer
 * a confirmação do seu endereço de email.
 * Este evento carrega o email do usuário para que os listeners possam agir.
 */
public record UserRequiresConfirmationEvent(String email) {}