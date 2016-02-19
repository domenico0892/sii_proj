package it.uniroma3.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma3.facade.MongoConnection;
import it.uniroma3.facade.PatternFacade;
import it.uniroma3.model.ContentBlockType;
import it.uniroma3.model.Pattern;

@WebServlet("/PatternController")
public class PatternController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String pattern = request.getParameter("newPattern");
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		Pattern p = (Pattern) session.getAttribute("pattern");
		List<String> res = this.split(name);
		if (res.size() == 1) {
			p.putContentBlockType(new ContentBlockType(name, pattern));
		}
		if (res.size() == 2) {
			p.putFiglio(p.getContentBlockTypeByName(res.get(0)), res.get(1), pattern);
		}
		
		session.setAttribute("pattern", p);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PatternFacade p = new PatternFacade(MongoConnection.getInstance());
		HttpSession session = request.getSession();
		Pattern patt = (Pattern)session.getAttribute("pattern");
		p.addPattern(patt);
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
