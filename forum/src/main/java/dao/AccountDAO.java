package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:postgresql://database-1.cphupkzcqpdm.us-west-2.rds.amazonaws.com:5432/forum_db";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "cJQHmvzrjP9mb5JZKbiZ";
	
	public boolean registerCheck(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//IDもしくは名前が重複している場合は登録失敗
			String sql1 = "SELECT USER_ID, NAME FROM account_list WHERE USER_ID = ? OR NAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql1);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getName());
			ResultSet rs = pStmt.executeQuery();
			//レコードの有無で分岐
			if(rs.isBeforeFirst()) {
				return false;
			}else {
				String sql2 = "INSERT INTO account_list (USER_ID, PASSWORD, NAME, ACCOUNT_TYPE) VALUES (?,?,?,?)";
				pStmt = conn.prepareStatement(sql2);
					pStmt.setString(1, user.getUserId());
					pStmt.setString(2, user.getPassword());
					pStmt.setString(3, user.getName());
					pStmt.setInt(4, user.getAccountType());
					pStmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public User loginCheck(User user) {
		User returnUser = user;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT USER_ID, PASSWORD, NAME, ACCOUNT_TYPE FROM account_list WHERE USER_ID = ? AND PASSWORD = ?";	
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPassword());
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				returnUser.setUserId(rs.getString("USER_ID"));
				returnUser.setPassword(rs.getString("PASSWORD"));
				returnUser.setName(rs.getString("NAME"));
				returnUser.setAccountType(rs.getInt("ACCOUNT_TYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return returnUser;
	}
}
