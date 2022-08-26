package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String text;
	private String date;
	private String favorite;
	
	public Mutter() {}
	public Mutter(int id) {
		this.id = id;
	}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	
	public Mutter(int id, String userName, String text, String date, String favorite) {
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.date = date;
		this.favorite = favorite;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
	public int getId() {
		return this.id;
	}
	public String getDate() {
		return date;
	}
	public String getFavorite() {
		return favorite;
	}
}
