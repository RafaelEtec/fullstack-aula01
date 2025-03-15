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

@SpringBootTest // Indica que esta é uma classe de teste de integração do Spring Boot
@AutoConfigureMockMvc // Configura automaticamente o MockMvc para simular requisições HTTP
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc; // Injeta uma instância do MockMvc para realizar requisições HTTP simuladas

	@Test // Indica que este é um método de teste JUnit
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")) // Simula uma requisição HTTP GET para "/greeting"
				.andDo(print()) // Imprime a requisição e a resposta no console para depuração
				.andExpect(status().isOk()) // Verifica se o status da resposta é 200 (OK)
				.andExpect(jsonPath("$.content").value("Hello, World!")); // Verifica se o JSON contém "Hello, World!" em "content"
	}

	@Test // Indica que este é um método de teste JUnit
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community")) // Simula GET para "/greeting" com parâmetro "name"
				.andDo(print()) // Imprime a requisição e a resposta no console
				.andExpect(status().isOk()) // Verifica se o status da resposta é 200 (OK)
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!")); // Verifica se o JSON contém "Hello, Spring Community!" em "content"
	}
}