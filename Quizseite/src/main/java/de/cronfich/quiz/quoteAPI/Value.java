package de.cronfich.quiz.quoteAPI;

public class Value {

	private long id;
	private String quote;
	
	public Value() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	@Override
	public String toString() {
		return quote;
	}
}
