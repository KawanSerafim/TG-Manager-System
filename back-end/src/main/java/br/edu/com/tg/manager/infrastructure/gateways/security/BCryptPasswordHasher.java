package br.edu.com.tg.manager.infrastructure.gateways.security;

import br.edu.com.tg.manager.core.ports.gateways.PasswordHasher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordHasher implements PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hash(String plainPassword) {
        return this.passwordEncoder.encode(plainPassword);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(String plainPassword, String hashedPassword) {
        return this.passwordEncoder.matches(plainPassword, hashedPassword);
    }
}