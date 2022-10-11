package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.LoginVo;
import view.model.rec.WorkerVo;

public class WorkerDao {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	// String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	LoginVo vo = new LoginVo();

	public WorkerDao() throws Exception {
		/*
		 * ============================================ 생성자 함수 - DB 연결 1. 드라이버를 로딩 2.
		 * Connection 얻어오기
		 */
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public void WorkerInsert(WorkerVo vo) throws SQLException {
		String name = vo.getName();
		String jumin = vo.getJumin();
		String tel = vo.getTel();
		String major = vo.getMajor();
		int career = vo.getCareer();
		String safe = vo.getSafe();
		String acc = vo.getAcc();
		int per_hour = vo.getPer_hour();
		int empno = vo.getEmpno();
		// name, jumin, tel, safe, emp, major, per, acc, career
		String sql = "insert into WORKER_TB(worker_no, worker_name, worker_ssn, worker_tel, worker_safe_check, employee_no, worker_major, worker_per_hour, worker_acc_num, worker_career)"
				+ "values(worker_num.nextval,?,?,?,?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ps.setString(2, jumin);
		ps.setString(3, tel);
		ps.setString(4, safe);
		ps.setInt(5, empno);
		ps.setString(6, major);
		ps.setInt(7, per_hour);
		ps.setString(8, acc);
		ps.setInt(9, career);

		ps.executeUpdate();
		ps.close();
	}

	public void workerUpdate(WorkerVo vo, int num) throws Exception {
		String name = vo.getName();
		String jumin = vo.getJumin();
		String tel = vo.getTel();
		String major = vo.getMajor();
		int career = vo.getCareer();
		String safe = vo.getSafe();
		String acc = vo.getAcc();
		int per_hour = vo.getPer_hour();
		int empno = vo.getEmpno();

		String sql = "update worker_tb set worker_name = ?, worker_ssn = ?, worker_tel = ?, worker_major = ?,worker_career = ?, worker_safe_check = ?, worker_acc_num = ?, worker_per_hour = ?"
				+ " where worker_no = ? ";
		ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ps.setString(2, jumin);
		ps.setString(3, tel);
		ps.setString(4, major);
		ps.setInt(5, career);
		ps.setString(6, safe);
		ps.setString(7, acc);
		ps.setInt(8, per_hour);
		ps.setInt(9, num);

		ps.executeUpdate();
		ps.close();
	}

	public void workerDelete(int num) throws Exception {
		String sql = "delete worker_tb where worker_no = ?";

		ps = con.prepareStatement(sql);

		ps.setInt(1, num);

		ps.executeUpdate();
		ps.close();
	}

//	public String empname(Login username) throws Exception {
//		int x;
//		
//		return x;
//	}

	public ArrayList workerSearch(int sel, String text) throws Exception {
		String[] selCol = { "worker_name", "emp_name" };
		String sql = "select w.worker_no, worker_name, worker_ssn, worker_tel, worker_safe_check, worker_major, worker_career, emp_name "
				+ "from worker_tb w, employee_tb e " 
				+ "where w.employee_no = e.employee_no and " + selCol[sel]
				+ " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("worker_no"));
			temp.add(rs.getString("worker_name"));
			temp.add(rs.getString("worker_ssn"));
			temp.add(rs.getString("worker_tel"));
			temp.add(rs.getString("worker_safe_check"));
			temp.add(rs.getString("worker_major"));
			temp.add(rs.getInt("worker_career"));
			temp.add(rs.getString("emp_name"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public String countworkers() throws Exception {
		int x = 0;
		String sql = "select count(Worker_no) from worker_tb";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			x = rs.getInt(1);
		}
		String r = String.valueOf(x);
		rs.close();
		stmt.close();

		return r;
	}

	public WorkerVo findByNum(int vNum) throws Exception {

		WorkerVo vo = new WorkerVo();
		stmt = con.createStatement();
		String sql = "SELECT * FROM worker_tb WHERE worker_no=" + vNum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setWorkerno(rs.getInt("worker_no"));
			vo.setName(rs.getString("worker_name"));
			vo.setJumin(rs.getString("worker_ssn"));
			vo.setTel(rs.getString("worker_tel"));
			vo.setSafe(rs.getString("worker_safe_check"));
			vo.setEmpno(rs.getInt("employee_no"));
			vo.setMajor(rs.getString("worker_major"));
			vo.setPer_hour(rs.getInt("worker_per_hour"));
			vo.setAcc(rs.getString("worker_acc_num"));
			vo.setCareer(rs.getInt("worker_career"));
		}
		rs.close();
		stmt.close();

		return vo;
	}
}
