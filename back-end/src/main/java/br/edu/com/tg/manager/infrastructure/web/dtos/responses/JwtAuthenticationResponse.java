package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

public record JwtAuthenticationResponse(
        String token,
        String tokenType
) {}