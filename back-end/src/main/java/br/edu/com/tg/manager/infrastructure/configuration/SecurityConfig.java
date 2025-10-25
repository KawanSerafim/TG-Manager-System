package br.edu.com.tg.manager.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe de configuração:
 * Define beans relacionados à segurança da aplicação.
 */
@Configuration
public class SecurityConfig {

    /**
     * Método de Infraestrutura:
     * Define o bean PasswordEncoder a ser usado na aplicação.
     * Utiliza o algoritmo BCrypt, que é o padrão recomendado para hashing de
     * senhas.
     * @return BCCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}