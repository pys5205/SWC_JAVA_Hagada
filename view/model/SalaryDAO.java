package view.model;

import java.sql.*;
import java.util.ArrayList;

import view.model.rec.*;
import view.tab.*;

public class SalaryDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;

	public SalaryDAO() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public ArrayList salarySelect(int empno, String year) throws Exception {
		String sql = "select substr(cmt_outtime,6,2),sum(((18-substr(cmt_intime,12,2))*worker_per_hour)),sum((worker_per_hour*1.5)*(substr(cmt_outtime,12,2)-18)) "
				+ "from commute_tb c, worker_tb w " + "where c.worker_no = w.worker_no and w.worker_no = " + empno
				+ " and substr(cmt_intime,1,4) = substr(cmt_outtime,1,4) and substr(cmt_intime,1,4) = '" + year + "' "
				+ "and substr(cmt_outtime,6,2) = substr(cmt_intime,6,2) " + " group by substr(cmt_outtime,6,2)";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(empno);
			temp.add(year);
			temp.add(rs.getString("substr(cmt_outtime,6,2)"));
			temp.add(rs.getString("sum(((18-substr(cmt_intime,12,2))*worker_per_hour))"));
			temp.add(rs.getString("sum((worker_per_hour*1.5)*(substr(cmt_outtime,12,2)-18))"));
			list.add(temp);

		}
		rs.close();
		stmt.close();
		// salaryInsert(list);
		return list;
	}

	public ArrayList taxselect(int empno, String year) throws Exception {
		String sql = "select salary_no, sal_month, s.sal_public*0.025, (s.sal_public*0.025)*0.1, s.sal_public*0.045, s.sal_public*0.035, s.sal_public*0.009, "
				+ "s.sal_public*0.025+(s.sal_public*0.025)*0.1+ s.sal_public*0.045+s.sal_public*0.035+ s.sal_public*0.009 총금액"
				+ " from worker_tb w, salary_tb s " + "where w.worker_no = s.worker_no and w.worker_no = " + empno
				+ " and sal_year = " + year;

		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList tax = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(empno);
			temp.add(rs.getString("salary_no"));
			temp.add(year);
			temp.add(rs.getString("sal_month"));
			temp.add(rs.getString("s.sal_public*0.025"));
			temp.add(rs.getString("(s.sal_public*0.025)*0.1"));
			temp.add(rs.getString("s.sal_public*0.045"));
			temp.add(rs.getString("s.sal_public*0.035"));
			temp.add(rs.getString("s.sal_public*0.009"));
			temp.add(rs.getString("총금액"));

			tax.add(temp);
		}
		rs.close();
		stmt.close();
		System.out.println("세금 검색 성공");
		return tax;
	}

	public ArrayList selTotal(int empno, String year) throws Exception {
		String sql = "select worker_name, sal_year, sal_month, sal_public+sal_plus 세전, sal_public+sal_plus-tax_total 세후"
				+ " from worker_tb w, salary_tb s, sal_detail_tb sd " + " where w.worker_no = " + empno
				+ " and w.worker_no = s.worker_no "
				+ " and s.sal_year = " + year + " and s.salary_no = sd.salary_no";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("worker_name"));
			temp.add(empno);
			temp.add(rs.getString("sal_year"));
			temp.add(rs.getString("sal_month"));
			temp.add(rs.getString("세전"));
			temp.add(rs.getString("세후"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		System.out.println("세전후 검색 성공");
		return list;
	}

	public void salaryInsert(ArrayList list) throws Exception {
		Object data = null;

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < ((ArrayList) list.get(i)).size(); j++) {
				data = ((ArrayList) list.get(i)).get(j);
				System.out.print(data + "|");
			}
			System.out.print("\n");
		}
		for (int i = 0; i < list.size(); i++) {
			String sql = "insert into salary_tb(salary_no, sal_history, worker_no, sal_year, sal_month, sal_public, sal_plus) "
					+ "values(salary_num.nextval, sysdate, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setObject(1, ((ArrayList) list.get(i)).get(0));
			ps.setObject(2, ((ArrayList) list.get(i)).get(1));
			ps.setObject(3, ((ArrayList) list.get(i)).get(2));
			ps.setObject(4, ((ArrayList) list.get(i)).get(3));
			ps.setObject(5, ((ArrayList) list.get(i)).get(4));

			ps.executeUpdate();
		}

		ps.close();
		System.out.println("salary_tb 추가 성공");
	}

	public void taxInsert(ArrayList tax) throws Exception {
		for (int i = 0; i < tax.size(); i++) {		
			String sql = "insert into sal_detail_tb(tax_num, salary_no, tax_income, tax_local, tax_national, tax_health, tax_employment, tax_total) "
					+ "values(sal_detail_num.nextVal, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setObject(1, ((ArrayList) tax.get(i)).get(1));
			ps.setObject(2, ((ArrayList) tax.get(i)).get(4));
			ps.setObject(3, ((ArrayList) tax.get(i)).get(5));
			ps.setObject(4, ((ArrayList) tax.get(i)).get(6));
			ps.setObject(5, ((ArrayList) tax.get(i)).get(7));
			ps.setObject(6, ((ArrayList) tax.get(i)).get(8));
			ps.setObject(7, ((ArrayList) tax.get(i)).get(9));

			ps.executeUpdate();
		}

		ps.close();
		System.out.println("sal_detail_tb 추가 성공");
	}

	public void totalInsert(ArrayList list) throws Exception {
		Object data = null;
		for (int i = 0; i < list.size(); i++) {
			String sql = "update salary_tb set sal_after_tax = ?, sal_before_tax = ? "
					+ " where worker_no = ? and sal_year = ? and sal_month = ?";
			ps = con.prepareStatement(sql);

			ps.setObject(1, ((ArrayList) list.get(i)).get(4));
			ps.setObject(2, ((ArrayList) list.get(i)).get(5));
			ps.setObject(3, ((ArrayList) list.get(i)).get(1));
			ps.setObject(4, ((ArrayList) list.get(i)).get(2));
			ps.setObject(5, ((ArrayList) list.get(i)).get(3));

			ps.executeUpdate();
		}

		ps.close();
		System.out.println("세전세후 업데이트 성공");
	}

	public boolean samesal(ArrayList list) throws Exception {
		Object year, year2;
		Object month, month2;
		boolean res = true;

		String sql = "select sal_year, sal_month from salary_tb";
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList same = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("sal_year"));
			temp.add(rs.getString("sal_month"));

			same.add(temp);
		}
		for (int i = 0; i < list.size(); i++) {
			year2 = ((ArrayList) list.get(i)).get(1);
			month2 = ((ArrayList) list.get(i)).get(2);

			year = (((ArrayList) same.get(i)).get(0));
			month = (((ArrayList) same.get(i)).get(1));
			if (year.equals(year2) && month.equals(month2)) {
				res = false;
				System.out.println("중복중복");
			}
		}
		rs.close();
		stmt.close();
		return res;
	}

	public void salDel(int empno, String year) throws Exception {
		/*String sql2 = " delete sal_detail_tb "
				+ "where salary_no = (select salary_no from salary_tb where worker_no = ? and sal_year = ?)";
		ps = con.prepareStatement(sql2);
		ps.setInt(1, empno);
		ps.setString(2, year);
		ps.executeUpdate();*/
		
		String sql = "delete salary_tb where worker_no =" + empno + " and sal_year = " + year;
		ps = con.prepareStatement(sql);

		ps.executeUpdate();
		ps.close();
		System.out.println("삭제월급");
	}
}
