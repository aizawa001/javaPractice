package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {



	public List<Mutter> findAll (String jdbcUrl, String dbUser, String dbPass) {
		List<Mutter> mutterList = new ArrayList<>();

		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql =
					"SELECT ID,NAME,TEXT FROM MUTTER ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id,userName,text);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	public boolean create (Mutter mutter, String jdbcUrl, String dbUser, String dbPass) {

		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql = "INSERT INTO MUTTER(NAME,TEXT) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());

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

	public Mutter findEach (int mutterId, String jdbcUrl, String dbUser, String dbPass) {
		Mutter mutter = null;

		try(Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql =
					"SELECT * FROM MUTTER WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,mutterId);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				mutter = new Mutter(id, name, text);
			} else {
				//このメッセージを表示させたい。
				String errorMsg = "指定のつぶやきは存在しません。";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutter;
	}
}
