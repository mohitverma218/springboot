package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;   
import org.springframework.web.bind.annotation.*;
import com.sms.model.SuperheroAllyCreateVO;
import com.sms.model.SuperheroCreateVO;
import com.sms.model.SuperheroDetailVO;
import com.sms.service.SuperheroManagementService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SuperheroManagementController {

	@Autowired
	SuperheroManagementService superheroManagementService;

	@GetMapping("/superheroes")
	public List<SuperheroDetailVO> getAllSuperheroes() {
		return superheroManagementService.retrieveSuperheroes();
	}

	@PostMapping("/superheroes")
	public SuperheroCreateVO createSuperhero(@Valid @RequestBody SuperheroCreateVO superheroCreateVO) {
		return superheroManagementService.createSuperhero(superheroCreateVO);
	}
	
	@PostMapping("/superheroes/allies")
	public SuperheroDetailVO createSuperheroAllies(@Valid @RequestBody SuperheroAllyCreateVO superheroAllyCreateVO) {
		return superheroManagementService.createSuperheroAllies(superheroAllyCreateVO);
	}

	@GetMapping("/superheroes/detail")
	public SuperheroDetailVO getSuperheroByName(@Valid @RequestParam(value = "name") String name) {
		return superheroManagementService.retrieveSuperheroes(name);
	}
}
