package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.bean.Superhero;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
	Superhero findByPseudonym(@Param("pseudonym") String pseudonym);
	Superhero findByName(@Param("name") String name);
}
