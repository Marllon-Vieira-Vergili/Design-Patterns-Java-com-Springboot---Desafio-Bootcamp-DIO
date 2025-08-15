package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.services;

import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.request.ProdutoRequest;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.response.ProdutoResponse;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.dao.ProdutoDAO;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.entity.Produto;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.BaseDeDadosVaziaExc;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.JaExisteEsseDadoCriadoExc;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.exception.ProdutoNaoLocalizadoExc;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.mapDTO.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoServiceImpl implements ProdutoService{


    //Injetando o JPA Repository da entidade
    @Autowired
    private ProdutoDAO produtoDAO;

    //Injetando o mapper para retornar os valores já filrados na api para usuário ver
    @Autowired
    private ProdutoMapper mapper;

    @Override
    public ProdutoResponse criarProduto(ProdutoRequest dadosProduto) {

        //Verificando se já não possui produto igual na base de dados, usando anyMatch
        boolean produtoJaExiste = encontrarTodosOsProdutos().stream().anyMatch(produtoResponse ->
                produtoResponse.precoProduto().equals(dadosProduto.precoProduto()) && produtoResponse.nomeProduto()
                        .equalsIgnoreCase(dadosProduto.nomeProduto()));

        if (produtoJaExiste == true){
            throw new JaExisteEsseDadoCriadoExc("Não foi possível criar esse produto com o nome: "
                    + dadosProduto.nomeProduto() + ", nem com esse Preço" + dadosProduto.precoProduto() + " Por que " +
                    "ele já existe um igualzinho criado no banco de dados");
        }

        //Senão... criar o produto novo
        Produto novoProduto = new Produto();
        novoProduto.setNomeProduto(dadosProduto.nomeProduto());
        novoProduto.setPreco(dadosProduto.precoProduto());

        //Persistir no banco os dados
        produtoDAO.save(novoProduto);

        //Retornar na api via mapper
        return mapper.retornarResponse(novoProduto);
    }

    @Override
    public Optional<ProdutoResponse> encontrarProdutoPelaId(Long id) {

        Produto produtoLocalizadoPelaId = produtoDAO.findById(id).orElseThrow(() ->
                new ProdutoNaoLocalizadoExc("Produto não foi localizado na base de dados com essa ID"));

        //Senão, retornar o produtoLocalidadoPelaId via mapper
        return Optional.ofNullable(mapper.retornarResponse(produtoLocalizadoPelaId));
    }

    @Override
    public List<ProdutoResponse> encontrarTodosOsProdutos() {

        if (produtoDAO.findAll().isEmpty()){
            throw new BaseDeDadosVaziaExc("Não foi localizado nenhum elemento na base de dados");
        }

        /*
        * Retornar a lista, usando a interface JPA com o método find all, mapeando todos os produtos encontrados
        * tal que cada produto vai receber um novo ProdutoResponse, que receberá os métodos como sua id,
        * seu nome, seu preço, coletando todos eles para uma lista.
        * */
        return produtoDAO.findAll().stream().map(produto -> new ProdutoResponse(produto.getId(),
                produto.getNomeProduto(), produto.getPreco())).collect(Collectors.toList());


    }

    @Override
    public ProdutoResponse atualizarDadosProdutoEspecifico(Long id, String novoNome, BigDecimal novoPreco) {

        //Localizar o produto pela ID, usando o Optional para Nullable
        Optional<Produto> produtoLocalizado = Optional.ofNullable(produtoDAO.findById(id).orElseThrow(() -> new ProdutoNaoLocalizadoExc(
                "Produto não localizado com essa id para ser removido"
        )));

        //Se valor estiver presente, ajustar os valores, conforme passado nos parâmetros
        if (produtoLocalizado.isPresent()){
            produtoLocalizado.get().setNomeProduto(novoNome);
            produtoLocalizado.get().setPreco(novoPreco);
        }

        //Converter o Optional para classe Produto
        Produto produtoAtualizado = produtoLocalizado.get();

        //Persistindo no banco essa alteração
                produtoDAO.save(produtoAtualizado);

                //Retornar via mapper o valor atualizado
        return mapper.retornarResponse(produtoAtualizado);
    }

    @Override
    public void removerProdutoPelaId(Long id) {

        Produto produtoLocalizado = produtoDAO.findById(id).orElseThrow(()-> new ProdutoNaoLocalizadoExc(
                "Produto não localizado com essa id para ser removido"
        ));

        //Se localizado, remover o produto
        produtoDAO.delete(produtoLocalizado);
    }
}
