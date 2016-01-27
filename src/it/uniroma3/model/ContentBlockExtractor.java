package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentBlockExtractor {
	
	private List<Pagina> pagine;
	private Pattern pattern;
	
	public ContentBlockExtractor (List<Pagina> pagine, Pattern pattern) {
		this.pagine = pagine;
		this.pattern = pattern;
	}
	
	public List<ContentBlock> extract () {
		List<ContentBlock> list = new ArrayList<ContentBlock>();
		//:contains(text): find elements that contain the given text.
		//The search is case-insensitive; e.g. p:contains(jsoup)
		for(Pagina p: this.pagine){
			String html = p.getHtml();
			Document doc = Jsoup.parse(html);
			
			//Elements commento = doc.select("img[src$=.png]");
			Elements commenti = doc.select("p.testoPost");
			//String text = doc.body().text(); 
			ContentBlock c = new ContentBlock();
			c.setUtente(commenti.text());
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
