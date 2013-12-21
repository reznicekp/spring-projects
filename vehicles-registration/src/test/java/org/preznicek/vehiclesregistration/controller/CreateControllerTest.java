package org.preznicek.vehiclesregistration.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @see http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/testing.html
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class CreateControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		CreateController createController = new CreateController();
		mockMvc = MockMvcBuilders.standaloneSetup(createController).build();
	}
	
	@Test
	public void testShowCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/create-car")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		mockMvc.perform(MockMvcRequestBuilders.get("/create-motorcycle")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		mockMvc.perform(MockMvcRequestBuilders.get("/create-truck")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		mockMvc.perform(MockMvcRequestBuilders.get("/create-bus")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}
