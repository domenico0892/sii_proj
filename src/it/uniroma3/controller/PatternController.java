package it.uniroma3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import it.uniroma3.facade.MongoConnection;
import it.uniroma3.facade.PatternFacade;

@WebServlet("/PatternController")
public class PatternController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String pattern = request.getParameter("newPattern");
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		Document doc = (Document)session.getAttribute("pattern");
		List<String> res = this.split(name);
		if (res.size() == 1) {
			doc.append(name, new Document().append("tag",pattern).append("figli", new Document()));
		}
		if (res.size() == 2) {
			Document padre = (Document) doc.get(res.get(0));
			Document figli = (Document) padre.get("figli");
			figli.append(res.get(1), pattern);
		}
		session.setAttribute("pattern", doc);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MongoConnection m = new MongoConnection();
		PatternFacade p = new PatternFacade(m);
		HttpSession session = request.getSession();
		Document doc = (Document)session.getAttribute("pattern");
		p.addPatternDocument(doc);
		m.close();
		String nextPage = response.encodeURL("/task");
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
	public List<String> split (String name) {
		List<String> res = new ArrayList<>();
		if (name.indexOf('.') != -1) {
			res.add(name.substring(0, name.indexOf('.')));
			res.add(name.substring(name.indexOf('.') + 1, name.length()));
		}
		else
			res.add(name);
		return res;
	}
}
