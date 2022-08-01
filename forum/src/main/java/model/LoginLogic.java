package model;

import dao.AccountDAO;

public class LoginLogic {
	public User excute(User user) {
		AccountDAO dao = new AccountDAO();
		user = dao.loginCheck(user);
		if (user.getUserId() != null && user.getPassword() != null && user.getName() !=null) {
			return user;
		}else {
			return null;
		}
		
	}
	
	public boolean registerCheck(User user) {
		AccountDAO dao = new AccountDAO();
		return dao.registerCheck(user); 
	}
}
