package it.uniroma3.model;

import java.util.HashMap;
import java.util.Map;

public class Pattern {
	
	private String host;
	private Map<String, String> patternMap;
	
	public Pattern(String host, Map<String, String> patternMap) {
		this.host = host;
		this.patternMap = new HashMap<String, String>();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public void addPattern (String key, String patt) {
		this.patternMap.put(key, patt);
	}
	
	public String getPattern (String key) {
		return this.patternMap.get(key);
	}
	
	public Map<String, String> getPatternMap () {
		return this.patternMap;
	}

	/*public Map<String, String> getPattern() {
		return pattern;
	}

	public void setPattern(Map<String, String> pattern) {
		this.pattern = pattern;
	}*/
}
