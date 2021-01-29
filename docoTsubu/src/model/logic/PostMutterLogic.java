package model.logic;

import dao.MutterDAO;
import model.entity.Mutter;

public class PostMutterLogic {
	public void execute (Mutter mutter, String jdbcUrl, String dbUser, String dbPass) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter, jdbcUrl, dbUser, dbPass);
	}
}
