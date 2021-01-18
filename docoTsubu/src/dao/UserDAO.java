package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	public User findUser (User user, String jdbcUrl, String dbUser, String dbPass) {
		User loginUser = null;
		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql =
					"SELECT * FROM USER WHERE ID=? AND PASS=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user.getId());
			pStmt.setString(2, user.getPass());
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				loginUser = new User(id, name, pass);
			} else {
				//このメッセージを表示させたい。
				String errorMsg = "指定のユーザーは存在しません。";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return loginUser;
	}

	public boolean createUser (User user, String jdbcUrl, String dbUser, String dbPass) {
		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql = "INSERT INTO USER(NAME,PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
