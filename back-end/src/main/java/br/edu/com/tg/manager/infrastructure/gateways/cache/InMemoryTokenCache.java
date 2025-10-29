package br.edu.com.tg.manager.infrastructure.gateways.cache;

import br.edu.com.tg.manager.core.ports.gateways.TokenCache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class InMemoryTokenCache implements TokenCache {
    private static final String CACHE_NAME = "emailTokens";

    /**
     * {@inheritDoc}
     */
    @Override
    @CachePut(value = CACHE_NAME, key = "#token")
    public String putToken(String token, String email) {
        return email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "#token")
    public Optional<String> getEmailByToken(String token) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(value = CACHE_NAME, key = "#token")
    public void removeToken(String token) {}
}