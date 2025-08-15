package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.request;


import java.math.BigDecimal;

/**
 * Record Produto para receber os dados que o usuário irá inserir, chamado pela
 * API Rest Controller.
 *
 * Usuário passará como parâmetros o nome do Produto e o seu Preço
 *
 * */
public record ProdutoRequest(String nomeProduto, BigDecimal precoProduto) {
}
