package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

public record CompleteRegistrationRequest(
        String registration,
        String email,
        String password
) {}