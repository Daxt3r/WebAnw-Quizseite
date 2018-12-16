package de.cronfisch.quiz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UniversalController {
	
		
	@ResponseBody
	@RequestMapping(path = "/templates/html/", method = RequestMethod.GET) 
	public String getWelcomePage() {

		return "index.html";
	}
}


