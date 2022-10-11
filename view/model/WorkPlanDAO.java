package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.model.rec.WorkVO;
import view.model.rec.WorkerTeamVO;

public class WorkPlanDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	// String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public WorkPlanDAO() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}

	public ArrayList materialCheck() throws Exception {
		String sql = "select type.mat_type_name typeName, mat.material_no matNo, mat.mat_name matName, ven.ven_name venName "
				+ "from material_type_tb type, material_tb mat, vender_tb ven "
				+ "where type.material_type_no = mat.material_type_no and ven.vender_no = mat.vender_no";

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("typeName"));
			temp.add(rs.getInt("matNo"));
			temp.add(rs.getString("matName"));
			temp.add(rs.getString("venName"));

			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}
 
	public ArrayList equipmentCheck() throws Exception {
		String sql = "select com.com_name comName, equip.equipment_no equipNo, equip.equip_name equipName "
				+ "from equip_company_tb com, equipment_tb equip "
				+ "where com.equip_company_no = equip.equip_company_no";

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("comName"));
			temp.add(rs.getInt("equipNo"));
			temp.add(rs.getString("equipName"));

			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}

	public void addMaterial(int selNum, int count, JTable dt) throws Exception {
		DefaultTableModel model = (DefaultTableModel) dt.getModel();
		String typeName = "";
		String matName = "";
		int matNo = 0;
		int countCheck = count;

		String sql = "select type.mat_type_name typeName, mat.material_no matNo, mat.mat_name matName "
				+ "from material_type_tb type, material_tb mat "
				+ "where type.material_type_no = mat.material_type_no and mat.material_no = " + selNum;

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			typeName = rs.getString("typeName");
			matNo = rs.getInt("matNo");
			matName = rs.getString("matName");

		}
		String[] data = { typeName, Integer.toString(matNo), matName, Integer.toString(countCheck) };
		rs.close();
		stmt.close();
		model.addRow(data);
		JOptionPane.showMessageDialog(null, matName + ", " + countCheck + "개 추가 완료.");

	}

	public void addEquipment(int selNum, int time, JTable dt) throws Exception {
		DefaultTableModel model = (DefaultTableModel) dt.getModel();
		String comName = "";
		String equipName = "";
		int equipNo = 0;
		int timeCheck = time;

		String sql = "select com.com_name comName, equip.equipment_no equipNo, equip.equip_name equipName \r\n"
				+ "from equip_company_tb com, equipment_tb equip\r\n"
				+ "where com.equip_company_no = equip.equip_company_no and equip.equipment_no = " + selNum;

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);

		if (rs.next()) {
			comName = rs.getString("comName");
			equipNo = rs.getInt("equipNo");
			equipName = rs.getString("equipName");

		}
		String[] data = { comName, Integer.toString(equipNo), equipName, Integer.toString(timeCheck) };
		rs.close();
		stmt.close();
		model.addRow(data);
		JOptionPane.showMessageDialog(null, equipName + ", " + timeCheck + "시간 추가 완료.");
	}

	public ArrayList searchTeam() throws Exception{
		WorkerTeamVO vo = new WorkerTeamVO();
		String sql = "select team_no, team_name, team_count from team_tb";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("team_no"));
			temp.add(rs.getString("team_name"));
			temp.add(rs.getInt("team_count"));

			list.add(temp);
			
		}

		rs.close();
		stmt.close();
		return list;
	}
	
	public void WorkInsert(WorkVO vo) throws SQLException{
		String workName = vo.getWorkName();
		int teamNo = vo.getTeamNo();
		String startDate = vo.getStartDate();
		String endDate = vo.getEndDate();
		ArrayList matlist = vo.getMatList();
		ArrayList equiplist = vo.getEquipList();
		String state = "대기";
		String stateTeam = "배정";
		
		int workNo = 0;
  	  System.out.println(startDate+"다오");
  	  System.out.println(endDate+"다오");
		String worksql = "insert into work_tb(work_no, work_name, team_no) "
				+ "values(work_num.nextval, ?, ?)";
		ps = con.prepareStatement(worksql);
		ps.setString(1, workName);
		ps.setInt(2, teamNo);
		ps.executeUpdate();
	//	ps.close();
		
		String teamState = "update team_tb set team_state = ? where team_no =" + teamNo;
		ps = con.prepareStatement(teamState);
		ps.setString(1, stateTeam);
		ps.executeUpdate();
		
		String selectWorkNosql = "select work_no from work_tb where work_name = '" + workName + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectWorkNosql);
		if(rs.next()) {
			workNo = rs.getInt("work_no");
		}
		rs.close();
		stmt.close();
		
		String workState = "insert into work_detail_tb(work_detail_no, work_detail_state, work_no) "
				+ "values(work_detail_num.nextval, ?, ?)";
		ps = con.prepareStatement(workState);
		ps.setString(1, state);
		ps.setInt(2, workNo);
		ps.executeUpdate();
		
		String insertSchedulesql = "insert into work_schedule_tb(work_schedule_no, actual_start_date, actual_end_date, work_no) "
				+ "values(work_schedule_num.nextval, ?, ?, ?)";
		ps = con.prepareStatement(insertSchedulesql);
		ps.setString(1, startDate);
		ps.setString(2, endDate);
		ps.setInt(3, workNo);
		ps.executeUpdate();
		
  	  for(int i = 0; i < matlist.size();i++ )
  	  {
  		  String a = String.valueOf(matlist.get(i));
  		  String[] b = a.split("#");
  		  int stockCount = Integer.parseInt(b[1]);
  		  int materialNum = Integer.parseInt(b[0]);

  		  String insertMatsql = "insert into stock_tb(stock_no, stock_count, material_no, work_no) "
  		  		+ "values(work_stock_num.nextval, ?, ?, ?)";
  		  ps = con.prepareStatement(insertMatsql);
  		  ps.setInt(1, stockCount);
  		  ps.setInt(2, materialNum);
  		  ps.setInt(3, workNo);
  		  ps.executeUpdate();
  	  }
  	  
  	  for(int i = 0; i < equiplist.size();i++ )
  	  {
  		  String a = String.valueOf(equiplist.get(i));
  		  String[] b = a.split("#");
  		  int equipUseTime = Integer.parseInt(b[1]);
  		  int equipNum = Integer.parseInt(b[0]);

  		  String insertMatsql = "insert into work_equip_tb(work_equip_no, work_equip_start_date, work_equip_end_date, work_equip_use_time, work_no, equipment_no) "
  		  		+ "values(work_equip_num.nextval, ?, ?, ?, ?, ?)";
  		  ps = con.prepareStatement(insertMatsql);
  		  ps.setString(1, startDate);
  		  ps.setString(2, endDate);
  		  ps.setInt(3, equipUseTime);
  		  ps.setInt(4, workNo);
  		  ps.setInt(5, equipNum);
  		  ps.executeUpdate();
  	  }
  	  
  	  ps.close();

		
	}

	public ArrayList allWorkSearch() throws Exception{
		String sql = "select work_name, t.team_count teamCount, d.work_detail_state detail from work_tb w, team_tb t, work_detail_tb d "
				+ "where w.team_no = t.team_no and w.work_no = d.work_no";

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		ArrayList list = new ArrayList();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("work_name"));
			temp.add(rs.getInt("teamCount"));
			temp.add(rs.getString("detail"));
			
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;

	}

	public WorkVO selectWork(String workName) throws Exception{
		int teamNo = 0;
		String startDate = "";
		String endDate  = "";
		ArrayList matList = new ArrayList();
		ArrayList equipList = new ArrayList();
		int workNo = 0;
		
		String workNosql = "select work_no from work_tb where work_name = '" + workName +"'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(workNosql);
		if(rs.next()) {
			workNo = rs.getInt("work_no");
		}

		String sql = "select t.team_no teamNO, to_char(w.actual_start_date, 'YYYY/MM/DD') Sdate, to_char(w.actual_end_date, 'YYYY/MM/DD') Edate from work_tb t, work_schedule_tb w "
				+ "where t.work_no = " + workNo ;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()) {
			teamNo = rs.getInt("teamNo");
			startDate = rs.getString("Sdate");
			endDate = rs.getString("Edate");
		}
		
		String matsql ="select type.mat_type_name typeName, st.material_no matNo, mat.mat_name matName, st.stock_count stockCount "
				+ "from material_type_tb type, material_tb mat, stock_tb st "
				+ "where st.work_no = " + workNo +" and st.material_no = mat.material_no and mat.material_type_no = type.material_type_no";
		stmt = con.createStatement();
		rs = stmt.executeQuery(matsql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("typeName"));
			temp.add(rs.getInt("matNo"));
			temp.add(rs.getString("matName"));
			temp.add(rs.getInt("stockCount"));			
			matList.add(temp);
		}
		
		String equipsql = "select com.com_name comName, w.equipment_no equipNo, equip.equip_name equipName, w.work_equip_use_time useTime "
				+ "from equip_company_tb com, equipment_tb equip, work_equip_tb w "
				+ "where w.work_no = " + workNo + " and w.equipment_no = equip.equipment_no and equip.equip_company_no = com.equip_company_no";
		stmt = con.createStatement();
		rs = stmt.executeQuery(equipsql);
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("comName"));
			temp.add(rs.getInt("equipNo"));
			temp.add(rs.getString("equipName"));
			temp.add(rs.getInt("useTime"));			
			equipList.add(temp);
		}
		stmt.close();
		rs.close();
		
		
		
		WorkVO vo = new WorkVO(workName, teamNo, startDate, endDate, matList, equipList);
		
		return vo;
	}
	
	public void mat_equip_View(String workName, JTable matdt, JTable equipdt) throws Exception{
		DefaultTableModel matModel = (DefaultTableModel) matdt.getModel();
		DefaultTableModel equipModel = (DefaultTableModel) equipdt.getModel();
		int workNo = 0;
		String type_com_Name = "";
		String mat_equip_Name = "";
		int mat_equip_No = 0;
		int stockCount_equipTime = 0;
		String workNosql = "select work_no from work_tb where work_name = '" + workName +"'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(workNosql);
		if(rs.next()) {
			workNo = rs.getInt("work_no");
		}
		
		String matsql ="select type.mat_type_name typeName, st.material_no matNo, mat.mat_name matName, st.stock_count stockCount "
				+ "from material_type_tb type, material_tb mat, stock_tb st "
				+ "where st.work_no = " + workNo +" and st.material_no = mat.material_no and mat.material_type_no = type.material_type_no";
		stmt = con.createStatement();
		rs = stmt.executeQuery(matsql);
		while(rs.next()) {
			type_com_Name = rs.getString("typeName");
			mat_equip_No = rs.getInt("matNo");
			mat_equip_Name = rs.getString("matName");
			stockCount_equipTime = rs.getInt("stockCount");
			
			String data[] = {type_com_Name, Integer.toString(mat_equip_No), mat_equip_Name, Integer.toString(stockCount_equipTime)};
			matModel.addRow(data);
		}
		
		String equipsql = "select com.com_name comName, w.equipment_no equipNo, equip.equip_name equipName, w.work_equip_use_time useTime "
				+ "from equip_company_tb com, equipment_tb equip, work_equip_tb w "
				+ "where w.work_no = " + workNo + " and w.equipment_no = equip.equipment_no and equip.equip_company_no = com.equip_company_no";
		stmt = con.createStatement();
		rs = stmt.executeQuery(equipsql);
		while(rs.next()) {
			type_com_Name = rs.getString("comName");
			mat_equip_No = rs.getInt("equipNo");
			mat_equip_Name = rs.getString("equipName");
			stockCount_equipTime = rs.getInt("useTime");
			
			String data[] = {type_com_Name, Integer.toString(mat_equip_No), mat_equip_Name, Integer.toString(stockCount_equipTime)};
			equipModel.addRow(data);
		}
		stmt.close();
		rs.close();
	}
	
	public void WorkUpdate(WorkVO vo) throws Exception{
		String workName = vo.getWorkName();
		int teamNo = vo.getTeamNo();
		String startDate = vo.getStartDate();
		String endDate = vo.getEndDate();
		ArrayList matlist = vo.getMatList();
		ArrayList equiplist = vo.getEquipList();
		
		int workNo = 0;
		
		String workNoSelect = "select work_no from work_tb where work_name = '" + workName + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(workNoSelect);
		if(rs.next()) {
			workNo = rs.getInt("work_no");
		}
		rs.close();
		stmt.close();
		
		String workUpdate = "update work_tb set work_name = ?, team_no = ? where work_no = " + workNo;
		  ps = con.prepareStatement(workUpdate);
		  ps.setString(1, workName);
		  ps.setInt(2, teamNo);
		  ps.executeUpdate();
		  
		  String scheduleUpdate = "update work_schedule_tb set actual_start_date = ?, actual_end_date = ? where work_no = " + workNo;
		  ps = con.prepareStatement(scheduleUpdate);
		  ps.setString(1, startDate);
		  ps.setString(2, endDate);
		  ps.executeUpdate();
		  
			String delmat = "delete from stock_tb where work_no = ?";
			ps = con.prepareStatement(delmat);
			ps.setInt(1, workNo);
			ps.executeUpdate();
			
			String delequip = "delete from work_equip_tb where work_no = ?";
			ps = con.prepareStatement(delequip);
			ps.setInt(1, workNo);
			ps.executeUpdate();
		  
	  	  for(int i = 0; i < matlist.size();i++ )
	  	  {
	  		  String a = String.valueOf(matlist.get(i));
	  		  String[] b = a.split("#");
	  		  int stockCount = Integer.parseInt(b[1]);
	  		  int materialNum = Integer.parseInt(b[0]);

	  		  String insertMatsql = "insert into stock_tb(stock_no, stock_count, material_no, work_no) "
	  		  		+ "values(work_stock_num.nextval, ?, ?, ?)";
	  		  ps = con.prepareStatement(insertMatsql);
	  		  ps.setInt(1, stockCount);
	  		  ps.setInt(2, materialNum);
	  		  ps.setInt(3, workNo);
	  		  ps.executeUpdate();
	  	  }
	 	  
	  	  for(int i = 0; i < equiplist.size();i++ )
	  	  {
	  		  String a = String.valueOf(equiplist.get(i));
	  		  String[] b = a.split("#");
	  		  int equipUseTime = Integer.parseInt(b[1]);
	  		  int equipNum = Integer.parseInt(b[0]);

	  		  String insertMatsql = "insert into work_equip_tb(work_equip_no, work_equip_start_date, work_equip_end_date, work_equip_use_time, work_no, equipment_no) "
	  		  		+ "values(work_equip_num.nextval, ?, ?, ?, ?, ?)";
	  		  ps = con.prepareStatement(insertMatsql);
	  		  ps.setString(1, startDate);
	  		  ps.setString(2, endDate);
	  		  ps.setInt(3, equipUseTime);
	  		  ps.setInt(4, workNo);
	  		  ps.setInt(5, equipNum);
	  		  ps.executeUpdate();
	  	  }
	 	  
	 	  ps.close();

	}
	
	public void deleteWork(String workName, int teamNo)throws Exception{
		int workNo = 0;
		String state = "삭제";
		String stateTeam = "미배정";

		String workNoSelect = "select work_no from work_tb where work_name = '" + workName + "'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(workNoSelect);
		if (rs.next()) {
			workNo = rs.getInt("work_no");
		}
		rs.close();
		stmt.close();
		
		String teamState = "update team_tb set team_state = ? where team_no =" + teamNo;
		ps = con.prepareStatement(teamState);
		ps.setString(1, stateTeam);
		ps.executeUpdate();

		String workState = "update work_detail_tb set work_detail_state = ? where work_no = ?";
		ps = con.prepareStatement(workState);
		ps.setString(1, state);
		ps.setInt(2, workNo);
		ps.executeUpdate();

		String workTeamNull = "update work_tb set team_no = null, area_no = null where work_no = ?";
		ps = con.prepareStatement(workTeamNull);
		ps.setInt(1, workNo);
		ps.executeUpdate();

		String delmat = "delete from stock_tb where work_no = ?";
		ps = con.prepareStatement(delmat);
		ps.setInt(1, workNo);
		ps.executeUpdate();

		String delequip = "delete from work_equip_tb where work_no = ?";
		ps = con.prepareStatement(delequip);
		ps.setInt(1, workNo);
		ps.executeUpdate();

		ps.close();
	}
	
	public void workProgress(String data) throws Exception{
		String state = "진행";
		String sql = "update work_detail_tb set work_detail_state = ? "
				+ "where work_no = (select work_no from work_tb where work_name = '" + data + "')";
		ps = con.prepareStatement(sql);
		ps.setString(1, state);
		ps.executeUpdate();
		ps.close();
		
		
	}

	public void workComplete(String memo, String workName) throws Exception{
		String state = "완료";
		String teamState = "미배정";
		
		String sql = "update work_detail_tb set work_detail_state = ?, memo = ? "
				+ "where work_no = (select work_no from work_tb where work_name = '" + workName +"')";
		ps = con.prepareStatement(sql);
		ps.setString(1, state);
		ps.setString(2, memo);
		ps.executeUpdate();
		
		String teamsql = "update team_tb set team_state = ? where team_no = (select team_no from work_tb where work_name = '"+ workName +"')";
		ps = con.prepareStatement(teamsql);
		ps.setString(1, teamState);
		ps.executeUpdate();
		ps.close();

		
	}
}