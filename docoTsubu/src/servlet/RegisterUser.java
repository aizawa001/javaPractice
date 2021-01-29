package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.User;
import model.logic.RegisterUserLogic;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String para = request.getParameter("para");

		if (para.equals("0")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			User registerUser = new User(id,name,pass);
			request.setAttribute("registerUser", registerUser);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
			dispatcher.forward(request, response);

		} else if (para.equals("1")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			User registerUser = new User(id,name,pass);

			ServletContext application = getServletContext();
			String jdbcUrl = (String) application.getAttribute("jdbcUrl");
			String dbUser = (String) application.getAttribute("dbUser");
			String dbPass = (String) application.getAttribute("dbPass");

			RegisterUserLogic registerUserLogic = new RegisterUserLogic();
			boolean flag = registerUserLogic.execute(registerUser, jdbcUrl, dbUser, dbPass);
			request.setAttribute("flag", flag);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerResult.jsp");
			dispatcher.forward(request, response);

		}
	}

}
