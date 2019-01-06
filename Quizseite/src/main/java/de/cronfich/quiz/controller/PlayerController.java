package de.cronfich.quiz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.cronfich.quiz.form.PlayerForm;
import de.cronfich.quiz.model.Player;

public class PlayerController {
			
	@RequestMapping(value = { "/addPlayer" }, method = RequestMethod.GET)
	public String showAddPlayerPage(Model model) {
		PlayerForm playerForm = new PlayerForm();
		model.addAttribute("playerForm", playerForm);
		return "addPlayer";
	}
}
