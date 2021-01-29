package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Mutter;
import model.logic.GetMutterLogic;

@WebServlet("/MutterDetail")
public class MutterDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int mutterId = Integer.parseInt(id);
		GetMutterLogic getMutterLogic = new GetMutterLogic();

		ServletContext application = getServletContext();
		String jdbcUrl = (String) application.getAttribute("jdbcUrl");
		String dbUser = (String) application.getAttribute("dbUser");
		String dbPass = (String) application.getAttribute("dbPass");

		Mutter mutter = getMutterLogic.execute(mutterId, jdbcUrl, dbUser, dbPass);

		if (mutter != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mutter",mutter);
		}


		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mutterDetail.jsp");
		dispatcher.forward(request, response);

	}

}
