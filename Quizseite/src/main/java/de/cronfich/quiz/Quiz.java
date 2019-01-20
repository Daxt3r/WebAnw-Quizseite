package de.cronfich.quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.cronfich.quiz.model.Question;

public class Quiz {

	//private static String path_fragen = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\fragen.txt";
	private static String path_fragen = "C:\\Users\\Tim\\git\\WebAnw-Quizseite\\Quizseite\\src\\main\\resources\\static\\data\\fragen.txt";

	
	private static List<Question> fragen_pool = new ArrayList<>();
	private static boolean init = false;
	private static int MAX_FRAGEN = 10;
	
	 /**
	  * Die Methode List die Werte aus fragen.txt ein und speichert diese in fragen_list; 
	 * @throws IOException 
	  */
	public static HashMap<Integer, Question> ReadQuestions() throws IOException, FileNotFoundException {
		
		String sZeile;
		String [] tmp_array;
		String [] tmp_antworten  = null;
		
		if(!init) {				
			FileReader fp_Rangliste = new FileReader(path_fragen);
			BufferedReader br_Rangliste = new BufferedReader(fp_Rangliste);
		
			while((sZeile = br_Rangliste.readLine()) != null) {
				tmp_antworten  = new String[3];
				tmp_array = sZeile.split(";");
				//Antworten werden in das Array gespeichert
				tmp_antworten[0] = tmp_array[3]; //Richtige Antwort
				tmp_antworten[1] = tmp_array[4]; //1. Falsche Antwort
				tmp_antworten[2] = tmp_array[5]; //2. Falsche Antwort
			
				fragen_pool.add(new Question(Integer.parseInt(tmp_array[0]), Integer.parseInt(tmp_array[1]), tmp_array[2], tmp_antworten));
			}
			br_Rangliste.close();
			init = true; //Die Fragelise wurder erfolgreich eingelesen und muss nicht erneut eingelesen werden
		}
	    
	    return CreateQuestionPool(fragen_pool);
	}
	
	
	private static HashMap<Integer, Question> CreateQuestionPool(List<Question> fragen_pool) {
	
		int nFragen_Counter = 0;
		int nZufallszahl = 0;
		
		HashMap<Integer, Question> fragen_liste = new HashMap<>(); //Liste mit den 10 Fragen
		
		//In der Schleife werden 10 Fragen aus dem Pool in die fragen_liste eingefügt. Jede Frage kommt nur einmal drin vor
		while(nFragen_Counter < MAX_FRAGEN) {
			nZufallszahl = (int) (Math.random() * 10) + nFragen_Counter; //Erstellt eine Zufallszahl, die nicht größer sein sollte als die Anzahl an Element im fragen_pool
			//Sicherheitshalber wird nochmal geprüft, dass der Wert nicht größer ist
			if(nZufallszahl < fragen_pool.size()) {
				//Es wird geprüft ob die Frage aus dem fragen_pool schon in der HashMap drin ist. Wenn ja wird eine neue Zufallszahl generiert
				//Wenn nicht, wird die Fragen in die HashMap eingefügt.
				if((fragen_liste.containsKey(fragen_pool.get(nZufallszahl).getnQuizID())) == false) {
					fragen_liste.put(fragen_pool.get(nZufallszahl).getnQuizID(), fragen_pool.get(nZufallszahl));
					nFragen_Counter++;
				}
			}
		}
		
		return fragen_liste;
	}
}
