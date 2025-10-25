package br.edu.com.tg.manager.application.listeners;

import br.edu.com.tg.manager.application.events.UserRequiresConfirmationEvent;
import br.edu.com.tg.manager.core.usecases.SendConfirmationEmailCase;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Ouvinte do Observer:
 * Representa uma reação ao evento que um usuário requer confirmação de email.
 */
@Component
public class ConfirmationEmailListener {

    private final SendConfirmationEmailCase useCase;

    public ConfirmationEmailListener(SendConfirmationEmailCase useCase) {

        this.useCase = useCase;
    }

    @EventListener
    public void handleUserRequiresConfirmation(

        UserRequiresConfirmationEvent event
    ) {

        useCase.execute(new SendConfirmationEmailCase.Input(event.email()));
    }
}