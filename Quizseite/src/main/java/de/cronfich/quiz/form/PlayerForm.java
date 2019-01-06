package de.cronfich.quiz.form;

public class PlayerForm {
	
	private int nPlayerID;
	private int nRang;
	private String sName;
	private int nPktZahl;
		
	public int getnPlayerID() {
		return nPlayerID;
	}
	public void setnPlayerID(int nPlayerID) {
		this.nPlayerID = nPlayerID;
	}
	public int getnRang() {
		return nRang;
	}
	public void setnRang(int nRang) {
		this.nRang = nRang;
	}
	public String getName() {
		return sName;
	}
	public void setName(String name) {
		sName = name;
	}
	public int getnPktZahl() {
		return nPktZahl;
	}
	public void setnPktZahl(int nPktZahl) {
		this.nPktZahl = nPktZahl;
	}
}
