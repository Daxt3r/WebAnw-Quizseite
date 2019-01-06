package de.cronfich.quiz;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import de.cronfich.quiz.model.Player;

public class Highscore {
	
	 /**
	  * Die Methode List die Werte aus rangliste.json aus und speichert diese in list_player; 
	 * @throws IOException 
	  */
	public static void ReadJSON() throws IOException {
		Player json_player = new Player(0, 0, "", 0);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(json_player);
		
		JsonReader jsonReader = new JsonReader(new FileReader("data/rangliste.json"));
		
		try {
		    while(jsonReader.hasNext()){
		        JsonToken nextToken = jsonReader.peek();
		        System.out.println(nextToken);

		        if(JsonToken.BEGIN_OBJECT.equals(nextToken)){

		            jsonReader.beginObject();

		        } else if(JsonToken.NAME.equals(nextToken)){

		            String name  =  jsonReader.nextName();
		            System.out.println(name);

		        } else if(JsonToken.STRING.equals(nextToken)){

		            String value =  jsonReader.nextString();
		            System.out.println(value);

		        } else if(JsonToken.NUMBER.equals(nextToken)){

		            long value =  jsonReader.nextLong();
		            System.out.println(value);

		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		jsonReader.close();
	}
}
