package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.User;

public class UserDAO {

	public User findUser (User user, String jdbcUrl, String dbUser, String dbPass) throws SQLException {
		User loginUser = null;
		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql =
					"SELECT * FROM USERS WHERE ID=? AND PASS=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setString(2, user.getPass());
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				loginUser = new User(id, name, pass);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		}
		return loginUser;
	}

	public int createUser (User user, String jdbcUrl, String dbUser, String dbPass) {
		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql = "INSERT INTO USERS(ID,NAME,PASS) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getPass());
			//更新されていたら１を返す
			int result = pStmt.executeUpdate();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
