package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String user_Id;
	private String password;
	private String name;
	private int accountType;
	
	public User() {}
	public User(String userId, String password) {
		this.user_Id = userId;
		this.password = password;
	}
	public User(String userId, String password, String name, int accountType) {
		this(userId, password);
		this.name = name;
		this.accountType = accountType;
	}
	public int getId() {
		return id;
	}
	public String getUserId() {
		return user_Id;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(String userId) {
		this.user_Id = userId;
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
