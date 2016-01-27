package it.uniroma3.controller;

import java.io.IOException;

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
		doc.append(name, pattern);
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
		String nextPage = response.encodeURL("/index.jsp");
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
