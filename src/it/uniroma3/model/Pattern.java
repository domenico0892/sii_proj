package it.uniroma3.model;

import java.util.HashMap;
import java.util.Map;

public class Pattern {
	
	private String host;
	private Map<String, Map<String, String>> patternMap;	

	public Pattern(String host, Map<String, Map<String, String>> patternMap) {
		this.host = host;
		this.patternMap = patternMap;
	}
	
	public Pattern () {
		this.patternMap = new HashMap<>();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public void putPadre (String name, String tag) {
		Map<String,String> m = new HashMap<>();
		m.put("tag", tag);
		this.patternMap.put(name, m);
	}
	
	public void putFiglio (String padre, String name, String tag) {
		this.patternMap.get(padre).put(name, tag);
	}
	
	public Map<String, String> getPadre (String name) {
		return this.patternMap.get(name);
	}

	
	public Map<String, Map<String, String>> getPatternMap () {
		return this.patternMap;
	}
}
