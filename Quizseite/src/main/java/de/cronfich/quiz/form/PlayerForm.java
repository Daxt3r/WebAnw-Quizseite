package de.cronfich.quiz.form;

public class PlayerForm {
	
	private int nRang;
	private String sName;
	private int nPktZahl;
	private String sMail;
	private String sNewMail;

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
	
	public void setsMail(String sMail) {
		this.sMail = sMail;
	}
	
	public String getsMail() {
		return sMail;
	}

	public String getsNewMail() {
		return sNewMail;
	}

	public void setsNewMail(String sNewMail) {
		this.sNewMail = sNewMail;
	}
}
