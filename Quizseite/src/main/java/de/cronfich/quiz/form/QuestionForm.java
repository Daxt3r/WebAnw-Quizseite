package de.cronfich.quiz.form;

public class QuestionForm {

	private int nQuizID;
	private int nPunkte;
	private String sFrage;
	private String [] array_Antworten = new String[3];
	
	public int getnQuizID() {
		return nQuizID;
	}
	
	public void setnQuizID(int nQuizID) {
		this.nQuizID = nQuizID;
	}
	
	public int getnPunkte() {
		return nPunkte;
	}
	
	public void setnPunkte(int nPunkte) {
		this.nPunkte = nPunkte;
	}
	
	public String getsFrage() {
		return sFrage;
	}
	
	public void setsFrage(String sFrage) {
		this.sFrage = sFrage;
	}
	
	public String[] getArray_Antworten() {
		return array_Antworten;
	}
	
	public void setArray_Antworten(String[] array_Antworten) {
		this.array_Antworten = array_Antworten;
	}
}
