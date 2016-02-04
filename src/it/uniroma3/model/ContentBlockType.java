package it.uniroma3.model;

public class ContentBlockType {
	
	private String name;
	private String tag;
	
	public ContentBlockType (String name, String tag) {
		this.name = name;
		this.tag = tag;
	}
	
	public ContentBlockType () {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int hashCode () {
		return this.name.hashCode();
	}
	
	public boolean equals (ContentBlockType c) {
		return this.name.equals(c.getName());
	}
}
