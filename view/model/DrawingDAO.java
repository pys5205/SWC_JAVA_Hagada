package view.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.model.rec.DrawingVO;

public class DrawingDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public DrawingDAO() throws Exception {
		connectDB();
	}
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void DrawingInsert(DrawingVO vo) throws SQLException, Exception {

		String drawing_version = vo.getDrawing_version();
		String drawing_indate = vo.getDrawing_indate();
		String drawing_image = vo.getDrawing_image();
		
		String sql = "insert into drawing_tb(drawing_no, drawing_version, drawing_type, drawing_indate, drawing_image, area_no) values (drawing_num.nextval, ?, ?, sysdate, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getDrawing_version());
		ps.setString(2, vo.getDrawing_type());
		ps.setString(3, vo.getDrawing_image());
		ps.setInt(4, vo.getArea_no());
		
		ps.executeUpdate();
		ps.close();
	}
	
}
