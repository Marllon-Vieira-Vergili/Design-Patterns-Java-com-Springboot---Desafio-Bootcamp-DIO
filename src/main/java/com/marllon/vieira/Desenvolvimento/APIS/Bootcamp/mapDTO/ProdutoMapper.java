package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.mapDTO;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.DTO.response.ProdutoResponse;
import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.entity.Produto;
import org.springframework.stereotype.Component;



/**
 * Componente Mapper para os dados da entidade DTO.
 *
 * Funciona como um transportador de dados entre entidade, e serviços,
 * sem que a entidade fique diretamente exposta na classe de serviços.
 *
 *
 * @method retornarResponse passará os dados para API
 * */
@Component
public class ProdutoMapper {

    public ProdutoResponse retornarResponse(Produto entidade){
        return new ProdutoResponse(entidade.getId(),
                entidade.getNomeProduto(),entidade.getPreco());
    }
}
