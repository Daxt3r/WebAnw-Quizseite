package de.cronfich.quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.cronfich.quiz.model.Player;

public class Quiz {

	 /**
	  * Die Methode List die Werte aus rangliste.json aus und speichert diese in list_player; 
	 * @throws IOException 
	  */
	public static List<Quiz> ReadQuestions() throws IOException, FileNotFoundException {
		
		List<Quiz> tmp_quiz = new ArrayList<>();
				
		String sZeile;
		String [] tmp_array;
		String path = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\fragen.txt";
		//String path = "Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
		FileReader fp_Rangliste = new FileReader(path);
		BufferedReader br_Rangliste = new BufferedReader(fp_Rangliste);
		
		br_Rangliste.readLine(); //Kopfzeile wird ausgelesen
		
		while((sZeile = br_Rangliste.readLine()) != null) {
			
			tmp_array = sZeile.split(";");
			
			//tmp_quiz.add(new Player(Integer.parseInt(tmp_array[0]), Integer.parseInt(tmp_array[1]), tmp_array[2], Integer.parseInt(tmp_array[3])));
		}
	    br_Rangliste.close();
	    
	    return tmp_quiz;
	}
}
