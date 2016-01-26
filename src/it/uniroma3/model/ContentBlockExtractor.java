package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ContentBlockExtractor {
	
	private List<Pagina> pagine;
	private Pattern pattern;
	
	public ContentBlockExtractor (List<Pagina> pagine, Pattern pattern) {
		this.pagine = pagine;
		this.pattern = pattern;
	}
	
	public List<ContentBlock> extract () {
		List<ContentBlock> list = new ArrayList<ContentBlock>();
		
		for(Pagina p: this.pagine){
			String html = p.getHtml();
			Document doc = Jsoup.parse(html);
			
			String titolo = doc.toString();
			ContentBlock c = new ContentBlock();
			c.setUtente(titolo);
			list.add(c);
		}
		return list;
	}

	public List<Pagina> getPagine() {
		return pagine;
	}

	public void setPagine(List<Pagina> pagine) {
		this.pagine = pagine;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}
