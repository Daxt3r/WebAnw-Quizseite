package de.cronfich.quiz.model;

public class Player implements Comparable<Object>{

	private int nRang;
	private String sName;
	private String sMail;
	private int nPktZahl;
	
	public Player (int nRang, String sName, int nPktZahl, String sMail) {
		this.nRang = nRang;
		this.sName = sName;
		this.nPktZahl = nPktZahl;
		this.sMail = sMail;
	}
		
	public int getnRang() {
		return nRang;
	}
	
	public void setnRang(int nRang) {
		this.nRang = nRang;
	}
	
	public String getsName() {
		return sName;
	}
	public void setsName(String name) {
		sName = name;
	}
	public int getnPktZahl() {
		return nPktZahl;
	}
	public void setnPktZahl(int nPktZahl) {
		this.nPktZahl = nPktZahl;
	}
	
	public String getsMail() {
		return sMail;
	}

	public void setsMail(String sMail) {
		this.sMail = sMail;
	}
	
	/** 
	 * Erstellt das Datenformat, damit der Datensatz in die .txt Datei geschrieben werden kann
	 * @return Datenformat, welches in die rangliste.txt geschrieben werden kann
	 */
	public String getDataformat() {
		
		String data = this.getsName() + ";" + this.getnPktZahl() + ";" + this.getsMail();
		return data;
	}

	@Override
	public int compareTo(Object o) {
		Player other = (Player) o;
		return Integer.compare(other.nPktZahl, this.nPktZahl);
	}
}
