package com.sms;

import static org.hamcrest.CoreMatchers.is; 
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sms.SuperheroManagementSystemApplication;
import com.sms.bean.Superhero;
import com.sms.bean.SuperheroPublisher;
import com.sms.repository.SuperheroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes =SuperheroManagementSystemApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class SuperheroManagementSystemIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SuperheroRepository repository;

	Superhero superhero;

	@Before
	public void setUp() throws ParseException {
		superhero = new Superhero("Robert Bruce Banner", "Hulk", SuperheroPublisher.MARVEL,
				new SimpleDateFormat("yyyy-MM-dd").parse("1962-05-01"));

		repository.save(superhero);
	}

	@Test
	public void testGetAllSuperheroes()
			throws Exception {
		mvc.perform(get("/api/superheroes")
				.contentType(MediaType.APPLICATION_JSON)
				.with(httpBasic("sms", "power_access@218")))
		.andExpect(status().isOk())
		.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].pseudonym", is("Hulk")));
	}
}