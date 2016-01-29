package it.uniroma3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentBlock {
	
	private String host;
	private String url;
	private String dataEstrazione;
	private String type;
	private Map<String, String> values;
	private List<String> entities;
	
	public ContentBlock () {
		this.values = new HashMap<>();
		this.entities = new ArrayList<>();
	}

	public void addValue (String key, String value) {
		this.values.put(key, value);
	}
	
	public void addEntity (String entity) {
		this.entities.add(entity);
	}
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDataEstrazione() {
		return dataEstrazione;
	}

	public void setDataEstrazione(String dataEstrazione) {
		this.dataEstrazione = dataEstrazione;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public List<String> getEntities() {
		return entities;
	}

	public void setEntity(List<String> entities) {
		this.entities = entities;
	}
	
	
	
	
	
	
	
	
	

	/*private String url;
	private String host;
	private String contenuto;
	private String dataCreazione;
	private String dataEstrazione;
	private String utente;
	private List<String> entity;
	
	public ContentBlock (String url, String host, String contenuto, String dataCreazione, String dataEstrazione, String utente) {
		this.url = url;
		this.host = host;
		this.contenuto = contenuto;
		this.dataCreazione = dataCreazione;
		this.dataEstrazione = dataEstrazione;
		this.utente = utente;
		this.entity = new ArrayList<String>();
	}
	
	public ContentBlock () {}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getDataEstrazione() {
		return dataEstrazione;
	}

	public void setDataEstrazione(String dataEstrazione) {
		this.dataEstrazione = dataEstrazione;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	public List<String> getEntity() {
		return this.entity;
	}
	
	public void setEnitity (List<String> l) {
		this.entity = l;
	}
	*/
}
