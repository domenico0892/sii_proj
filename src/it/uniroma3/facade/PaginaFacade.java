package it.uniroma3.facade;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import it.uniroma3.model.Pagina;

public class PaginaFacade {
	
	private MongoConnection conn;
	
	public PaginaFacade(MongoConnection m) {
		this.conn = m;
	}
	
	public List<Pagina> getPagineByHost (String host) {
		Document query = new Document();
		List<Pagina> l = new ArrayList<>();
		query.append("host", host);
		FindIterable<Document> f = this.conn.getMongoClient().getDatabase("pagine").getCollection("pagine").find(query);
		for (Document d : f) {
			l.add(document2Pagina(d));
		}
		return l;
	}
	
	public Pagina getPaginaByUrl (String url) {
		Document query = new Document();
		query.append("url", url);
		FindIterable<Document> f = this.conn.getMongoClient().getDatabase("pagine").getCollection("pagine").find(query);
		return document2Pagina(f.first());
	}
	
	private Pagina document2Pagina (Document d) {
		String url = d.getString("url");
		String host = d.getString("host");
		String html = d.getString("html");
		return new Pagina(url, host, html);
		}
}
