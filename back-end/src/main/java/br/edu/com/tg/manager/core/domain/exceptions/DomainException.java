package br.edu.com.tg.manager.core.domain.exceptions;

/**
 * Exceção especializada de domínio:
 * Representa as violações de uma regra de negócio dentro do domínio
 * da aplicação.
 * Por pertencer ao núcleo (core) da aplicação, esta classe é independente de
 * frameworks ou bibliotecas externas, sendo, portanto, considerada uma classe
 * pura.
 */
public class DomainException extends RuntimeException {

    /**
     * Construtor de negócio:
     * Encaminha a mensagem de erro ao método do RuntimeException.
     * @param message Mensagem de erro.
     */
    public DomainException(String message) {

        super(message);
    }
}

/*
 * Padrões de texto de exceção:
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
 * - Diferente do tempo atual:
 *
 * [O 'tempo' não pode ser diferente do atual.]
 *
 * -------------------
 *
 * - Diferente do padrão:
 *
 * [O 'campo' foge do padrão: 'padrão'.]
 *
 * -------------------
 *
 * - Campo já cadastrado.
 *
 * [O 'campo' já foi cadastrado no sistema.]
 */