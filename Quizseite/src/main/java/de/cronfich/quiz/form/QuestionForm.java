package de.cronfich.quiz.form;

public class QuestionForm {

	private String sQuizID;
	private int nSchwierigkeit;
	private String sFrage;
	private String [] array_Antworten = new String[3];
	
	public String getsQuizID() {
		return sQuizID;
	}
	public void setsQuizID(String sQuizID) {
		this.sQuizID = sQuizID;
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