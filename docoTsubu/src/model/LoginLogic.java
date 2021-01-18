package model;

import dao.UserDAO;

public class LoginLogic {
	public User execute (User user, String jdbcUrl, String dbUser, String dbPass) {

		UserDAO dao = new UserDAO();
		User loginUser = dao.findUser(user, jdbcUrl, dbUser, dbPass);
		return loginUser;
		//name、passともに英文字と数字で３文字以上
//		if (user.getName().matches("[A-Za-z0-9]{3,}") && user.getPass().matches("[A-Za-z0-9]{3,}")) {
//			return true;
//		}
	}
}
