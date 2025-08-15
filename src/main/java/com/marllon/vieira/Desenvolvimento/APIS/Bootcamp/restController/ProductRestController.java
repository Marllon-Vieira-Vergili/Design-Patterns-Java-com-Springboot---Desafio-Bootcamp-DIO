package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.restController;

import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.request.ProdutoRequest;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.response.ProdutoResponse;

import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Criando os endpoints de Requisição via Get, Post, Put e Delete
 * da entidade Produto
 *
 * */
@RestController(value = "produto")
public class ProductRestController {

    //Injetando a interface
    @Autowired
    private ProdutoService produtoService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> encontrarProdutoPelaId(@PathVariable Long id){

        Optional<ProdutoResponse> produtoLocalizado = produtoService.encontrarProdutoPelaId(id);

        return ResponseEntity.ok(produtoLocalizado.get());
    }

    @GetMapping(value = "/todosProdutos")
    public ResponseEntity<List<ProdutoResponse>> encontrarTodosOsProdutos(){

        //Instanciando a lista de produtos, usando o método do produtoService
        List<ProdutoResponse> listaDeProdutos = (produtoService.encontrarTodosOsProdutos());

        //Se estiver vazia, retornar noContent no api, e disparará a exceção na lógica do services
        if (listaDeProdutos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        //Se achar, status 200 e mostrar a lista de produtos
        return ResponseEntity.ok(listaDeProdutos);
    }


    @PostMapping(value = "/adicionarProduto")
    public ResponseEntity<ProdutoResponse> adicionarNovoProduto(@RequestBody ProdutoRequest dadosNovoProduto){

        //Instanciando o produto response
        ProdutoResponse produtoResponse = produtoService.criarProduto(dadosNovoProduto);

        //Verificando se ele foi criado mesmo, tentando encontrar todos os produtos
        List<ProdutoResponse> foiCriado = produtoService.encontrarTodosOsProdutos();

        //Criando uma variável booleana que vai fazer esse fluxo no banco e ver se encontra os valores, e
        //se achou ele mesmo
        boolean b = foiCriado.stream().anyMatch(produtoResponse1 -> produtoResponse1.id() != 0
                && Objects.equals(produtoResponse1.nomeProduto(), dadosNovoProduto.nomeProduto()) && produtoResponse1.precoProduto()
                .equals(dadosNovoProduto.precoProduto()));

        //Se achou, retorna ele pelo response entity
        if (b){
            return ResponseEntity.ok(produtoResponse);
            //Senão, retorna bad request(400)
        }else{
            return ResponseEntity.badRequest().build();
        }


    }

    @PutMapping(value = "/alterarProduto/{id}")
    public ResponseEntity<ProdutoResponse> alterarProduto(@PathVariable Long id,
                                                          @RequestParam String novoNome,
                                                          @RequestParam BigDecimal novoPreco) throws IllegalArgumentException {

        //Realizar as atualizações dos dados
        ProdutoResponse response = produtoService.atualizarDadosProdutoEspecifico(id,novoNome,novoPreco);

        //Se der certo, retornar a resposta em json para a pessoa
        if (response.id().equals(id) && response.nomeProduto().equalsIgnoreCase(novoNome) && response.precoProduto().equals(novoPreco)){

            return ResponseEntity.ok(response);
        }
        //Senão.. retornar bad request (erro 400)
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/removerProduto/{id}")
    public ResponseEntity <Void> removerProduto(@PathVariable Long id){
        produtoService.removerProdutoPelaId(id);
        return ResponseEntity.noContent().build(); //Retornar o código de no-content quando for deletado
    }
}
