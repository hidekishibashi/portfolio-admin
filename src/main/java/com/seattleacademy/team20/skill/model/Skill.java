package com.seattleacademy.team20.skill.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Skill {

	@Setter
	@Getter
	private Integer id;

	@Setter
	@Getter
	private String name;

	@Setter
	@Getter
	private Integer skillCategoryId;

	@Setter
	@Getter
	private Integer score;
}
