package model;

import dao.MutterDAO;

public class PostMutterLogic {
	public void execute (Mutter mutter, String jdbcUrl, String dbUser, String dbPass) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter, jdbcUrl, dbUser, dbPass);
	}
}
