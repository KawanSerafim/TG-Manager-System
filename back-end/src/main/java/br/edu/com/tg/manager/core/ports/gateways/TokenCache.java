package br.edu.com.tg.manager.core.ports.gateways;

import java.util.Optional;

/**
 * Portão de acesso de domínio:
 * Define um contrato para a ação de armazenar e recuperar Tokens
 * temporários.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public interface TokenCache {
    /**
     * Método de contrato de domínio:
     * Armazena ou atualiza um email associado a um token, no cache.
     * O token é a chave.
     * @param token Token a ser armazenado.
     * @param email Email do usuário.
     * @return Email armazenado.
     */
    String putToken(String token, String email);

    /**
     * Método de contrato de domínio:
     * Busca um email associado a um token.
     * @param token Token fornecido pela requisição.
     * @return Optional vazio ou String (o email).
     */
    Optional<String> getEmailByToken(String token);

    /**
     * Método de contrato de domínio:
     * Remove o token fornecido.
     * @param token Token fornecido pela requisição.
     */
    void removeToken(String token);
}