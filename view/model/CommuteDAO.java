package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.rec.LoginVo;
import view.model.rec.CommuteVO;
import view.model.rec.WorkerVo;

public class CommuteDAO {

	Connection con;
	// String url = "jdbc:oracle:thin:@localhost:1521:kibwa1";
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	LoginVo vo = new LoginVo();

	public CommuteDAO() throws Exception {
		/*
		 * ============================================ 생성자 함수 - DB 연결 1. 드라이버를 로딩 2.
		 * Connection 얻어오기
		 */
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	// 작업자 검색
	public ArrayList CommuteSearch(int sel, String text) throws Exception {
		String[] selCol = { "WORKER_NAME", "CMT_INTIME" };
		String sql = "select EMPLOYEE_NO,commute_no, w.WORKER_NO 번호, WORKER_NAME, WORKER_MAJOR, CMT_INTIME, CMT_OUTTIME "
				+ " from WORKER_TB w, COMMUTE_TB c " + " where w.WORKER_NO = c.WORKER_NO " + " and " + selCol[sel]
				+ " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("EMPLOYEE_NO"));
			temp.add(rs.getString("commute_no"));
			temp.add(rs.getString("번호"));
			temp.add(rs.getString("WORKER_NAME"));
			temp.add(rs.getString("WORKER_MAJOR"));
			temp.add(rs.getString("CMT_INTIME"));
			temp.add(rs.getString("CMT_OUTTIME"));

			list.add(temp);
		}
		rs.close();
		stmt.close();

		return list;
	}

	// 출근 등록
	public void Intime(int workerno) throws SQLException {

		// String name, String intime, String outtime, String major, String date, int
		// empno,ㄴ int workerno
		// 작업자번호, 이름, 출퇴근시간, 관리자번호, 이름 전문분야, 날짜
		String sql = "insert into commute_tb(commute_no, cmt_intime, worker_no) values(commute_no.nextval,to_char(sysdate, 'YYYY-MM-DD HH24'),?)";
		ps = con.prepareStatement(sql);
		ps.setInt(1, workerno);
		// 숫자는 몇번째자리인지

		ps.executeUpdate();
		ps.close();
	}

	// 퇴근 등록
	public void Outtime(int workerno) throws SQLException {

		String sql = "update commute_tb set cmt_outtime = to_char(sysdate, 'YYYY-MM-DD HH24') "
				+ " where worker_no = ? " + "and substr(cmt_intime, 0,10) = to_char(sysdate, 'YYYY-MM-DD')";
		ps = con.prepareStatement(sql);
		ps.setInt(1, workerno);
		// 숫자는 몇번째자리인지

		ps.executeUpdate();
		ps.close();
	}

	public void commuteUpdate(int workerno, String intime, String outtime) throws Exception {
		String sql = "update commute_tb set cmt_intime = ?, cmt_outtime = ? "
				+ " where worker_no = ?"
				+ " and substr(cmt_intime, 0,10) = to_char(sysdate, 'YYYY-MM-DD')";

		ps = con.prepareStatement(sql);
		ps.setString(1, intime);
		ps.setString(2, outtime);
		ps.setInt(3, workerno);
		
		ps.executeUpdate();
		ps.close();
	}

	public CommuteVO findByNum(String num) throws Exception {
		// TODO Auto-generated method stub
		CommuteVO vo = new CommuteVO();
		stmt = con.createStatement();
		String sql = "select w.worker_no, worker_name, cmt_intime, cmt_outtime " + " from worker_tb w, commute_tb c "
				+ " where commute_no =" + num + " and w.worker_no = c.worker_no";
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setWorkerno(rs.getInt("worker_no"));
			vo.setName(rs.getString("worker_name"));
			vo.setIntime(rs.getString("cmt_intime"));
			vo.setOuttime(rs.getString("cmt_outtime"));
		}
		rs.close();
		stmt.close();

		return vo;
	}

}
