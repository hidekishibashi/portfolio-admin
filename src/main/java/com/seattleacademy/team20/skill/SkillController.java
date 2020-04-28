package com.seattleacademy.team20.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seattleacademy.team20.skill.service.SkillService;

@RequestMapping("/skills")
@RestController
public class SkillController {

	@Autowired
	private SkillService service;

	@GetMapping(value = "/categories", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getSkillCategories() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(service.getSkillCategories());
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);
	}

	@GetMapping(produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getSkills() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(service.getSkills());
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/firebase-upload")
	public ResponseEntity<String> upload() {
		return new ResponseEntity<String>("Uploaded", HttpStatus.NO_CONTENT);
	}
}
