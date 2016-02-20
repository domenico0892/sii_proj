package it.uniroma3.controller;

import java.io.IOException;
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

import it.uniroma3.facade.MongoConnection;
import it.uniroma3.facade.PaginaFacade;
import it.uniroma3.model.Pagina;
import it.uniroma3.model.Pattern;

@WebServlet("/anteprimactrl")
public class AnteprimaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String nextPage = "/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		PaginaFacade pgf = new PaginaFacade(MongoConnection.getInstance());
		//PatternFacade ptf = new PatternFacade(MongoConnection.getInstance());
		String url;
		if (request.getParameter("submit").equals("Mi sento fortunato!")) {
			List<String> pagine = (List<String>) session.getAttribute("pagine");
			url = pagine.get(new Random().nextInt(pagine.size()-1));
		}
		else {
			url = request.getParameter("url");
		}
		Pagina p = pgf.getPaginaByUrl(url);	
		String html = p.getHtml();
		Document doc = Jsoup.parse(html);
		doc.getElementsByTag("head").append("<script type=\"text/javascript\" src=\"jquery.js\"/><script type=\"text/javascript\" src=\"jquery.dom-outline-1.0.js\"/><script type=\"text/javascript\" src=\"app.js\"/>");
		doc.getElementsByTag("body").prepend("<div><form action=\"PatternController\" method=\"post\"><input id=\"progettosii\" type=\"submit\" name=\"submit\" value=\"ho finito!\"/></form></div>");
		this.nextPage = "/anteprima.jsp";
		Pattern newPat = new Pattern();
		newPat.setHost(p.getHost());
		session.setAttribute("pattern", newPat);
		session.setAttribute("pagina", doc.toString());
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(this.nextPage);
		rd.forward(request, response);
	}
}
