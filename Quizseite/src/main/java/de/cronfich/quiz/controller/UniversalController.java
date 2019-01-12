package de.cronfich.quiz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.cronfich.quiz.Highscore;
import de.cronfich.quiz.form.PlayerForm;
import de.cronfich.quiz.model.Player;

@Controller
public class UniversalController {
	
	private static List<Player> players = new ArrayList<Player>();
	
	@Value("${error.message}")
	private String errorMessage;
	
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
	
	@RequestMapping(value = {"/quizende"}, method = RequestMethod.GET)
	public String getQuizendePage() {
		
		return "quizende.html";
	}
	
	/**
	 * Leitet zur Seite weiter, auf der ein Spieler seine Daten löschen kann.
	 * @return 
	 */
	@RequestMapping(value = {"/deletePlayer"}, method = RequestMethod.GET)
	public String showDeletePage(Model model) {
		PlayerForm playerForm = new PlayerForm();
		model.addAttribute("playerForm", playerForm);
		return "deletePlayer.html";
	}
	
	@RequestMapping(value = {"/deletePlayer"}, method = RequestMethod.DELETE)
	public String removePlayer(Model model, @ModelAttribute("playerForm") PlayerForm playerForm) {
		
		//Wenn die Liste noch nicht eingelesen wurde, wird dies nachgeholt
		if(players == null) {
			try {
				players = Highscore.ReadRangliste();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String sName = playerForm.getsName();
		String sMail = playerForm.getsMail();
		if(sName != null && sName.length() > 0 && sMail != null && sMail.length() > 0) {
			
			Iterator<Player> iter = players.iterator();
			while(iter.hasNext()) {
				Player player = iter.next();
				
				if(player.getsName().equals(sName) && player.getsMail().equals(sMail)) {
					
					int idx = players.indexOf(player); //Index des Spielers, der Gelöscht werden soll
					players.remove(idx); //Spieler wird gelöscht
					//rangliste.txt muss noch neu beschrieben werden
					return "highscore.html";
				}
			}
		
		}
		
		model.addAttribute("errorMessage", errorMessage);
		return "deletePlayer.html";
	}
	
	
	/**
	 * Zeigt das Ergebnis des Quizes an und der Benutzer hat die Möglichkeit sich in der
	 * Rangliste zu verewigen
	 * @param model
	 * @return Gibt das angeforderte html Dokument zurück
	 */
	@RequestMapping(value = { "/quizende" }, method = RequestMethod.POST)
	public String savePlayer(Model model, @ModelAttribute("playerForm") PlayerForm playerForm) {
		String sName = playerForm.getsName();
		String sMail = playerForm.getsMail();
		int nPktzahl = playerForm.getnPktZahl();
		
		if(sName != null && sName.length() > 0 && sMail != null && sMail.length() > 0) {
			
			Player newPlayer = new Player(0, sName, nPktzahl, sMail);
			try {
				Highscore.WritteRangliste(newPlayer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			players.add(newPlayer); //Neuer Spieler wird in die Liste hinzugefügt
			
			Highscore.sortList_setRang(players);
		    
			return "redirect:/highscore";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "quizende.html";
	}
}


