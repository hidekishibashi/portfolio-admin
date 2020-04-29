package com.seattleacademy.team20.skill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seattleacademy.team20.firebase.service.FirebaseUploadService;
import com.seattleacademy.team20.skill.model.Skill;
import com.seattleacademy.team20.skill.model.SkillCategory;
import com.seattleacademy.team20.skill.repository.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository repository;

	@Autowired
	private FirebaseUploadService firebaseUploadService;

	public List<SkillCategory> getSkillCategories() {
		return repository.selectSkillCategories();
	}

	public List<Skill> getSkills() {
		return repository.selectSkills();
	}

	public void upload() {
		firebaseUploadService.uploadSkill(getSkillCategories(), getSkills());
	}
}
