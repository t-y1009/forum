package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Mutter;
import model.User;

public class LikeDAO {
	private final String JDBC_URL="jdbc:postgresql://34.168.81.118:5432/forum";
	private final String DB_USER ="postgres";
	private final String DB_PASS ="second";
	
	public boolean checkLike(User loginUser, Mutter mutter) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS))	{
			String sql = "SELECT COUNT(*) FROM favorite WHERE USER_ID = ? AND MUTTER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginUser.getId());
			ps.setInt(2, mutter.getId());
			ResultSet rs = ps.executeQuery();
			String like = null;
			while (rs.next()) {
				like = rs.getString("COUNT");
			}
			if (like.equals("0")) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void addLike(User loginUser, Mutter mutter) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS))	{
			String sql = "INSERT INTO favorite (USER_ID, MUTTER_ID) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, loginUser.getId());
			ps.setInt(2, mutter.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteLike(User loginUser, Mutter mutter) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS))	{
			String sql = "DELETE FROM favorite WHERE USER_ID = ? AND MUTTER_ID = ?";
			PreparedStatement ps  = conn.prepareStatement(sql);
			ps.setInt(1, loginUser.getId());
			ps.setInt(2, mutter.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
