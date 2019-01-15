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
import java.util.Iterator;
import java.util.List;

import de.cronfich.quiz.model.Player;

public class Highscore {
	
	private static String path_rangliste = "C:\\Users\\Marcel\\git\\repository-quiz\\Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
	//private static String path_rangliste = "C:\\Users\\Tim\\git\\WebAnw-Quizseite\\Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
	//String path = "Quizseite\\src\\main\\resources\\static\\data\\rangliste.txt";
	/**
	  * Die Methode List die Werte aus rangliste.txt ein und speichert diese in tmp_liste; 
	 * @throws IOException 
	  */
	public static List<Player> ReadRangliste() throws IOException, FileNotFoundException {
				
		List<Player> tmp_liste = new ArrayList<>();
		
		String sZeile;
		String [] tmp_array;
		
		FileReader fp_Rangliste = new FileReader(path_rangliste);
		BufferedReader br_Rangliste = new BufferedReader(fp_Rangliste);
		
		br_Rangliste.readLine(); //Kopfzeile wird ausgelesen
		
		while((sZeile = br_Rangliste.readLine()) != null) {
			
			if(!sZeile.isEmpty()) {
				tmp_array = sZeile.split(";");
				tmp_liste.add(new Player(Integer.parseInt("0"), tmp_array[0], Integer.parseInt(tmp_array[1]), tmp_array[2]));
			}
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
				
		PrintWriter pWriter = null;
		pWriter = new PrintWriter(new BufferedWriter(new FileWriter(path_rangliste, true))); //Das true gibt an, dass die Daten nur angehangen werden sollen
		
		pWriter.println();
		pWriter.flush();
		pWriter.print(player.getDataformat());
		pWriter.flush();
		pWriter.close();
				
	}
	
	/**
	 * Aktuallisiert die Rangliste mit den gelöschten Spielern. Liste wird neu geschrieben.
	 * @param players Liste mit allen Spielern
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void WritteRanglisteAll(List<Player> players) throws IOException, FileNotFoundException {
		
		PrintWriter pWriter = null;
		pWriter = new PrintWriter(new BufferedWriter(new FileWriter(path_rangliste))); //rangliste.txt wird überschrieben
		
		pWriter.println("Name;Punkte;Mail"); //Kopfzeile wird in die rangliste.txt geschrieben
		pWriter.flush();
		
		Iterator<Player> iter = players.iterator();
		while(iter.hasNext()) {
			Player player = iter.next();
			
			pWriter.println(player.getDataformat());
			pWriter.flush();
		}
		
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
