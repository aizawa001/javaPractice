package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterUserLogic;
import model.User;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			User registerUser = new User(name,pass);
			request.setAttribute("registerUser", registerUser);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("done")) {
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			User registerUser = new User(name,pass);

			ServletContext application = getServletContext();
			String jdbcUrl = (String) application.getAttribute("jdbcUrl");
			String dbUser = (String) application.getAttribute("dbUser");
			String dbPass = (String) application.getAttribute("dbPass");

			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			boolean flag;
			flag = registerUserLogic.execute(registerUser, jdbcUrl, dbUser, dbPass);

		}
	}

}
