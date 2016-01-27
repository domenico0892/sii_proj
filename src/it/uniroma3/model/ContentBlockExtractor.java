package it.uniroma3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentBlockExtractor {

	private List<Pagina> pagine;
	private Pattern pattern;
	private List<String> keywords;

	public ContentBlockExtractor (List<Pagina> pagine, Pattern pattern, List<String> kw) {
		this.pagine = pagine;
		this.pattern = pattern;
		this.keywords = kw;
	}

	public List<ContentBlock> extract () {
		List<ContentBlock> list = new ArrayList<ContentBlock>();

		for(Pagina p: this.pagine){
			String html = p.getHtml();
			Document doc = Jsoup.parse(html);

			//sezione di estrazione commenti
			if (this.pattern.getPattern("blocco") != null) {
				Elements es = doc.select(this.pattern.getPattern("blocco"));
				for (Element e : es) {
					ContentBlock c = new ContentBlock();
					if (this.pattern.getPattern("utente") != null) 
						c.setUtente(e.select(this.pattern.getPattern("utente")).text());
					if (this.pattern.getPattern("data") != null)
						c.setDataCreazione((e.select(this.pattern.getPattern("data")).text()));
					if (this.pattern.getPattern("testo") != null)
						c.setContenuto((e.select(this.pattern.getPattern("testo")).text()));
					c.setDataEstrazione(new Date().toString());
					c.setHost(p.getHost());
					c.setUrl(p.getUrl());
					list.add(c);
				}
			}
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
