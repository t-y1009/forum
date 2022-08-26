package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/docotsubu";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "";
	
	public List<Employee> findAll(){
		List<Employee> empList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "SELECT ID, NAME, AGE FROM EMPLOYEE";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				Employee employee = new Employee(id, name, age);
				empList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empList;
	}
}
