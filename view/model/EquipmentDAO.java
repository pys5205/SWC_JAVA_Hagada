package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import view.model.rec.*;

public class EquipmentDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;

	public EquipmentDAO() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public void equipInsert(EquipmentVO vo) throws Exception {
		String name = vo.getName();
		int money = vo.getMoney();
		String company = vo.getCompany();
		String check = vo.getCheck();
		System.out.println(name);
		String sql = "insert into equipment_tb(equipment_no, EQUIP_NAME, EQUIP_PER_HOUR, EQUIP_COMPANY_NO, EQUIP_INPUT_CHECK)"
				+ " values(equipment_num.nextVal, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);

		ps.setString(1, name);
		ps.setInt(2, money);
		ps.setString(3, company);
		ps.setString(4, check);

		ps.executeUpdate();
		ps.close();
	}

	public ArrayList equipSearch(int sel, String text) throws Exception {
		String[] selCol = { "EQUIP_NAME", "EQUIP_COMPANY_NO" };
		String sql = "select EQUIPMENT_NO, EQUIP_NAME, EQUIP_PER_HOUR, c.EQUIP_COMPANY_NO 번호, COM_NAME, EQUIP_INPUT_CHECK "
				+ "from EQUIPMENT_TB e, EQUIP_COMPANY_TB c " + "where e.EQUIP_COMPANY_NO = c.EQUIP_COMPANY_NO " + "and "
				+ selCol[sel] + " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("EQUIPMENT_NO"));
			temp.add(rs.getString("EQUIP_NAME"));
			temp.add(rs.getInt("EQUIP_PER_HOUR"));
			temp.add(rs.getInt("번호"));
			temp.add(rs.getString("COM_NAME"));
			temp.add(rs.getString("EQUIP_INPUT_CHECK"));

			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public void equipDelete(String name) throws Exception {
		String sql = "delete equipment_tb where equip_name = ?";

		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.executeUpdate();
		ps.close();
	}

	public void equipUpdate(EquipmentVO vo,String name) throws Exception {
		String rename = vo.getName();
		String company = vo.getCompany();
		String check = vo.getCheck();
		int money = vo.getMoney();
		
		String sql = "update equipment_tb set equip_name = ?, equip_input_check = ?, equip_per_hour =? "
				+ "where equip_name = ?";
		ps = con.prepareStatement(sql);
		
		ps.setString(1, rename);
		ps.setString(2, check);
		ps.setInt(3, money);
		ps.setString(4, name);
		
		ps.executeUpdate();
		ps.close();
	}
	
	public ArrayList totalEquip(String name, int money) throws Exception {
		stmt = con.createStatement();
		String sql = "select equip_name, WORK_EQUIP_START_DATE, WORK_EQUIP_END_DATE, WORK_EQUIP_USE_TIME, EQUIP_PER_HOUR, WORK_EQUIP_USE_TIME*EQUIP_PER_HOUR "
				+ "from equipment_tb e, work_equip_tb we "
				+ "where e.EQUIPMENT_NO = we.EQUIPMENT_NO "
				+ "and e.equip_name like '%"+name+"%'";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(name);
			temp.add(rs.getDate("WORK_EQUIP_START_DATE"));
			temp.add(rs.getDate("WORK_EQUIP_END_DATE"));
			temp.add(rs.getInt("WORK_EQUIP_USE_TIME"));
			temp.add(rs.getInt("EQUIP_PER_HOUR"));
			temp.add(rs.getInt("WORK_EQUIP_USE_TIME*EQUIP_PER_HOUR"));
			
			list.add(temp);
		}
		System.out.println(list);
		rs.close();
		stmt.close();
		
		return list;
	}

	public EquipmentVO findByNum(int vNum) throws Exception {
		EquipmentVO vo = new EquipmentVO();
		stmt = con.createStatement();
		String sql = "select * from equipment_tb where equipment_no = " + vNum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setName(rs.getString("EQUIP_NAME"));
			vo.setMoney(rs.getInt("EQUIP_PER_HOUR"));
			vo.setCheck(rs.getString("EQUIP_INPUT_CHECK"));
			vo.setCompany(rs.getString("EQUIP_COMPANY_NO"));
		}

		rs.close();
		stmt.close();
		return vo;
	}
}
