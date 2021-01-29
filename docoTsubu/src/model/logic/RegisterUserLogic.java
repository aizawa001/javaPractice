package model.logic;

import dao.UserDAO;
import model.entity.User;

public class RegisterUserLogic {
	public boolean execute (User user, String jdbcUrl, String dbUser, String dbPass) {
		UserDAO dao = new UserDAO();
		if (dao.createUser(user, jdbcUrl, dbUser, dbPass) == 1) {
			return true;
		}  else {
			return false;
		}
	}
}
