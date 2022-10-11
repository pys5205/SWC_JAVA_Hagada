package view.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import view.model.rec.*;
import view.tab.MaterialViewTab;

public class MaterialDAO {

	public MaterialDAO() throws Exception {
		connectDB();
	}

	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;

	MaterialVO vo = new MaterialVO();
	MaterialOrderVO vo2 = new MaterialOrderVO();

	void connectDB() throws Exception {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);

	}

	public void MaterialInsert(MaterialVO vo) throws SQLException {
		String name = vo.getMat_Name();
		int price = vo.getMat_Price();
		int count = vo.getMat_count();
		int type = vo.getMaterial_type_no();
		int vender = vo.getVender_no();

		String sql = "INSERT into material_tb(material_no, mat_name, material_type_no, mat_price, mat_count, vender_no ) "
				+ " values (mater_num.nextval, ?, ?, ?, ?, ?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, type);
		ps.setInt(3, price);
		ps.setInt(4, count);
		ps.setInt(5, vender);

		ps.executeUpdate();
		ps.close();
	}

	public void MaterialUpdate(MaterialVO vo, int num) throws Exception {

		String sql = "UPDATE material_tb set mat_name =?, material_type_no = ?, mat_price = ?, mat_count = ?,  vender_no = ? "
				+ " where material_no = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getMat_Name());
		ps.setInt(2, vo.getMaterial_type_no());
		ps.setInt(3, vo.getMat_Price());
		ps.setInt(4, vo.getMat_count());
		ps.setInt(5, vo.getVender_no());
		ps.setInt(6, num);

		ps.executeUpdate();
		ps.close();
	}

	public void MaterialDelete(int num) throws Exception { // 자재 정보 삭제
		String sql = "DELETE material_tb where material_no = ?";

		ps = con.prepareStatement(sql);

		ps.setInt(1, num);
		ps.executeUpdate();
		ps.close();

	}

	public ArrayList MaterialSearch(int sel, String text) throws Exception {
		String[] selCol = { "mat_name" };
		String sql = "select material_no, mat_name,  material_type_no, mat_price, mat_count, vender_no  "
				+ "from material_tb " + "where " + selCol[sel] + " like '%" + text + "%'";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("material_no"));
			temp.add(rs.getString("mat_name"));
			temp.add(rs.getInt("material_type_no"));
			temp.add(rs.getInt("mat_price"));
			temp.add(rs.getInt("mat_count"));
			temp.add(rs.getInt("vender_no"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public void OrderInsert(int num, int count, int result) throws Exception {

		String sql = "insert into order_tb (order_no, material_no, order_date, order_count, order_total_price) "
				+ " values(order_num.nextval, ?, sysdate, ?, ?)";

		ps = con.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setInt(2, count);
		ps.setInt(3, result);

		ps.executeUpdate();
		ps.close();
	}

	public ArrayList OrderSelect(int num, int count, int result) throws Exception {

		String sql = "select order_no, mt.material_no, order_date, order_count, order_total_price "
				+ " from order_tb ot, material_tb mt " + " where mt.material_no = ot.material_no and "
				+ "mt.material_no =  " + num;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("order_no"));
			temp.add(num);
			temp.add(rs.getDate("order_date"));
			temp.add(rs.getInt("order_count"));
			temp.add(rs.getInt("order_total_price"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		System.out.println("주문 성공");
		return list;
	}

	public MaterialVO findByNum(int vNum) throws Exception {

		MaterialVO vo = new MaterialVO();
		stmt = con.createStatement();
		String sql = "SELECT * FROM material_tb WHERE material_no= " + vNum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setMaterial_no(rs.getInt("material_no"));
			vo.setMat_Name(rs.getString("mat_name"));
			vo.setMaterial_type_no(rs.getInt("material_type_no"));
			vo.setMat_Price(rs.getInt("mat_price"));
			vo.setMat_count(rs.getInt("mat_count"));
			vo.setVender_no(rs.getInt("vender_no"));

		}
		rs.close();
		stmt.close();

		return vo;
	}

	public MaterialOrderVO findByNum2(int vNum) throws Exception {

		MaterialOrderVO vo = new MaterialOrderVO();
		stmt = con.createStatement();
		String sql = "SELECT * FROM order_tb WHERE order_no= " + vNum;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vo.setOrder_no(rs.getInt("order_no"));
			vo.setMaterial_no(rs.getInt("material_no"));
			vo.setOrder_date(rs.getDate("order_date"));
			vo.setOrder_count(rs.getInt("order_count"));
			vo.setOrder_total_price(rs.getInt("order_total_price"));

		}
		rs.close();
		stmt.close();

		return vo;
	}

}