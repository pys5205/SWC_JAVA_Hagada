package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.model.rec.WorkVO;

public class WorkCompleteDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	// String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public WorkCompleteDAO() throws Exception {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public ArrayList allWorkView()throws Exception{
		
		String sql = "select work.work_no workNo, work.work_name workName, team.team_name teamName, "
				+ "to_char(sche.actual_start_date, 'YYYY/MM/DD') sDate, "
				+ "to_char(sche.actual_end_date, 'YYYY/MM/DD') eDate, detail.work_detail_state state "
				+ "from work_tb work, team_tb team, work_schedule_tb sche, work_detail_tb detail "
				+ "where work.work_no = sche.work_no "
				+ "and work.team_no = team.team_no "
				+ "and work.work_no = detail.work_no "
				+ "and detail.work_detail_state = '완료'";
		
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("workNo"));
			temp.add(rs.getString("workName"));
			temp.add(rs.getString("teamName"));
			temp.add(rs.getString("sDate"));
			temp.add(rs.getString("eDate"));
			temp.add(rs.getString("state"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;

	}
	public ArrayList workSearch(int sel, String text) throws Exception {
		String[] selCol = {"work_name"};
		String sql = "select w.work_no 작업번호, work_name, team_name, to_char(s.actual_start_date, 'YYYY/MM/DD') sDate, to_char(s.actual_end_date, 'YYYY/MM/DD') eDate, WORK_DETAIL_STATE "
				+ "FROM work_tb w, work_schedule_tb s, team_tb t, WORK_DETAIL_TB d "
				+ "where w.team_no = t.team_no "
				+ "and w.work_no = d.work_no "
				+ "and w.work_no = s.work_no "
				+ "and "+selCol[sel]+" like '%"+text+"%'"
				+ " and WORK_DETAIL_STATE = '완료'";
		
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		ArrayList list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("작업번호"));
			temp.add(rs.getString("work_name"));
			temp.add(rs.getString("team_name"));
			temp.add(rs.getString("sDate"));
			temp.add(rs.getString("eDate"));
			temp.add(rs.getString("WORK_DETAIL_STATE"));
			
			list.add(temp);
		}
		rs.close();
		stmt.close();
		
		return list;
	}
	
	public String memoPrint(int data) throws Exception{
		String memo="";
		String sql = "select memo from work_detail_tb where work_no = " + data;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()) {
			memo = rs.getString("memo");
		}
		
		return memo;
	}

}
