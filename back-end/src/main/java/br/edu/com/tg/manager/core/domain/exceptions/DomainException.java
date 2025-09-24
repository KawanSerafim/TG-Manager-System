package br.edu.com.tg.manager.core.domain.exceptions;

/**
 * Exceção especializada.
 * Lançada para indicar uma violação de uma regra de negócio dentro do domínio
 * da aplicação.
 * 
 * -------------------
 * 
 * Padrões de exceção:
 * 
 * -------------------
 * 
 * - Campo obrigatório:
 * 
 * [O campo 'nome do campo' é obrigatório.]
 * 
 * -------------------
 * 
 * - Formato inválido:
 * 
 * [O formato do campo 'nome do campo' é inválido.]
 * 
 * -------------------
 * 
 * - Relação obrigatória:
 * 
 * [O(A) 'Entidade A' deve estar associado(a) a um(a) 'Entidade B'.]
 * 
 * -------------------
 * 
 * - Não encontrado:
 *
 * [O(A) 'Entidade' com 'campo' = 'valor' não foi encontrado(a).]
 * 
 * -------------------
 * 
 * - Sem permissão:
 * 
 * [O(A) 'Entidade' não tem permissão de 'Nível ou Cargo'.]
 * 
 * -------------------
 * 
 * - Estado inválido:
 * 
 * [O(A) 'Entidade' não tem o estado válido para a ação.]
 * 
 * -------------------
 * 
 * - Tempo atual:
 * 
 * [O 'tempo' não pode ser diferente do atual.]
 */
public class DomainException extends RuntimeException {

    /**
     * Construtor que aceita a mensagem de erro.
     * @param message Mensagem detalhando a violação da regra de negócio.
     */
    public DomainException(String message) {

        super(message);
    }
}