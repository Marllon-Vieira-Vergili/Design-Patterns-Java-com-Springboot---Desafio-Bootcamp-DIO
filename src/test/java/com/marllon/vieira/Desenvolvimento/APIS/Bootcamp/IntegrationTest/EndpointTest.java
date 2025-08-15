package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp.IntegrationTest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * ==================================================
 * TAREFA: TERMINAR DE IMPLEMENTAR OS TESTES DOS OUTROS ENDPOINTS
 * =============================================================
 *
 * **/


/**
 * Camada de teste dos APIS do endpoint usando teste de integração com mock
 * */
@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {

    //Injetando o mock (Model view controller) para endpoints
    @Autowired
    private MockMvc mockMvc;


    /**
     * Classe aninhada para testar cenários de sucesso dos endpoints
     */
    @Nested
    @Order(1)
    public class CenariosSucessoEndpoints {

        @Test
        void adicionarProdutosDeveRetornarOk() throws Exception {

            mockMvc.perform(post("/adicionarProduto").
                            contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                    "nomeProduto": "Notebook",
                                    "precoProduto": 2300
                                    }
                                    """)).andExpect(status().isOk()
                    ).andExpect(jsonPath("$.nomeProduto").value("Notebook"))
                    .andExpect(jsonPath("$.precoProduto").value(2300));
        }


        @Test
        public void encontrarPelaIdDeveRetornarOk() throws Exception {

            //Criando um valor pra mock em banco de dados
            String response = mockMvc.perform(post("/adicionarProduto")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "nomeProduto": "Notebook",
                                        "precoProduto": 2300
                                    }
                                    """))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();


            //Agora tentando buscar esse produto criado, pela id dele que será 1L(Long)
            mockMvc.perform(get("/localizarProduto/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.nomeProduto").value("Notebook"))
                    .andExpect(jsonPath("$.precoProduto").value(2300));
        }


    }


    /**
     * Classe aninhada para testar cenários de erros dos endpoints
     */
    @Nested
    @Order(2)
    public class CenariosErrosEExceptionsEndpoints {


        @Test
        void metodoAdicionarProdutoDeveRetornar302() throws Exception {

            //CORRIGIR! Comentei por que estava dando conflito com os metodos do primeiro nested.

            //Exemplo de lógica que poderia ser funcional
            /*
            //Criando a primeira vez
            mockMvc.perform(post("/adicionarProduto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "nomeProduto": "Televisao",
                            "precoProduto": 3000
                            }
                            """)).andExpect(status().isOk());

            //Se tentar criar denovo, ele deve informar que já existe
            // e retornar o status 302(found)
            mockMvc.perform(post("/adicionarProduto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                            {
                            "nomeProduto": "Televisao",
                            "precoProduto": 3000
                            }
                            """)).andExpect(status().isFound());

        }

             */
        }

    }
}
