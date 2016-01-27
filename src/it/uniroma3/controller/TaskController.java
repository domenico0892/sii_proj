package it.uniroma3.controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;

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
	private MongoConnection mc = new MongoConnection();
	private String nextPage = "/index.jsp";
	private PaginaFacade pgf = new PaginaFacade(mc);
	private PatternFacade ptf = new PatternFacade(mc);
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

//		String nextPage = "/index.jsp";
//		MongoConnection m = new MongoConnection();
//		PaginaFacade p = new PaginaFacade(m);
//		PatternFacade patt = new PatternFacade(m);
		HttpSession session = request.getSession();


		//richiesta parametri
		String url = request.getParameter("url");
		//String keyword = request.getParameter("keyword");

		//parse url per host e estrazione pagine
		URL pagina = new URL (url);
		String host = pagina.getHost();
		List<Pagina> l = this.pgf.getPagineByHost(host);

		//ricerca del pattern
		Pattern pattern = this.ptf.getPatternByHost(host);
		
		if (pattern == null) {
			//analisi pagina inserita
			if (l.size()>0) {
				String html = l.get(0).getHtml();
				Document doc = Jsoup.parse(html);
				doc.getElementsByTag("head").append("<script type=\"text/javascript\" src=\"jquery.js\"/><script type=\"text/javascript\" src=\"jquery.dom-outline-1.0.js\"/><script type=\"text/javascript\" src=\"app.js\"/>");
				nextPage = "/anteprima.jsp";
				org.bson.Document patternDoc = new org.bson.Document();
				patternDoc.append("host", host);
				session.setAttribute("pattern", patternDoc);
				session.setAttribute("pagina", doc.toString());
			}
		}
		else {
			//qui ci va l'estrazione dei commenti!

//			session.setAttribute("size pagine", l.size());
//			ContentBlockExtractor c = new ContentBlockExtractor(l, pattern);
//			List<ContentBlock> lc = c.extract();
//			session.setAttribute("size cntblk", lc.size());
//			String result = "gg";
//			for (ContentBlock cb : lc) {
//				result = result + cb.getUtente() + " ";
//			}
//			session.setAttribute("titoli", result);
			extractAll(l,pattern,this.mc);
			
		}
		nextPage = response.encodeURL(nextPage);
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);

		return; 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 

		throws ServletException, IOException {
		//richiesta parametri
		String url = request.getParameter("url");
		//String keyword = request.getParameter("keyword");

		//parse url per host e estrazione pagine
		URL pagina = new URL (url);
		String host = pagina.getHost();
		List<Pagina> l = this.pgf.getPagineByHost(host);

		//ricerca del pattern
		Pattern pattern = this.ptf.getPatternByHost(host);	
		
		extractAll(l,pattern,this.mc);
		
	}
	
	public void extractAll(List<Pagina> l, Pattern pattern, MongoConnection m){
		ContentBlockExtractor c = new ContentBlockExtractor(l, pattern, null);
		List<ContentBlock> lc = c.extract();
		ContentBlockFacade cbf = new ContentBlockFacade(m);
		for (ContentBlock cb : lc){
			cbf.addContentBlock(cb);
		}		
	}
}
