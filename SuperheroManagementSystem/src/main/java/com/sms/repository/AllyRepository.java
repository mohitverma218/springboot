package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sms.bean.Ally;
import com.sms.bean.Superhero;

@Repository
public interface AllyRepository extends JpaRepository<Ally, Long> {
	Ally findBySuperheroAndAlly(@Param("superhero") Superhero superhero,@Param("ally") Superhero ally);
}
