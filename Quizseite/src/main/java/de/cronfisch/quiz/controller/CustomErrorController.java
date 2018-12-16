package de.cronfisch.quiz.controller;

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
							("<html>"
									+ "<head>"
									+ "<link rel=stylesheet href=css/styles.css>"
									+ "</head>"
									+ "<body>"
									+ "<h1 align=center>Page Not Found</h1>"
									+ "<div align=center>Sorry, but the page you were trying to</div>"
									+ "<div align=center>view does not exist.</div>"
									+ "<div align=center>Status code: <b>%s</b></div>"
									+ "<div align=center>Exception Message: %s</div>"
									+ "<body>"
									+ "</html>",
	                      statusCode, exception==null? "N/A": exception.getMessage());
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
