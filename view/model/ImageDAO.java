package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImageDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public ImageDAO() throws Exception {
		connectDB();
	}
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public String resSelect(String data) throws Exception{
		String draw = "";
		String sql = "select drawing_image from drawing_tb where area_no = " + data;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()) {
			draw = rs.getString("drawing_image");
		}
		
		rs.close();
		stmt.close();
		return draw;
	}
}
