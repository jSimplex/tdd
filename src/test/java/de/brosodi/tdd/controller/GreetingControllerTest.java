package de.brosodi.tdd.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.RecursiveComparisonAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.brosodi.tdd.data.Greeting;
import de.brosodi.tdd.exceptions.NotAcceptableException;
import de.brosodi.tdd.services.GreetingService;

@WebMvcTest
public class GreetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private GreetingService greetingServiceMock;
	
	@Test
	public void badRequestTest() throws Exception {
		when(greetingServiceMock.createGreeting(any(String.class))).thenThrow(new NotAcceptableException());
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/greeting").queryParam("name", "TRUMP"))
				.andExpect(status().isBadRequest());
	
	}
	
	@Test
	public void simpleTest() throws Exception {
		Greeting greeting = new Greeting(4, "example");
		when(greetingServiceMock.createGreeting(any(String.class))).thenReturn(greeting);
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
				.andExpect(status().is2xxSuccessful());
		verify(greetingServiceMock, times(1)).createGreeting("World");
		assertBody(result, greeting);
	}

	private void assertBody(ResultActions result, Object expected, String... ignoredFields) throws Exception {
		String body = result.andReturn().getResponse().getContentAsString();
		assertThat(objectMapper.readValue(body, expected.getClass()))
				.usingRecursiveComparison().ignoringFields(ignoredFields).isEqualTo(expected);

	}
}
