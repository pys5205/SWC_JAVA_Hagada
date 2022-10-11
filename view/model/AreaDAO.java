package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.model.rec.AreaVO;

public class AreaDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public AreaDAO() throws Exception {
		connectDB();
	}
	
	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void AreaInsert(AreaVO vo) throws SQLException {
		String areaDetail = vo.getArea_detail();
		String areaCheck = vo.getScaff_check();
		String areaAddr = vo.getArea_addr();
		String sql = "insert into area_tb(area_no, area_detail, scaff_check, area_addr) values (area_num.nextval, ?, ?, ?)";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, areaDetail);
		ps.setString(2, areaCheck);
		ps.setString(3, areaAddr);
		
		ps.executeUpdate();
		ps.close();
	}
	
	public void AreaModify(AreaVO vo, int num) throws Exception {
		String sql = "update area_tb set area_detail = ?, scaff_check = ?, area_addr = ? where area_no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getArea_detail());
		ps.setString(2, vo.getScaff_check());
		ps.setString(3, vo.getArea_addr());
		ps.setInt(4, num);
		ps.executeUpdate();
		ps.close();
	}
	
	public void AreaDelete(int num) throws Exception {
		String sql = "delete from area_tb where area_no = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		ps.executeUpdate();
		ps.close();
	}
	
	public ArrayList AreaSearch(int sel, String text) throws Exception {
		String[] selcol = {"area_detail", "scaff_check", "area_addr"};
		String sql = "select * from area_tb where " + selcol[sel] + " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("area_no"));
			temp.add(rs.getString("area_detail"));
			temp.add(rs.getString("scaff_check"));
			temp.add(rs.getString("area_addr"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}
	
	public AreaVO findByNum(int anum) throws Exception {
		AreaVO vo = new AreaVO();
		stmt = con.createStatement();
		String sql = "select * from area_tb where area_no = " + anum;
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			vo.setArea_no(rs.getInt("area_no"));
			vo.setArea_detail(rs.getString("area_detail"));
			vo.setScaff_check(rs.getString("scaff_check"));
			vo.setArea_addr(rs.getString("area_addr"));
		}
		rs.close();
		stmt.close();
		return vo;
	}
	
}
