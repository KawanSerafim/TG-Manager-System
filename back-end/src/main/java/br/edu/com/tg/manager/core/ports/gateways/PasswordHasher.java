package br.edu.com.tg.manager.core.ports.gateways;

/**
 * Portão de acesso de núcleo:
 * Define um contrato abstrato para a ação de criptografar (hash)
 * e verificar senhas.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é
 * independente de frameworks ou bibliotecas externas, sendo,
 * portanto, considerada uma classe pura.
 */
public interface PasswordHasher {
    /**
     * Método de contrato de núcleo:
     * Gera um hash seguro de uma senha em texto puro.
     * @param plainPassword Senha em texto puro.
     * @return Senha criptografada.
     */
    String hash(String plainPassword);

    /**
     * Método de contrato de núcleo:
     * Verifica se uma senha em texto puro corresponde a um hash.
     * @param plainPassword Senha em texto puro.
     * @param hashedPassword Senha criptografada.
     * @return True ou False.
     */
    boolean matches(String plainPassword, String hashedPassword);
}