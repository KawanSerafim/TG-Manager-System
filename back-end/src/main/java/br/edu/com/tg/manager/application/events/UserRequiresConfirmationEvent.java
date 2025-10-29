package br.edu.com.tg.manager.application.events;

/**
 * Porta-dados de aplicação:
 * Representa um evento disparado quando um novo usuário (Professor,
 * Aluno, etc.) é persistido e requer a confirmação do seu endereço
 * de email.
 * Esta classe é usada no padrão Observer (Publisher-Subscriber)
 * para desacoplar a lógica de criação da lógica de envio de email.
 */
public record UserRequiresConfirmationEvent(String email) {}