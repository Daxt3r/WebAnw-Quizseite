package de.cronfich.quiz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.cronfich.quiz.Highscore;
import de.cronfich.quiz.Quiz;
import de.cronfich.quiz.form.PlayerForm;
import de.cronfich.quiz.model.Player;
import de.cronfich.quiz.model.Question;

@Controller
public class UniversalController {
	
	private static List<Player> players = new ArrayList<Player>();
	private static HashMap<Integer, Question> questions = new HashMap<Integer, Question>();
	
	private static int nFragen_Counter = 1;
	private int nPunktePlayer = 0;
	private static boolean bQuizStarted = false;
	
	@Value("${error.message}")
	private String errorMessage;
	
	@Value("${error.message.PlayerNotFound}")
	private String errorMessagePlayerNotFound;
	
	@Value("${error.message.PlayerNameRequired}")
	private String errorMessagePlayerNameRequired;
	
	@Value("${error.message.OldMailRequired}")
	private String errorMessageOldMailRequired;
	
	@Value("${error.message.NewMailRequired}")
	private String errorMessageNewMailRequired;
	
	
	@RequestMapping(path = "/")
	public String getWelcomePage() {

		return "index.html";
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
		boolean bPlayerFound = true;
		
		//List die Daten aus der rangliste.txt ein
		try {
			players = Highscore.ReadRangliste();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
					
					//Schreibt die Rangliste erneut
					try {
						Highscore.WritteRanglisteAll(players); 
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} 
					
					return "redirect:/highscore";
				}
			}
			bPlayerFound = false;
		}
		
		if(!bPlayerFound)
			model.addAttribute("errorMessage", errorMessagePlayerNotFound);
		else
			model.addAttribute("errorMessage", errorMessage);
		
		return "deletePlayer.html";
	}
	
	
	/**
	 * Lädet die Fragen für die Quizseite
	 * @return
	 */
	@RequestMapping(value = { "/quizseite" }, method = RequestMethod.GET)
	public String getQuizPage(Model model) {
		
		Question question = null;
		
		//Es wird geprüft ob das Quiz schon gestartet wurde
		if(!bQuizStarted) {
			try {
				questions = Quiz.ReadQuestions();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bQuizStarted = true;
		}
		
		if(nFragen_Counter < 10) {
			
			for(Map.Entry<Integer, Question> e : questions.entrySet()) {
				 question = e.getValue();
				 questions.remove(e.getKey()); //Benutzte Frage wird aus der Liste gelöscht
				 break; //Schleife wird verlassen, da nur eine Frage aus der Liste benötigt wird
			}
			
			nPunktePlayer += question.getnPunkte(); //Die Punkte der Fragen werden zusammenaddiert
			model.addAttribute("question", question);
			nFragen_Counter++; //Counter für die Fragen wird erstellt
			return "quizseite.html";
		}
		else
		{
			nFragen_Counter = 1; //Zähler wird zurück gesetzt
			return "redirect:/quizende";
		}
	}
	
	/** 
	 * Gibt die Punktzahl des Spielers aus
	 */
	@RequestMapping(value = { "/quizende" }, method = RequestMethod.GET)
	public String showFinishPage(Model model) {
		PlayerForm playerForm = new PlayerForm();
		playerForm.setnPktZahl(nPunktePlayer);
		
		model.addAttribute("playerForm", playerForm);
		model.addAttribute("playerPunkte", nPunktePlayer);
		return "quizende.html";
	}
	
	/**
	 * Zeigt das Ergebnis des Quizes an und der Benutzer hat die Möglichkeit sich in der
	 * Rangliste zu verewigen
	 * @param model
	 * @return Gibt den Namen des angeforderte html Dokuments zurück
	 */
	@RequestMapping(value = { "/quizende" }, method = RequestMethod.POST)
	public String savePlayer(Model model, @ModelAttribute("playerForm") PlayerForm playerForm) {
		String sName = playerForm.getsName();
		String sMail = playerForm.getsMail();
		
		if(sName != null && sName.length() > 0 && sMail != null && sMail.length() > 0) {
			
			Player newPlayer = new Player(0, sName, nPunktePlayer, sMail);
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
	
	@RequestMapping(value = { "/updatePlayerData"}, method = RequestMethod.GET)
	public String getUpdatePage(Model model) {
		
		PlayerForm playerForm = new PlayerForm();
		model.addAttribute("playerForm", playerForm);
		return "updatePlayerData.html";
	}
	
	
	@RequestMapping(value = { "/updatePlayerData"}, 
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public String updatePlayer(Model model, @RequestBody Player player1) {
		
		String sName = player1.getsName();
		String sMail = player1.getsMail();
		String sNewMail = player1.getsNewMail();
		
		if(sName != null && sName.length() > 0 && sMail != null && sMail.length() > 0 && sNewMail != null && sNewMail.length() > 0) {
			
			Iterator<Player> iter = players.iterator();
			while(iter.hasNext()) {
				Player player = iter.next();
				
				if(player.getsName().equals(sName) && player.getsMail().equals(sMail)) {
										
					player.setsMail(sNewMail); //Neue Mail vom Spieler wird gesetzt
					
					//Schreibt die Rangliste erneut
					try {
						Highscore.WritteRanglisteAll(players); 
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} 
					
					return "redirect:/highscore";
				}
			}
		}
		else if (sName == null || sName.length() == 0)
			model.addAttribute("errorMessage", errorMessagePlayerNameRequired);
		else if(sMail == null || sName.length() == 0)
			model.addAttribute("errorMessage", errorMessageOldMailRequired);
		else if(sNewMail == null || sNewMail.length() == 0)
			model.addAttribute("errorMessage", errorMessageNewMailRequired);
		else
			model.addAttribute("errorMessage", errorMessagePlayerNotFound);
		
		return "updatePlayerData.html";
	}
}


