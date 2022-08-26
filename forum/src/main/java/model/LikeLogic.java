package model;

import dao.LikeDAO;

public class LikeLogic {
	
	public boolean checkExecute(User loginUser, Mutter mutter) {
		LikeDAO dao = new LikeDAO();
		return dao.checkLike(loginUser, mutter);
	}
	
	public void addExecute(User loginUser, Mutter mutter) {
		LikeDAO dao = new LikeDAO();
		dao.addLike(loginUser, mutter);
	}
	
	public void deleteExecute(User loginUser, Mutter mutter) {
		LikeDAO dao = new LikeDAO();
		dao.deleteLike(loginUser, mutter);
	}
}
