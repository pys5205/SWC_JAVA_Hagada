package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.rec.SignUpVo;

public class SignUpDao {
	public SignUpDao() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}

	// ###########################################################
	// DB control method
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	//String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
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

	public void regist(SignUpVo vo) throws Exception {
		/*
		 * 1. sql 작성하기 ( insert 문 작성 : 멤버필드를 변수로 지정하여 ) 2. sql 전송객체 얻어오기 (
		 * PreparedStatement가 더 적합할 듯 ) 3. sql 전송 ( 전송전에 ?로 지정 ) 4. 닫기 ( Connection은 닫으면
		 * 안됨 )
		 */
		String emp_name = vo.getEmp_name();
		System.out.println(emp_name);
		String emp_id = vo.getEmp_id();
		String emp_pw = vo.getEmp_pw();
		String emp_ssn = vo.getEmp_ssn();
		String emp_tel = vo.getEmp_tel();

		String sql = "insert into employee_tb(employee_no, emp_name, emp_id, emp_pw, emp_ssn, emp_tel,emp_job) values(regist_num.nextval,?,?,?,?,?,'가')";
		ps = con.prepareStatement(sql);
		ps.setString(1, emp_name);
		ps.setString(2, emp_id); // ps.setString(1, vo.getName());
		ps.setString(3, emp_pw);
		ps.setString(4, emp_ssn);
		ps.setString(5, emp_tel);

		ps.executeUpdate();
		ps.close();
	}
}
