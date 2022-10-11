package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.model.rec.CompanyVO;

public class CompanyDAO { // 중장비 거래처
	public CompanyDAO() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}

	// ###########################################################
	// DB control method
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;

	CompanyVO vo = new CompanyVO();

	void connectDB() throws Exception {
		/*
		 * 1. 드라이버를 드라이버매니저에 등록 2. 연결 객체 얻어오기
		 */
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public void bComInsert(CompanyVO vo) throws SQLException { // 중장비 정보 등록
		String name = vo.getCom_name();
		String tel = vo.getCom_tel();
		String mgrname = vo.getCom_mgr_name();
		String mgrtel = vo.getCom_mgr_tel();
		String addr = vo.getCom_addr();

		String sql = "INSERT into equip_company_tb(equip_company_no, com_name, com_tel, com_mgr_name, com_mgr_tel, com_addr)"
				+ "values (eqcom_num.nextval, ?, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, tel);
		ps.setString(3, mgrname);
		ps.setString(4, mgrtel);
		ps.setString(5, addr);

		ps.executeUpdate();
		ps.close();
	}

	public void bComUpdate(CompanyVO vo, int num) throws Exception { // 중장비 정보 수정

		String sql = "UPDATE equip_company_tb set com_name = ?, com_tel = ?, com_mgr_name = ?, com_mgr_tel =?, com_addr =?"
				+ " where equip_company_no = ?";
		ps = con.prepareStatement(sql);

		ps.setString(1, vo.getCom_name());
		ps.setString(2, vo.getCom_tel());
		ps.setString(3, vo.getCom_mgr_name());
		ps.setString(4, vo.getCom_mgr_tel());
		ps.setString(5, vo.getCom_addr());
		ps.setInt(6, num);

		ps.executeUpdate();
		ps.close();
	}

	public void bComDelete(int num) throws Exception { // 중장비 정보 삭제
		String sql = "DELETE equip_company_tb where equip_company_no = ?";

		ps = con.prepareStatement(sql);

		ps.setInt(1, num);
		ps.executeUpdate();
		ps.close();

	}

	public ArrayList bComSearch(int sel, String text) throws Exception {
		String[] selCol = { "com_name", "com_mgr_name" };
		String sql = "select equip_company_no, com_name, com_tel, com_mgr_name, com_mgr_tel, com_addr "
				+ "from equip_company_tb " + "where " + selCol[sel] + " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("equip_company_no"));
			temp.add(rs.getString("com_name"));
			temp.add(rs.getString("com_tel"));
			temp.add(rs.getString("com_mgr_name"));
			temp.add(rs.getString("com_mgr_tel"));
			temp.add(rs.getString("com_addr"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public CompanyVO findByNum(int vNum) throws Exception {

		CompanyVO vo = new CompanyVO();
		stmt = con.createStatement();
		String sql = "SELECT * FROM equip_company_tb WHERE equip_company_no=" + vNum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setEquip_company_no(rs.getInt("equip_company_no"));
			vo.setCom_name(rs.getString("com_name"));
			vo.setCom_tel(rs.getString("com_tel"));
			vo.setCom_mgr_name(rs.getString("com_mgr_name"));
			vo.setCom_mgr_tel(rs.getString("com_mgr_tel"));
			vo.setCom_addr(rs.getString("com_addr"));

		}
		rs.close();
		stmt.close();

		return vo;
	}
}