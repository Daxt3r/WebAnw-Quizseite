package de.cronfich.quiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.cronfich.quiz.model.Player;

public class Highscore {
	
	 /**
	  * Die Methode List die Werte aus rangliste.txt ein und speichert diese in tmp_liste; 
	 * @throws IOException 
	  */
	public static List<Player> ReadRangliste() throws IOException, FileNotFoundException {
				
		List<Player> tmp_liste = new ArrayList<>();
		
		String sZeile;
		String [] tmp_array;
		String path = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
		//String path = "Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
		FileReader fp_Rangliste = new FileReader(path);
		BufferedReader br_Rangliste = new BufferedReader(fp_Rangliste);
		
		br_Rangliste.readLine(); //Kopfzeile wird ausgelesen
		
		while((sZeile = br_Rangliste.readLine()) != null) {
			
			tmp_array = sZeile.split(";");
			tmp_liste.add(new Player(Integer.parseInt("0"), tmp_array[0], Integer.parseInt(tmp_array[1]), tmp_array[2]));
		}
	    br_Rangliste.close();
	    
	    Highscore.sortList_setRang(tmp_liste);
	    
	    return tmp_liste;
	}
	
	/**
	 * Fügt einen neuen Datensatz in die rangliste.txt ein
	 * @param player Neuer Spielerobjekt, welche in die Datei geschrieben werden soll
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void WritteRangliste(Player player) throws IOException, FileNotFoundException {
		
		String path = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
		
		PrintWriter pWriter = null;
		pWriter = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
		
		pWriter.println(player.getDataformat());
		pWriter.flush();
		pWriter.close();
				
	}
	
	/**
	 * Setzt die Ränge für die einzelnen Spieler
	 * @param player_liste Liste mit den Spielern die Sortiert werden sollen
	 */
	public static void sortList_setRang(List<Player> player_liste) {
		
		int i = 1;
		
		Collections.sort(player_liste);
		
		for (Player player : player_liste) {
			player.setnRang(i++);
		}
	}
}
