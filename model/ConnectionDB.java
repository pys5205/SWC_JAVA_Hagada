package model;
import java.sql.*;

public class ConnectionDB {
	
	//private String jdbc_url = "jdbc:oracle:thin:@192.168.0.171:1521:pys";
	private String jdbc_url = "jdbc:oracle:thin:@localhost:1521:pys";
	private String db_id ="hagada";
	private String db_pwd  ="pass";
	private String db_driver = "oracle.jdbc.driver.OracleDriver";

	public String getJdbc_url() {
		return jdbc_url;
	}
	public String getDb_id() {
		return db_id;
	}
	public String getDb_pwd() {
		return db_pwd;
	}
	public String getDriver() {
		return db_driver;
	}
	
	public ConnectionDB() {
		// TODO Auto-generated constructor stub
		Connection con = null;
		String url = jdbc_url;
		String driver = db_driver;
		String id = db_id;
		String pwd = db_pwd;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("성공적으로 로딩되었음");
		} 
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("해당 드라이버를 찾을 수 없습니다.1");
		}
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println("해당 드라이버를 찾을 수 없습니다.2");
		}

	}

}
