package com.seattleacademy.team20.firebase.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Service
public class FirebaseUploadService {

	private FirebaseApp app;

	public void initialize() throws IOException {
		File authFile = new File(getClass().getClassLoader().getResource("authentication.json").getFile());
		FileInputStream refreshToken = new FileInputStream(authFile);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.setDatabaseUrl("https://seattle-academy-demo.firebaseio.com/").build();
		app = FirebaseApp.initializeApp(options);
	}

	public void execute(Object value) {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final FirebaseDatabase database = FirebaseDatabase.getInstance(app);
		DatabaseReference ref = database.getReference("/");
		ref.setValue(value, new DatabaseReference.CompletionListener() {
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
