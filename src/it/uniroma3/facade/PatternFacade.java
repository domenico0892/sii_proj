package it.uniroma3.facade;

import java.util.HashMap;
import java.util.Map;

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

	public void addPatternDocument (Document d) {
		this.conn.getMongoClient().getDatabase("pagine").getCollection("pattern").insertOne(d);
	}

	private Document pattern2Document (Pattern p) {
		Document d = new Document();
		d.append("host", p.getHost());
		for (String key:p.getPatternMap().keySet())
			d.append(key, p.getPatternMap().get(key));
		return d;
	}

	private Pattern document2Pattern (Document d) {
		String host = d.getString("host");
		Map<String, String> mappa = new HashMap<String, String>();
		for (String key : d.keySet()) 
			if (!key.equals("_id") && !key.equals("host"))
				mappa.put(key, d.getString(key));		
		return new Pattern(host, mappa);
	}
}

