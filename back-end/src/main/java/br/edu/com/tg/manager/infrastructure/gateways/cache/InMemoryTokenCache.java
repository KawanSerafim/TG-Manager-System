package br.edu.com.tg.manager.infrastructure.gateways.cache;

import br.edu.com.tg.manager.core.ports.gateways.TokenCache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Implementador de portão de acesso:
 * Implementa o funcionamento do cache de tokens temporários.
 * Por pertencer à infraestrutura da aplicação, esta classe utiliza da anotação
 * Component do SpringBoot, permitindo que o framework gerencie a classe, e o
 * Spring Cache com a biblioteca Caffeine para armazenamento em memória.
 */
@Component
public class InMemoryTokenCache implements TokenCache {

    /*
     * Define o nome do cache que será usado nas anotações, e este nome será
     * referenciado na application.properties.
     */
    private static final String CACHE_NAME = "emailTokens";

    /**
     * {@inheritDoc}
     */
    @Override
    @CachePut(value = CACHE_NAME, key = "#key")
    public String putToken(String key, String token) {

        return token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "#key")
    public Optional<String> getToken(String key) {

        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(value = CACHE_NAME, key = "#key")
    public void removeToken(String key) {}
}