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
		d.append("patterns", p.getPatternMap());
		/*for (String key:p.getPatternMap().keySet())
			d.append(key, p.getPatternMap().get(key));*/
		return d;
	}

	private Pattern document2Pattern (Document d) {
		Pattern p = new Pattern();
		p.setHost(d.getString("host"));
		Document patterns = (Document) d.get("patterns");
		for (String padre : patterns.keySet()) {
			Document docPadre = (Document)patterns.get(padre);
			p.putPadre(padre, docPadre.getString("tag"));
			for (String figlio : docPadre.keySet())
				if (!figlio.equals("tag"))
					p.putFiglio(padre, figlio, docPadre.getString(figlio));
		}	
		return p;
	}
}

