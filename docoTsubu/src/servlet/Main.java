package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		ServletContext application = getServletContext();
		String jdbcUrl = (String) application.getAttribute("jdbcUrl");
		String dbUser = (String) application.getAttribute("dbUser");
		String dbPass = (String) application.getAttribute("dbPass");

		List<Mutter> mutterList = getMutterListLogic.execute(jdbcUrl, dbUser, dbPass);
		request.setAttribute("mutterList", mutterList);

		//ログインの確認のためにUserをセッションスコープから取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("/docoTsubu/");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		ServletContext application = getServletContext();
		String jdbcUrl = (String) application.getAttribute("jdbcUrl");
		String dbUser = (String) application.getAttribute("dbUser");
		String dbPass = (String) application.getAttribute("dbPass");

		//テキストのnullチェックとセッションからmutterList,loginUserの取得、保存
		if (text != null && text.length() != 0) {

			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			Mutter mutter = new Mutter(loginUser.getName(),text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, jdbcUrl, dbUser, dbPass);
		}else {
			//エラーメッセージの保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}

		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute(jdbcUrl, dbUser, dbPass);
		request.setAttribute("mutterList", mutterList);

		//メイン画面へのフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);


	}
}
