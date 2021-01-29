package model.logic;

import java.util.List;

import dao.MutterDAO;
import model.entity.Mutter;

public class GetMutterListLogic {
	public List<Mutter> execute(String jdbcUrl, String dbUser, String dbPass) {
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll(jdbcUrl, dbUser, dbPass);
		return mutterList;
	}
}
