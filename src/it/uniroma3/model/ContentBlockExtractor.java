package it.uniroma3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

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

			//per ora solo CB tipati, cioè del tipo padre.figlio
			for (String keyPadre : this.pattern.getPatternMap().keySet()) {
				Map<String, String> m = this.pattern.getPadre(keyPadre);
				Elements es = doc.select(m.get("tag"));
				for (Element e : es) {
					ContentBlock c = new ContentBlock();
					c.setDataEstrazione(new Date().toString());
					c.setHost(p.getHost());
					c.setUrl(p.getUrl());
					c.setType(keyPadre);
					if (m.size() == 1) {
						c.addValue(keyPadre, e.text());
						for (String s : matchEntity(e.text()))
							c.addEntity(s);
					}
					else {
						for (String keyFiglio : this.pattern.getPadre(keyPadre).keySet()) {
							if (!keyFiglio.equals("tag")) {
								String text = e.select(m.get(keyFiglio)).text();
								c.addValue(keyFiglio, text);
								for (String s : matchEntity(text))
									c.addEntity(s);
							}
						}
					}
					list.add(c);
				}
			}
		}
		return list;
	}

	public Set<String> matchEntity (String contenuto) {
		Set<String> entity = new HashSet<String>();
		for (String kw : this.keywords) {
			java.util.regex.Pattern my_pattern = java.util.regex.Pattern.compile("([^a-z]"+kw+"[^a-z])|(^"+kw+"[^a-z])");
			Matcher m = my_pattern.matcher(contenuto.toLowerCase());
			if (m.find()){
				entity.add(kw);
			}
		}
		return entity;
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
