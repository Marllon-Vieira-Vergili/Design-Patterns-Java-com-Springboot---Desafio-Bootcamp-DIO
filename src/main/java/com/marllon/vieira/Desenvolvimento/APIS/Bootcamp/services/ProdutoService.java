package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.services;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.request.ProdutoRequest;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.response.ProdutoResponse;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.BaseDeDadosVaziaExc;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.JaExisteEsseDadoCriadoExc;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.ProdutoNaoLocalizadoExc;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/*MÈTODOS CRUD*/
public interface ProdutoService {



    /**
     * Método Criar Produto
     *
     * @return ProdutoResponse DTO
     * @param dadosProduto
     *
     * @throws JaExisteEsseDadoCriadoExc se ja detectar que existe um valor idêntico no banco de dados
     * analisando pelo nome do produto e seu valor
     *
     * */
    public ProdutoResponse criarProduto(ProdutoRequest dadosProduto);


    /**
     * Método Encontrar produto pela sua id
     *
     * @return ProdutoResponse DTO como Optional, para filtrar nullPointer
     * @param id  a ser informada do produto criado
     *
     * @throws ProdutoNaoLocalizadoExc se não for localizado o produto
     * */
    public Optional<ProdutoResponse> encontrarProdutoPelaId(Long id);


    /**
     * Método localizar todos os produtos e retornar em uma lista
     *
     * @return Lista ProdutoResponse DTO
     * @throws BaseDeDadosVaziaExc se não possuir e não for encontrado nenhum elemento na lista
     * */
    public List<ProdutoResponse> encontrarTodosOsProdutos();



    /**
     * Método para atualizar os dados de um determinado produto, passando como
     * @param id, novoNome e novoPreco
     *
     * @return Lista encadeada ProdutoResponse DTO
     * @throws ProdutoNaoLocalizadoExc se não for localizado o produto pela Id
     * */
    public ProdutoResponse atualizarDadosProdutoEspecifico(Long id, String novoNome, BigDecimal novoPreco);


    /**
     * Método para remover um determinado produto, passando sua
     * @param id informada
     *
     * @return nada, só executa se der certo
     * @throws ProdutoNaoLocalizadoExc se não for localizado o produto pela Id, e interromper o método
     * */
    public void removerProdutoPelaId(Long id);
}
