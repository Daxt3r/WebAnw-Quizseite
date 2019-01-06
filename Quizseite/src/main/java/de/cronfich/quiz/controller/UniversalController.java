package de.cronfich.quiz.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.cronfich.quiz.Highscore;
import de.cronfich.quiz.model.Player;

@Controller
public class UniversalController {
	
	private static List<Player> list_player = new ArrayList<Player>();
	
	@RequestMapping(path = "/")
	//@ResponseBody -> Gibt den return Wert als solchen zurück. Wird diese Annotation entfernt, wird auf die html Datei 
	//				   referenziert. Diese muss jedoch unter templates liegen. Ansonsten gibt es ein "Page not found" - Error 500
	public String getWelcomePage() {

		return "index.html";
	}
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String getQuizPage() {
		return "index.html";
	}
	
	@RequestMapping(value = {"/highscore"}, method = RequestMethod.GET)
	public String getHighscorePage(Model model)
	{
		try {
			Highscore.ReadJSON();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("list_player", list_player);
		return "highscore.html";
	}
}


