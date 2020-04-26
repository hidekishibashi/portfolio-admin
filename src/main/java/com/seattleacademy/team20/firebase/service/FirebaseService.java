package com.seattleacademy.team20.firebase.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private FirebaseUploadService uploadService;

	@SuppressWarnings("unchecked")
	public void upload() {
		// select skill info
		List<SkillInfo> selectSkillList = jdbcTemplate.query(
				"select c.name as category_name, s.name, s.score from skill_categories c inner join skills s on c.id = s.skill_category_id",
				new SkillRowMapper());
		// convert skill for firebase
		Map<String, List<Skill>> convertedSkillMap = selectSkillList.stream()
				.collect(Collectors.groupingBy(SkillInfo::getCategoryName)).entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey,
						s -> s.getValue().stream().map(m -> m.getSkill()).collect(Collectors.toList())));

		Map<String, Object> firebaseValue = new HashMap<>();
		List<Map<String, Object>> innerList = new ArrayList<>();
		for (Entry<String, List<Skill>> entry : convertedSkillMap.entrySet()) {
			Map<String, Object> innerValue = new HashMap<>();
			innerValue.put("category", entry.getKey());
			innerValue.put("skills", entry.getValue());
			innerList.add(innerValue);
		}

		firebaseValue.put("skill-categories", innerList);
		uploadService.execute(firebaseValue);
	}

	@SuppressWarnings({ "rawtypes" })
	public class SkillRowMapper implements RowMapper {
		public SkillInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Skill skill = new Skill(rs.getString("name"), rs.getInt("score"));
			SkillInfo skillInfo = new SkillInfo(rs.getString("category_name"), skill);
			return skillInfo;
		}
	}

	private class SkillInfo {
		private String categoryName;
		private Skill skill;

		private SkillInfo(String categoryName, Skill skill) {
			this.categoryName = categoryName;
			this.skill = skill;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public Skill getSkill() {
			return skill;
		}
	}

	public static class Skill {
		private String name;
		private Integer score;

		private Skill(String name, Integer score) {
			this.name = name;
			this.score = score;
		}

		public String getName() {
			return name;
		}

		public Integer getScore() {
			return score;
		}

		@Override
		public String toString() {
			return String.format("Skill name : %s, score : %s", getName(), getScore());
		}
	}
}
