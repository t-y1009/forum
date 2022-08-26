package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;
import model.User;

public class MutterDAO {
	private final String JDBC_URL="jdbc:postgresql://34.168.81.118:5432/forum";
	private final String DB_USER ="postgres";
	private final String DB_PASS ="second";
	
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS))	{
			String sql = "SELECT ID,NAME,TEXT,DATE,(SELECT count(*) FROM favorite WHERE mutter_id = mutter.id)AS FAVORITE "
						+ "FROM mutter ORDER BY ID DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String user_name = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				String favorite = rs.getString("FAVORITE");
				Mutter mutter = new Mutter(id, user_name, text, date, favorite);
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
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mutter.getUserName());
			ps.setString(2, mutter.getText());
			//更新の可否で分岐
			int result =ps.executeUpdate();
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
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mutter.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public List<Mutter> serch(Mutter mutter) {
		List<Mutter> mutterList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE,(SELECT count(*) FROM favorite WHERE mutter_id = mutter.id)AS FAVORITE "
						+ "FROM mutter WHERE NAME = ? OR TEXT = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mutter.getUserName());
			ps.setString(2, mutter.getText());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				String favorite = rs.getString("FAVORITE");
				mutter = new Mutter(id, userName, text, date,favorite);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
			return mutterList;
	}
	
	public List<Mutter> myFavorite(User loginUser) {
		List<Mutter> mutterList = new ArrayList<>();
		Mutter mutter;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, TEXT, DATE, (SELECT count(*) FROM favorite WHERE mutter_id = mutter.id)AS FAVORITE FROM mutter WHERE ID IN (select mutter_id from favorite where user_id = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginUser.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				String favorite = rs.getString("FAVORITE");
				mutter = new Mutter(id, userName, text, date,favorite);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
			return mutterList;
	}
}