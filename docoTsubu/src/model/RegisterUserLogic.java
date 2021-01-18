package model;

import dao.UserDAO;

public class RegisterUserLogic {
	public boolean execute (User user, String jdbcUrl, String dbUser, String dbPass) {
		UserDAO dao = new UserDAO();
		return dao.createUser(user, jdbcUrl, dbUser, dbPass);
	}
}
