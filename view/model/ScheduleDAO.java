package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import view.tab.ScheduleViewTab;
import view.model.rec.ScheduleVO;

public class ScheduleDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ScheduleDAO() throws Exception {

		connectDB();
	}

	void connectDB() throws Exception {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	//스케줄명 등록
	public void scheduleInsert(ScheduleVO vo, int num) throws Exception {
		System.out.println("등록다오");
		String sql = "update work_schedule_tb set schedule_name = ? where work_schedule_no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getSname());
		ps.setInt(2, num);		
		ps.executeUpdate();
		ps.close();

	}

	//스케줄명 수정
	public void schedulModify(ScheduleVO vo, int num) throws Exception {

		String sql = "update work_schedule_tb set schedule_name = ? where work_schedule_no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getSname());
		ps.setInt(2, num);
		ps.executeUpdate();
		ps.close();
	}
	
	// 스케줄명 삭제
	public void schedulDelete(int num) throws Exception {
		String sql = "delete from work_schedule_tb where work_schedule_no = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		ps.executeUpdate();
		ps.close();
	}
	
	public ArrayList scheduleSearch(int sel, String text) throws Exception {

		String[] selCol = {"WORK_SCHEDULE_NO","schedule_name"};
		String sql = "SELECT work_schedule_no, schedule_name, actual_start_date, actual_end_date, work_no FROM work_schedule_tb WHERE "
				+ selCol[sel] + " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("work_schedule_no"));
			temp.add(rs.getString("schedule_name"));
			temp.add(rs.getString("actual_start_date"));
			temp.add(rs.getString("actual_end_date"));
			temp.add(rs.getInt("work_no"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}
	
	public ScheduleVO findByNum(int snum) throws Exception {
		ScheduleVO vo = new ScheduleVO();
		stmt = con.createStatement();
		String sql = "select * from work_schedule_tb where work_schedule_no = " + snum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setSchedule_no(rs.getInt("work_schedule_no"));
			vo.setSname(rs.getString("schedule_name"));
			vo.setAsDate(rs.getString("actual_start_date"));
			vo.setAeDate(rs.getString("actual_end_date"));
			vo.setPsDate(rs.getString("plan_start_date"));
			vo.setPeDate(rs.getString("plan_end_date"));
			vo.setWork_no(rs.getInt("work_no"));
		}
		rs.close();
		stmt.close();
		return vo;
	}

}
