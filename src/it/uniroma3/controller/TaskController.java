package it.uniroma3.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import it.uniroma3.facade.ContentBlockFacade;
import it.uniroma3.facade.MongoConnection;
import it.uniroma3.facade.PaginaFacade;
import it.uniroma3.facade.PatternFacade;
import it.uniroma3.model.ContentBlock;
import it.uniroma3.model.ContentBlockExtractor;
import it.uniroma3.model.Pagina;
import it.uniroma3.model.Pattern;

@WebServlet("/task")
public class TaskController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String nextPage = "/index.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PaginaFacade pgf = new PaginaFacade(MongoConnection.getInstance());
		PatternFacade ptf = new PatternFacade(MongoConnection.getInstance());
		
		HttpSession session = request.getSession(); //1
		//this.mc = new MongoConnection();
		//richiesta parametri //2
		String url = request.getParameter("url");
		String keyword = request.getParameter("keyword");
		List<String> k = matchEntity(keyword);


		//parse url per host e estrazione pagine //3
		URL pagina = new URL (url);
		String host = pagina.getHost();
		session.setAttribute("url", url); // da levare?
		session.setAttribute("keywords", k); // da levare?
		List<Pagina> l = pgf.getPagineByHost(host);

		//ricerca del pattern //4
		Pattern pattern = ptf.getPatternByHost(host);

		if (pattern == null) {
			if (l.size()>0) {
				Pagina daPresentare = null;
				for (Pagina pag : l) {
					if (pag.getUrl().equals(url))
						daPresentare = pag;
				}
				if (daPresentare == null)
					daPresentare = l.get(new Random().nextInt(l.size()-1));
				String html = daPresentare.getHtml();
				Document doc = Jsoup.parse(html);
				doc.getElementsByTag("head").append("<script type=\"text/javascript\" src=\"jquery.js\"/><script type=\"text/javascript\" src=\"jquery.dom-outline-1.0.js\"/><script type=\"text/javascript\" src=\"app.js\"/>");
				doc.getElementsByTag("body").prepend("<div><form action=\"PatternController\" method=\"post\"><input id=\"progettosii\" type=\"submit\" name=\"submit\" value=\"ho finito!\"/></form></div>");
				this.nextPage = "/anteprima.jsp";
				Pattern newPat = new Pattern();
				newPat.setHost(host);
				session.setAttribute("pattern", newPat);
				session.setAttribute("pagina", doc.toString());
			}
			else {
				request.setAttribute("errore", "Nessuna pagina trovata!");
			}
		}
		else {
			//estrazione    //5
			session.setAttribute("host", pattern.getHost()); // da levare?
			int size = extractAll(l,pattern,k,MongoConnection.getInstance());
			request.setAttribute("stato", "estratti "+size+" content block");
			MongoConnection.getInstance().close();
		}

		//prossima pagina //6
		this.nextPage = response.encodeURL(this.nextPage);
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(this.nextPage);
		rd.forward(request, response);
		
		return; 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		PaginaFacade pgf = new PaginaFacade(MongoConnection.getInstance());
		PatternFacade ptf = new PatternFacade(MongoConnection.getInstance());
		HttpSession session = request.getSession(); //1

		//richiesta parametri //2
		String url = (String) session.getAttribute("url");
		@SuppressWarnings("unchecked")
		List<String> k = (List<String>)session.getAttribute("keywords");

		//parse url per host e estrazione pagine //3
		URL pagina = new URL (url);
		String host = pagina.getHost();
		List<Pagina> l = pgf.getPagineByHost(host);

		//ricerca del pattern //4
		Pattern pattern = ptf.getPatternByHost(host);	


		//estrazione //5
		int size = extractAll(l,pattern,k,MongoConnection.getInstance());
		request.setAttribute("stato", "estratti "+size+" content block");

		//prossima pagina //6
		MongoConnection.getInstance().close();
		this.nextPage = response.encodeURL("/index.jsp");
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(this.nextPage);
		rd.forward(request, response);
	}



	public List<String> matchEntity(String frase){
		List<String> entity = new ArrayList<String>();
		String [] sp = frase.split(" ");
		for (String kw: sp){
			entity.add(kw);
		}
		return entity;
	}		


	public int extractAll(List<Pagina> l, Pattern pattern, List<String> kw, MongoConnection m){

		ContentBlockExtractor c = new ContentBlockExtractor(l, pattern, kw);
		List<ContentBlock> lc = c.extract();
		ContentBlockFacade cbf = new ContentBlockFacade(m);
		for (ContentBlock cb : lc){
			cbf.addContentBlock(cb);
		}
		return lc.size();
	}
}
