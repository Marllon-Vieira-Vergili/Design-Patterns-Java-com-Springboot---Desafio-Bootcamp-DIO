package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.response;


import java.math.BigDecimal;

/**
 * Record Produto para retornar o determinado tipo de JSON personalizado para o usuário, chamado pela
 * API Rest Controller
 *
 *
 * Usuário receberá como valor a id do produto, o nome do produto e o preço
 *
 * */
public record ProdutoResponse(Long id, String nomeProduto, BigDecimal precoProduto) {
}
