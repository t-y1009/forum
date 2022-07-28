package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private String id;
	private String userName;
	private String text;
	private String date;
	
	public Mutter() {}
	//削除時のコンストラクタ
	public Mutter(String id) {
		this.id = id;
	}
	//追加
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}
	
	public Mutter(String id, String userName, String text, String date) {
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
	public String getId() {
		return this.id;
	}
	public String getDate() {
		return date;
	}
}
