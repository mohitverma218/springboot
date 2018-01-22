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

import com.sms.bean.Superhero;
import com.sms.bean.SuperheroPublisher;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SuperheroRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private SuperheroRepository superheroRepository;
 
    @Test
    public void testFindSuperheroByName() throws ParseException {
        Superhero superhero = new Superhero("Robert Bruce Banner", "Hulk", SuperheroPublisher.MARVEL, new SimpleDateFormat("yyyy-MM-dd").parse("1962-05-01"));
        entityManager.persist(superhero);
        entityManager.flush();
     
        Superhero found = superheroRepository.findByName(superhero.getName());

        assertThat(found.getName()).isEqualTo(superhero.getName());
    }
}
