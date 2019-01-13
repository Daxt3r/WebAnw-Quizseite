package de.cronfich.quiz.model;

public class Question {

	private int nQuizID;
	private int nSchwierigkeit;
	private String sFrage;
	private String [] array_Antworten = new String[3];
	
	public Question(int nQuizID, int nSchwierigkeit, String sFrage, String [] array_Antworten) {
		this.nQuizID = nQuizID;
		this.nSchwierigkeit = nSchwierigkeit;
		this.sFrage = sFrage;
		this.array_Antworten = array_Antworten;
	}
	
	public int getnQuizID() {
		return nQuizID;
	}
	public void setnQuizID(int nQuizID) {
		this.nQuizID = nQuizID;
	}
	public int getnSchwierigkeit() {
		return nSchwierigkeit;
	}
	public void setnSchwierigkeit(int nSchwierigkeit) {
		this.nSchwierigkeit = nSchwierigkeit;
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
