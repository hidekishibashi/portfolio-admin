package com.seattleacademy.team20.skill.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seattleacademy.team20.skill.model.Skill;
import com.seattleacademy.team20.skill.model.SkillCategory;

@Repository
public class SkillRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SkillCategory> selectSkillCategories() {
		final String sql = "select id, name from skill_categories";
		return jdbcTemplate.query(sql, new RowMapper<SkillCategory>() {
			public SkillCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new SkillCategory(rs.getInt("id"), rs.getString("name"));
			}
		});
	}

	public List<Skill> selectSkills() {
		final String sql = "select id, name, skill_category_id, score from skills";
		return jdbcTemplate.query(sql, new RowMapper<Skill>() {
			public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Skill(rs.getInt("id"), rs.getString("name"), rs.getInt("skill_category_id"),
						rs.getInt("score"));
			}
		});
	}
}
