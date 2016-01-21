package it.uniroma3.model;

public class Pattern {
	
	private String host;
	private String pattern;
	
	public Pattern(String host, String pattern) {
		this.host = host;
		this.pattern = pattern;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
