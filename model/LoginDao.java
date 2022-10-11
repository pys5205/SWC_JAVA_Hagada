package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.rec.LoginVo;

public class LoginDao {
	public LoginDao() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}

	// ###########################################################
	// DB control method
	Connection con;
	//String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
//	String url = "jdbc:oracle:thin:@192.168.219.107:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;

	void connectDB() throws Exception {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public boolean login(String id, String pass) throws Exception {

		boolean x = false;
		String SQL = "SELECT emp_pw FROM employee_tb WHERE emp_id = ?"; 
		ps = con.prepareStatement(SQL);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String passs = rs.getString("emp_pw");
			if (pass.equals(passs)) {
				x = true;
			} else {
				x = false;
			}
		}
		return x;
	}

}
