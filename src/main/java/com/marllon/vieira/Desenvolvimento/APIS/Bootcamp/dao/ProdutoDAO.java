package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.dao;

import com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoDAO extends JpaRepository<Produto, Long> {

    /**
     * Interface dos repositórios CRUD passando a id que é do tipo Long
     * e a classe Produto, herdando métodos do JPA, vamos implementar na camada
     * Services
     *
     * */


}
