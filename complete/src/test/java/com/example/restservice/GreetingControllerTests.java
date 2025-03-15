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

@SpringBootTest // Anotação que indica que este é um teste de integração do Spring Boot. Carrega o contexto completo da aplicação.
@AutoConfigureMockMvc // Configura automaticamente o MockMvc para testes de controladores sem precisar de um servidor HTTP real.
public class GreetingControllerTests {

	@Autowired // Injeta automaticamente uma instância de MockMvc para simular requisições HTTP.
	private MockMvc mockMvc;

	@Test // Anotação que indica que este método é um teste.
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
		// Simula uma requisição GET para o endpoint "/greeting".
		this.mockMvc.perform(get("/greeting"))
				.andDo(print()) // Imprime detalhes da requisição e resposta no console (útil para depuração).
				.andExpect(status().isOk()) // Verifica se o status da resposta é HTTP 200 (OK).
				.andExpect(jsonPath("$.content").value("Hello, World!")); // Verifica se o campo "content" no JSON da resposta contém "Hello, World!".
	}

	@Test // Anotação que indica que este método é um teste.
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {
		// Simula uma requisição GET para o endpoint "/greeting" com o parâmetro "name" definido como "Spring Community".
		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()) // Imprime detalhes da requisição e resposta no console.
				.andExpect(status().isOk()) // Verifica se o status da resposta é HTTP 200 (OK).
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!")); // Verifica se o campo "content" no JSON da resposta contém "Hello, Spring Community!".
	}
}