/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest // Indica que a classe de teste usa o Spring Boot para carregar o contexto da aplicação.
@AutoConfigureMockMvc // Configura automaticamente o MockMvc, que será usado para simular requisições HTTP.
public class GreetingControllerTests { // Define a classe de teste para o controlador GreetingController.

	@Autowired // Injeta automaticamente a dependência do MockMvc configurado pelo Spring.
	private MockMvc mockMvc; // MockMvc é usado para simular requisições HTTP e validar respostas.

	@Test // Indica que este método é um teste e será executado ao rodar os testes.
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
		// Método que testa o endpoint /greeting sem parâmetros.

		this.mockMvc.perform(get("/greeting")) // Simula uma requisição HTTP GET no endpoint "/greeting".
				.andDo(print()) // Imprime os detalhes da requisição e resposta no console (útil para depuração).
				.andExpect(status().isOk()) // Verifica se o status HTTP da resposta é 200 OK.
				.andExpect(jsonPath("$.content").value("Hello, World!"));
		// Verifica se o campo "content" no JSON da resposta tem o valor "Hello, World!".
	}

	@Test // Outro método de teste.
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {
		// Testa o endpoint /greeting com o parâmetro "name".

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				// Simula uma requisição HTTP GET no endpoint "/greeting" com o parâmetro "name=Spring Community".
				.andDo(print()) // Imprime os detalhes da requisição e resposta no console.
				.andExpect(status().isOk()) // Verifica se o status HTTP da resposta é 200 OK.
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
		// Verifica se o campo "content" no JSON da resposta tem o valor "Hello, Spring Community!".
	}
}