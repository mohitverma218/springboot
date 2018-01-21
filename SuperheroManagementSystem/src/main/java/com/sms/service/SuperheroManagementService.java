package com.sms.service;

import java.util.ArrayList; 
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sms.bean.Ally;
import com.sms.bean.Skill;
import com.sms.bean.Superhero;
import com.sms.bean.SuperheroPublisher;
import com.sms.exception.SuperheroAlreadyExistException;
import com.sms.exception.SuperheroNotFoundException;
import com.sms.model.SuperheroAllyCreateVO;
import com.sms.model.SuperheroAllyDetailVO;
import com.sms.model.SuperheroCreateVO;
import com.sms.model.SuperheroDetailVO;
import com.sms.repository.AllyRepository;
import com.sms.repository.SkillRepository;
import com.sms.repository.SuperheroRepository;

@Component
public class SuperheroManagementService {

	@Autowired
	SuperheroRepository superheroRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	AllyRepository allyRepository;

	public SuperheroCreateVO createSuperhero(SuperheroCreateVO superheroCreateVO) {

		String name = superheroCreateVO.getName();
		String pseudonym = superheroCreateVO.getPseudonym();
		SuperheroPublisher publisher = superheroCreateVO.getPublisher();
		Date appearanceOn = superheroCreateVO.getFirstAppearanceOn();

		Superhero databaseRecord = superheroRepository.findByPseudonym(pseudonym);
		if(databaseRecord!=null) {
			String message = "Already exist a superhero record with the pseudonym : "+pseudonym;
			throw new SuperheroAlreadyExistException(message);
		}

		Superhero bean = new Superhero();
		bean.setName(name);
		bean.setPseudonym(pseudonym);
		bean.setPublisher(publisher);
		bean.setFirstAppearanceOn(appearanceOn);

		Superhero savedBean = superheroRepository.saveAndFlush(bean);

		List<String> skills = superheroCreateVO.getSkills();
		List<Skill> skillBeans = new ArrayList<Skill>();
		if(skills!=null&&!skills.isEmpty()) {
			for(String skillName : skills) {
				Skill skill = skillRepository.findByNameAndSuperhero(skillName, savedBean);
				if(skill == null) {
					skill = new Skill();
					skill.setName(skillName);
					skill.setSuperhero(savedBean);
				}
				skillBeans.add(skill);
			}
			skillRepository.save(skillBeans);
			skillRepository.flush();
		}

		return makeSuperheroCreateVO(savedBean, skillBeans);
	}
	
	public SuperheroDetailVO createSuperheroAllies(SuperheroAllyCreateVO superheroAllyCreateVO) {

		String name = superheroAllyCreateVO.getName();
		Superhero superhero = validateAndGetSuperheroRecord(name);
		
		List<Ally> allies = new ArrayList<Ally>();
		
		for(String allyName : superheroAllyCreateVO.getAllies()) {
			Superhero allySuperhero = validateAndGetSuperheroRecord(allyName);
			
			Ally  ally = allyRepository.findBySuperheroAndAlly(superhero, allySuperhero);
			if(ally == null) {
				ally = new Ally();
				ally.setSuperhero(superhero);
				ally.setAlly(allySuperhero);
			}
			allies.add(ally);
			
			Ally  allyReverse = allyRepository.findBySuperheroAndAlly(allySuperhero, superhero);
			if(allyReverse == null) {
				allyReverse = new Ally();
				allyReverse.setSuperhero(allySuperhero);
				allyReverse.setAlly(superhero);
			}
			allies.add(allyReverse);
		}

		allyRepository.save(allies);
		allyRepository.flush();

		return makeSuperheroDetailVO(validateAndGetSuperheroRecord(name));
	}

	public List<SuperheroDetailVO> retrieveSuperheroes() {
		List<Superhero> superheroes = superheroRepository.findAll();
		if(superheroes == null||superheroes.isEmpty()) {
			String message = "No superhero records found";
			throw new SuperheroNotFoundException(message);
		}

		List<SuperheroDetailVO> superheroDetailVOs = new ArrayList<SuperheroDetailVO>();
		for(Superhero superhero : superheroes) {
			SuperheroDetailVO detailVO = makeSuperheroDetailVO(superhero);
			superheroDetailVOs.add(detailVO);
		}

		return superheroDetailVOs;
	}
	
	public SuperheroDetailVO retrieveSuperheroes(String name) {
		Superhero superhero = validateAndGetSuperheroRecord(name);
		return makeSuperheroDetailVO(superhero);
	}
	
	private Superhero validateAndGetSuperheroRecord(String name) {
		Superhero superhero = superheroRepository.findByPseudonym(name);
		if(superhero == null) {
			superhero  = superheroRepository.findByName(name);
			if(superhero==null) {
				String message = "No superhero record found with the name/pseudonym : "+name;
				throw new SuperheroNotFoundException(message);
			}
		}
		return superhero;
	}

	private SuperheroDetailVO makeSuperheroDetailVO(Superhero superhero) {
		SuperheroDetailVO superheroDetailVO = new SuperheroDetailVO();

		superheroDetailVO.setName(superhero.getName());
		superheroDetailVO.setPseudonym(superhero.getPseudonym());
		superheroDetailVO.setPublisher(superhero.getPublisher());
		superheroDetailVO.setFirstAppearanceOn(superhero.getFirstAppearanceOn());

		List<String> skills = new ArrayList<String>();
		if(superhero.getSkills()!=null&&!superhero.getSkills().isEmpty()) {
			for(Skill skill : superhero.getSkills()) {
				skills.add(skill.getName());
			}
		}
		superheroDetailVO.setSkills(skills);
		
		List<SuperheroAllyDetailVO> allies = new ArrayList<SuperheroAllyDetailVO>();
		if(superhero.getAllies()!=null&&!superhero.getAllies().isEmpty()) {
			for(Ally ally : superhero.getAllies()) {
				SuperheroAllyDetailVO allyDetailVO = new SuperheroAllyDetailVO();
				allyDetailVO.setName(ally.getAlly().getName());
				allyDetailVO.setPseudonym(ally.getAlly().getPseudonym());
				allies.add(allyDetailVO);
			}
		}
		superheroDetailVO.setAllies(allies);
		return superheroDetailVO;
	}

	private SuperheroCreateVO makeSuperheroCreateVO(Superhero superhero,List<Skill> skillBeans) {
		SuperheroCreateVO superheroCreateVO = new SuperheroCreateVO();

		superheroCreateVO.setName(superhero.getName());
		superheroCreateVO.setPseudonym(superhero.getPseudonym());
		superheroCreateVO.setPublisher(superhero.getPublisher());
		superheroCreateVO.setFirstAppearanceOn(superhero.getFirstAppearanceOn());

		List<String> skills = new ArrayList<String>();
		if(skillBeans!=null&&!skillBeans.isEmpty()) {
			for(Skill skill : skillBeans) {
				skills.add(skill.getName());
			}
		}
		superheroCreateVO.setSkills(skills);
		return superheroCreateVO;
	}
}
