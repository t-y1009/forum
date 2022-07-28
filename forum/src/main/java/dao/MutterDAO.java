package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	private final String JDBC_URL = "jdbc:postgresql://database-1.cphupkzcqpdm.us-west-2.rds.amazonaws.com:5432/forum_db";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "cJQHmvzrjP9mb5JZKbiZ";
	
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS))	{
			String sql = "SELECT ID,NAME,TEXT,DATE FROM mutter ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String user_name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				Mutter mutter = new Mutter(id, user_name, text, date);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	public boolean create(Mutter mutter) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO mutter(NAME, TEXT, DATE) VALUES(?, ?, now())";	
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			//更新の可否で分岐
			int result =pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(Mutter mutter) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "DELETE FROM mutter WHERE id = ?";	
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, Integer.parseInt(mutter.getId()));
			pStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Mutter> serch(Mutter mutter) {
		List<Mutter> mutterList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE FROM mutter WHERE NAME = ? OR TEXT = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				mutter = new Mutter(id, userName, text, date);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
			return mutterList;
	}
}