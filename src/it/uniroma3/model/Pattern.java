package it.uniroma3.model;

import java.util.HashMap;
import java.util.Map;

public class Pattern {
	
	private String host;
	private Map<ContentBlockType, Map<String, String>> patternMap;	

	public Pattern(String host, Map<ContentBlockType, Map<String, String>> patternMap) {
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
	
	public void putContentBlockType (ContentBlockType c) {
		this.patternMap.put(c, new HashMap<>());
	}
	
	public void putFiglio (ContentBlockType c, String name, String tag) {
		this.patternMap.get(c).put(name, tag);
	}
	
	public Map<String, String> getFigliByContentBlockType (ContentBlockType c) {
		return this.patternMap.get(c);
	}
	
	public ContentBlockType getContentBlockTypeByName (String name) {
		for (ContentBlockType c : this.patternMap.keySet()) 
			if (c.getName().equals(name))
				return c;
		return null;
	}

	
	public Map<ContentBlockType, Map<String, String>> getPatternMap () {
		return this.patternMap;
	}
}
