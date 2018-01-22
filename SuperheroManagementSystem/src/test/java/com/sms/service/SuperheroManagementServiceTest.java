package com.sms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sms.bean.Ally;
import com.sms.bean.Skill;
import com.sms.bean.Superhero;
import com.sms.bean.SuperheroPublisher;
import com.sms.model.SuperheroDetailVO;
import com.sms.repository.AllyRepository;
import com.sms.repository.SkillRepository;
import com.sms.repository.SuperheroRepository;

@RunWith(SpringRunner.class)
public class SuperheroManagementServiceTest {

	@TestConfiguration
	static class SuperheroManagementServiceTestContextConfiguration {

		@Bean
		public SuperheroManagementService superheroService() {
			return new SuperheroManagementService();
		}
	}

	@Autowired
	private SuperheroManagementService superheroManagementService;

	@MockBean
	private SuperheroRepository superheroRepository;
	@MockBean
	private AllyRepository allyRepository;
	@MockBean
	private SkillRepository skillRepository;

	@Before
	public void setUp() throws ParseException {
		Superhero superhero = new Superhero("Robert Bruce Banner", "Hulk", SuperheroPublisher.MARVEL, new SimpleDateFormat("yyyy-MM-dd").parse("1962-05-01"));
		Superhero ally = new Superhero("Anthony Stark", "Iron Man", SuperheroPublisher.MARVEL, new SimpleDateFormat("yyyy-MM-dd").parse("1963-03-01"));
		
		List<Superhero> superheroes = new ArrayList<Superhero>();
		superheroes.add(superhero);
		superheroes.add(ally);

		Ally allies  = new Ally(superhero, ally);
		
		Skill skill1 = new Skill("Superhuman Strength", superhero);
		Skill skill2 = new Skill("Cognitive Intelligence", superhero);
		
		List<Skill> skills = new ArrayList<Skill>();
		skills.add(skill1);
		skills.add(skill2);
		
		Mockito.when(superheroRepository.findByName(superhero.getName()))
		.thenReturn(superhero);
		Mockito.when(superheroRepository.findByPseudonym(superhero.getPseudonym()))
		.thenReturn(superhero);
		
		Mockito.when(superheroRepository.findByName(ally.getName()))
		.thenReturn(ally);
		Mockito.when(superheroRepository.findByPseudonym(ally.getPseudonym()))
		.thenReturn(ally);
		
		Mockito.when(superheroRepository.findAll())
		.thenReturn(superheroes);
		
		Mockito.when(allyRepository.findBySuperheroAndAlly(superhero,ally))
		.thenReturn(allies);
		
		Mockito.when(skillRepository.findByNameAndSuperhero(skill1.getName(),superhero))
		.thenReturn(skill1);
		Mockito.when(skillRepository.findByNameAndSuperhero(skill2.getName(),superhero))
		.thenReturn(skill2);
		
		Mockito.when(skillRepository.findByNameAndSuperhero(skill1.getName(),ally))
		.thenReturn(skill1);
		Mockito.when(skillRepository.findByNameAndSuperhero(skill2.getName(),ally))
		.thenReturn(skill2);
		
		Mockito.when(skillRepository.findBySuperhero(superhero))
		.thenReturn(skills);
		
	}

	@Test
	public void testRetrieveSuperheroesByName() {
		String name = "Hulk";
		SuperheroDetailVO superhero = superheroManagementService.retrieveSuperheroes(name);
		assertThat(superhero.getPseudonym()).isEqualTo(name);
	}
	
	@Test
	public void testRetrieveSuperheroes() {
		List<SuperheroDetailVO> superhero = superheroManagementService.retrieveSuperheroes();
		assertThat(superhero.size()).isEqualTo(2);
	}
}
