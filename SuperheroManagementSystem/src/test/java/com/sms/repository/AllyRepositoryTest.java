package com.sms.repository;

import static org.assertj.core.api.Assertions.assertThat; 
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.sms.bean.Ally;
import com.sms.bean.Superhero;
import com.sms.bean.SuperheroPublisher;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AllyRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AllyRepository allyRepository;

	@Test
	public void testFindSuperheroByName() throws ParseException {
		Superhero superhero = new Superhero("Robert Bruce Banner", "Hulk", SuperheroPublisher.MARVEL, new SimpleDateFormat("yyyy-MM-dd").parse("1962-05-01"));
		entityManager.persist(superhero);
		entityManager.flush();

		Superhero ally = new Superhero("Anthony Stark", "Iron Man", SuperheroPublisher.MARVEL, new SimpleDateFormat("yyyy-MM-dd").parse("1963-03-01"));
		entityManager.persist(ally);
		entityManager.flush();

		Ally allies  = new Ally(superhero, ally);
		entityManager.persist(allies);
		entityManager.flush();

		Ally found = allyRepository.findBySuperheroAndAlly(superhero, ally);

		assertThat(found.getAlly().getName()).isEqualTo(ally.getName());
	}
}
