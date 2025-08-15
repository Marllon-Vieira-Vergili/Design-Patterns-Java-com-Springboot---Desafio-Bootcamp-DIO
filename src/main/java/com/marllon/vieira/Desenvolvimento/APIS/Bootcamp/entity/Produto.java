package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "produto")
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @Setter(AccessLevel.NONE) //Não instanciar o setter na ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_produto")
    @NotBlank(message = "Nome do Produto é requerido")
    @Size(min = 2, max = 50, message = "Nome do produto deve possuir pelo menos 2 caracteres e no máximo 50")
    private String nomeProduto;

    @Column(name = "preco_produto")
    @NotNull(message = "Preço do produto é requerido")
    @DecimalMin(value = "0,001", message = "O preço precisa ser maior do que zero.")
    private BigDecimal preco;
}

/**
 * Classe Entidade para criar a tabela com o nome "produto" no banco de dados
 * Utilizando Lombok para deixar o código mais limpo de entender, sem precisar
 * ficar instanciando construtor com parâmetro, sem parâmetro, getter, setter, toString..
 * usando tudo anotações
 *
 * e algumas validações já realizadas direto no atributo da entidade, para servir deu ma
 * "Primeia camada" contra falhas, ou valores inválidos.
 *
 *
 * **/