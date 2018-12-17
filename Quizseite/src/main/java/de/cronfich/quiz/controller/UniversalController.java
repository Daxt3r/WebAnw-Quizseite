package de.cronfich.quiz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UniversalController {
	
	
	@RequestMapping(path = "/")
	//@ResponseBody -> Gibt den return Wert als solchen zurück. Wird diese Annotation entfernt, wird auf die html Datei 
	//				   referenziert. Diese muss jedoch unter templates liegen. Ansonsten gibt es ein "Page not found" - Error 500
	public String getWelcomePage() {

		return "index.html";
	}
}


