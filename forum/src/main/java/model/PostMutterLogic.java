package model;

import java.util.List;

import dao.MutterDAO;

public class PostMutterLogic {
	
	public List<Mutter> serchExcecute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.serch(mutter);
		return mutterList;
	}
	public void incertExecute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
	}
	
	public void deleteExcecute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.delete(mutter);
	}
}
