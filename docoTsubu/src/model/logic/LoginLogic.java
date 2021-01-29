package model.logic;

import java.sql.SQLException;

import dao.UserDAO;
import model.entity.User;

public class LoginLogic {
	public User execute (User user, String jdbcUrl, String dbUser, String dbPass) throws SQLException {

		UserDAO dao = new UserDAO();
		User loginUser = dao.findUser(user, jdbcUrl, dbUser, dbPass);
		return loginUser;
		//name、passともに英文字と数字で３文字以上
//		if (user.getName().matches("[A-Za-z0-9]{3,}") && user.getPass().matches("[A-Za-z0-9]{3,}")) {
//			return true;
//		}
	}
}
