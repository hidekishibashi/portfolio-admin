package com.seattleacademy.team20.skill.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SkillCategory {

	@Setter
	@Getter
	private Integer id;

	@Setter
	@Getter
	private String name;
}
