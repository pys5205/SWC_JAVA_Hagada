package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChartDao {
	public ChartDao() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public ArrayList<Integer> getCount() throws Exception{
		
		String plansql = "select count(*) Count from work_tb work, work_detail_tb detail where work.work_no = detail.work_no and detail.work_detail_state = '대기'";
		String progsql = "select count(*) Count from work_tb work, work_detail_tb detail where work.work_no = detail.work_no and detail.work_detail_state = '진행'";
		String compsql	="select count(*) Count from work_tb work, work_detail_tb detail where work.work_no = detail.work_no and detail.work_detail_state = '완료'";
		String delsql = "select count(*) Count from work_tb work, work_detail_tb detail where work.work_no = detail.work_no and detail.work_detail_state = '삭제'";

		
		stmt = con.createStatement();
		rs = stmt.executeQuery(plansql);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(rs.next()) {
			list.add(rs.getInt("Count"));
		}
		rs = stmt.executeQuery(progsql);
		if(rs.next()) {
			list.add(rs.getInt("Count"));
		}
		rs = stmt.executeQuery(compsql);
		if(rs.next()) {
			list.add(rs.getInt("Count"));
		}
		rs = stmt.executeQuery(delsql);
		if(rs.next()) {
			list.add(rs.getInt("Count"));
		}
		rs.close();
		stmt.close();
		
		return list;
		
	}
}
