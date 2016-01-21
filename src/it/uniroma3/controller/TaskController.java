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

import it.uniroma3.facade.MongoConnection;
import it.uniroma3.facade.PaginaFacade;
import it.uniroma3.facade.PatternFacade;
import it.uniroma3.model.Pagina;
import it.uniroma3.model.Pattern;

@WebServlet("/task")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String nextPage;
		MongoConnection m = new MongoConnection();
		PaginaFacade p = new PaginaFacade(m);
		PatternFacade patt = new PatternFacade(m);


		//richiesta parametri
		String url = request.getParameter("url");
		//String keyword = request.getParameter("keyword");

		//parse url per host e estrazione pagine
		URL pagina = new URL (url);
		String host = pagina.getHost();
		List<Pagina> l = p.getPagineByHost(host);

		//ricerca del pattern
		Pattern pattern = patt.getPatternByHost("host");
		if (pattern == null) {
			//analisi pagina inserita
			if (l.size()>0) {
				String html = l.get(0).getHtml();
				Document doc = Jsoup.parse(html);
				doc.getElementsByTag("head").append("<script type=\"text/javascript\" src=\"jquery.js\"/><script type=\"text/javascript\" src=\"jquery.dom-outline-1.0.js\"/><script type=\"text/javascript\" src=\"app.js\"/>");
				nextPage = "/anteprima.jsp";
				//String html = "<html><head></head><body><h1>hello world!</h1></body></html>";
				HttpSession session = request.getSession();
				session.setAttribute("pagina", doc.toString());
			}
			else {
				//qui ci va l'estrazione dei commenti!
				nextPage = "/index.jsp";
			}
			nextPage = response.encodeURL(nextPage);
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
		return; 
	}
}
