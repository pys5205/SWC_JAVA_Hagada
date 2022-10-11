package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ManagementDao {
	public ManagementDao() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}
	// ###########################################################
	// DB control method
	Connection con;
//	String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	
	void connectDB() throws Exception {
		/*
		 * 1. 드라이버를 드라이버매니저에 등록 2. 연결 객체 얻어오기
		 */
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
}
