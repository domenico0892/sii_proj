import java.io.IOException;
import java.net.URL;

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

@WebServlet("/task")
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String url = request.getParameter("url");
		String keyword = request.getParameter("keyword");
		URL pagina = new URL (url);
		String host = pagina.getHost();
		String nextPage = "/product.jsp";
		String html = "<html><head></head><body><h1>hello world!</h1></body></html>";
		Document doc = Jsoup.parse(html);
		doc.getElementsByTag("head").append("<script type=\"text/javascript\" src=\"jquery.js\"/><script type=\"text/javascript\" src=\"jquery.dom-outline-1.0.js\"/><script type=\"text/javascript\" src=\"app.js\"/>");
		HttpSession session = request.getSession();
		session.setAttribute("host", doc.toString());
		nextPage = response.encodeURL(nextPage);
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return; 
	}
}
