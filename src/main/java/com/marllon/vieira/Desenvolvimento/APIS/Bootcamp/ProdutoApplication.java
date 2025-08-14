package com.marllon.vieira.Desenvolvimento.APIS.Bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Classe main para inicializar a aplicação.
 *
 * Para acessar o banco de dados H2, abra o navegador, copie o link, conforme está as configurações
 no application.properties.
 *
 * http://localhost:8080/h2
 * Usuário: teste
 * senha: teste
 *
 *Para acessar o swagger UI e testar as apis
 *
 * http://localhost:8080/swagger-ui.html
 *
 * Ou Utilizar o Postman ou Imsomnia
 *
 * */

@SpringBootApplication
public class ProdutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);

		//Criando um @entity de produto só para testar se o banco sobe


	}

}
