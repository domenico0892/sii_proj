package it.uniroma3.facade;

import org.bson.Document;
import it.uniroma3.model.ContentBlock;

public class ContentBlockFacade {

	private MongoConnection conn;

	public ContentBlockFacade (MongoConnection m) {
		this.conn = m;
	}
	
	public void addContentBlock (ContentBlock c) {
		this.conn.getMongoClient().getDatabase("pagine").getCollection("contents").insertOne(contentBlock2Document(c));
	}

	private Document contentBlock2Document (ContentBlock c) {
		Document d = new Document();
		d.append("url", c.getUrl());
		d.append("host", c.getHost());
		d.append("contenuto", c.getContenuto());
		d.append("dataCreazione", c.getDataCreazione());
		d.append("dataEstrazione", c.getDataEstrazione());
		d.append("utente", c.getUtente());
		if (c.getEntity().size()>0)
			d.append("entity", c.getEntity());
		return d;
	}

}
