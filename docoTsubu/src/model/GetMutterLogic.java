package model;

import dao.MutterDAO;

public class GetMutterLogic {
	public Mutter execute (int id, String jdbcUrl, String dbUser, String dbPass) {
		MutterDAO dao = new MutterDAO();
		Mutter mutter = dao.findEach(id, jdbcUrl, dbUser, dbPass);
		return mutter;
	}
}
