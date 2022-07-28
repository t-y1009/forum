package model;

import java.io.Serializable;

public class User implements Serializable {
	private String userId;
	private String password;
	private String name;
	private int accountType;
	
	public User() {}
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	public User(String userId, String password, String name, int accountType) {
		this(userId, password);
		this.name = name;
		this.accountType = accountType;
	}
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
}
