package com.seattleacademy.team20.firebase;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seattleacademy.team20.firebase.service.FirebaseService;

@Controller
public class FirebaseController {

	@Autowired
	private FirebaseService firebaseService;

	@RequestMapping(value = "firebase/upload", method = RequestMethod.GET)
	public String upload(Locale locale, Model model) {

		firebaseService.upload();

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "firebase/upload";
	}
}
