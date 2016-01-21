package it.uniroma3.facade;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import it.uniroma3.model.Pattern;

public class PatternFacade {
	
	private MongoConnection conn;
	
	public PatternFacade(MongoConnection m) {
		this.conn = m;
	}
	
	public Pattern getPatternByHost (String host) {
		Document query = new Document();
		query.append("host", host);
		FindIterable<Document> f = this.conn.getMongoClient().getDatabase("pagine").getCollection("pattern").find(query);
		if (f.first()!=null)
			return document2Pattern(f.first());
		else
			return null;
	}
	
	public void addPattern (Pattern p) {
		this.conn.getMongoClient().getDatabase("pagine").getCollection("pattern").insertOne(pattern2Document(p));
	}
	
	private Document pattern2Document (Pattern p) {
		Document d = new Document();
		d.append("host", p.getHost());
		d.append("pattern", p.getPattern());
		return d;
	}
	
	private Pattern document2Pattern (Document d) {
		String host = d.getString("host");
		String pattern = d.getString("pattern");
		return new Pattern(host, pattern);
		}
}
