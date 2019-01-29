package de.cronfich.quiz.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {
	
	@RequestMapping("/error")
	@ResponseBody
	public String handleError(HttpServletRequest request) {
		Integer statusCode = (Integer)
				request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception)
				request.getAttribute("javax.servlet.error.exception");
				return String.format
							("<!DOCTYPE html>"
									+ "<head>"
									+ "<meta charset=\"UTF-8\">\r\n" 
									+ "<meta name=\"description\" content=\"Fehler Seite\">"
									+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/styles.css\" />"
									+ "<title>Fehlerseite</title>"
									+ "</head>"
									+ "<body>"
									+ "<section>"
									+ "<h1>Page Not Found</h1>"
									+ "<p>Sorry, but the page you were trying to view does not exist.</p>"
									+ "<p>Status code: <b>%s</b></p>"
									+ "<p>Exception Message: %s</p>"
									+ "</section>"
									+ "</body>"
									+ "</html>",
	                      statusCode, exception==null? "N/A": exception.getMessage());
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
