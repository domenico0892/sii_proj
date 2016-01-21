package it.uniroma3.model;

public class Pagina {

	private String url;
	private String host;
	private String html;
	
	public Pagina (String u, String h, String c) {
		this.url = u;
		this.host = h;
		this.html = c;
	}

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

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
