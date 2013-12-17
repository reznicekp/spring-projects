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
public class SearchControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		SearchController searchController = new SearchController();
		mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
	}
	
	@Test
	public void testShowSearch() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/search")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
}
