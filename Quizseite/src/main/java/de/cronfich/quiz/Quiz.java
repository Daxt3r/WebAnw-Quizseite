package de.cronfich.quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.cronfich.quiz.model.Player;
import de.cronfich.quiz.model.Question;

public class Quiz {

	 /**
	  * Die Methode List die Werte aus fragen.txt ein und speichert diese in fragen_list; 
	 * @throws IOException 
	  */
	public static List<Question> ReadQuestions() throws IOException, FileNotFoundException {
		
		List<Question> tmp_fragen = new ArrayList<>();
				
		String sZeile;
		String [] tmp_array;
		String [] tmp_antworten  = new String[3];
		
		String path = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\fragen.txt";
		//String path = "Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
		FileReader fp_Rangliste = new FileReader(path);
		BufferedReader br_Rangliste = new BufferedReader(fp_Rangliste);
		
		br_Rangliste.readLine(); //Kopfzeile wird ausgelesen
		
		while((sZeile = br_Rangliste.readLine()) != null) {
			
			tmp_array = sZeile.split(";");
			//Antworten werden in das Array gespeichert
			tmp_antworten[0] = tmp_array[3]; //Richtige Antwort
			tmp_antworten[1] = tmp_array[4]; //1. Falsche Antwort
			tmp_antworten[2] = tmp_array[5]; //2. Falsche Antwort
			
			tmp_fragen.add(new Question(Integer.parseInt(tmp_array[0]), Integer.parseInt(tmp_array[1]), tmp_array[2], tmp_antworten));
		}
	    br_Rangliste.close();
	    
	    return tmp_fragen;
	}
}
