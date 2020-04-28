package com.seattleacademy.team20.firebase.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seattleacademy.team20.skill.model.Skill;
import com.seattleacademy.team20.skill.model.SkillCategory;

@Service
public class FirebaseUploadService {

	private FirebaseApp app;

	public FirebaseUploadService() throws IOException {
		this.initialize();
	}

	public void initialize() throws IOException {
		File authFile = new File(getClass().getClassLoader().getResource("authentication.json").getFile());
		FileInputStream refreshToken = new FileInputStream(authFile);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setDatabaseUrl("https://seattle-academy-demo.firebaseio.com/").build();
		app = FirebaseApp.initializeApp(options);
	}

	public void execute(List<SkillCategory> categories, List<Skill> skills) {
		final FirebaseDatabase database = FirebaseDatabase.getInstance(app);
		DatabaseReference ref = database.getReference("/skill--categories");
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dataMap;
		for (SkillCategory category : categories) {
			dataMap = new HashMap<>();
			dataMap.put("category", category.getName());
			dataMap.put("skills", skills.stream().filter(s -> s.getSkillCategoryId().equals(category.getId()))
					.collect(Collectors.toList()));
			dataList.add(dataMap);
		}
		ref.setValue(dataList, new DatabaseReference.CompletionListener() {
			@Override
			public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
				if (databaseError != null) {
					System.out.println("Data could not be saved " + databaseError.getMessage());
				} else {
					System.out.println("Data saved successfully.");
				}
			}
		});
	}
}
