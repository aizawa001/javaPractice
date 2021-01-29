package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.User;
import model.logic.LoginLogic;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		User user = new User(id, pass);
		LoginLogic loginLogic = new LoginLogic();
		ServletContext application = getServletContext();
		String jdbcUrl = (String) application.getAttribute("jdbcUrl");
		String dbUser = (String) application.getAttribute("dbUser");
		String dbPass = (String) application.getAttribute("dbPass");
		User loginUser = null;
		try {
			loginUser = loginLogic.execute(user, jdbcUrl, dbUser, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		HttpSession session = request.getSession();
		if (loginUser != null) {
			session.setAttribute("loginUser",loginUser);
		} else {
			String errorMsg = "指定のユーザーが見つかりませんでした。";
			session.setAttribute("errorMsg", errorMsg);
		}


		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);


	}

}
