package br.edu.com.tg.manager.core.ports.gateways;

import java.util.Optional;

/**
 * Portão de acesso de domínio:
 * Define um contrato para a ação de armazenar e recuperar Tokens temporários.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma classe
 * pura.
 */
public interface TokenCache {

    /**
     * Método de contrato de domínio:
     * Armazena ou atualiza um token associado a uma chave ID, no cache.
     * @param key Chave ID.
     * @param token Token a ser armazenado.
     * @return Token armazenado.
     */
    String putToken(String key, String token);

    /**
     * Método de contrato de domínio:
     * Busca um token associado a uma chave ID, no cache.
     * @param key Chave ID.
     * @return Optional vazio ou Token.
     */
    Optional<String> getToken(String key);

    /**
     * Método de contrato de domínio:
     * Remove um token associado a uma chave ID, do cache.
     * @param key Chave ID.
     */
    void removeToken(String key);
}