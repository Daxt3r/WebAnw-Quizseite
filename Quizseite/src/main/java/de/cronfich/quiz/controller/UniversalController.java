package de.cronfich.quiz.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.cronfich.quiz.Highscore;
import de.cronfich.quiz.form.PlayerForm;
import de.cronfich.quiz.model.Player;

@Controller
public class UniversalController {
	
	private static List<Player> players = new ArrayList<Player>();
	
	@RequestMapping(path = "/")
	public String getWelcomePage() {

		return "index.html";
	}
	
	@RequestMapping(value = {"/quiz", "/quizseite"}, method = RequestMethod.GET)
	public String getQuizPage() {
		return "quizseite.html";
	}
	
	/**
	 * List alle Daten aus der rangliste.txt ein und speichert die Daten in list_player, welche auf der HTML-Seite highscore.html
	 * ausgegeben wird an.
	 * @param model
	 * @return Gibt das angeforderte html Dokument zurück
	 */
	@RequestMapping(value = {"/highscore"}, method = RequestMethod.GET)
	public String getHighscorePage(Model model) {
		try {
			players = Highscore.ReadRangliste();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("players", players);
		return "highscore.html";
	}
}


