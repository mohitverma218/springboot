package com.sms.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sms.bean.Skill;
import com.sms.bean.Superhero;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	List<Skill> findBySuperhero(@Param("superhero") Superhero superhero);
	Skill findByNameAndSuperhero(@Param("name") String name,@Param("superhero") Superhero superhero);
}
