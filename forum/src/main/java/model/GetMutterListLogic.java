package model;
import java.util.List;

import dao.MutterDAO;
public class GetMutterListLogic {
	public List<Mutter> mutterListExecute(){
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findAll();
		return mutterList;
	}
	
	public List<Mutter> favoriteSerchExecute(User loginUser){
		MutterDAO dao = new MutterDAO();
		return dao.myFavorite(loginUser);
		
	}
}
