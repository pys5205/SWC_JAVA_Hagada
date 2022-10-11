package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WorkTeamDAO {
	Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:pys";
	// String url = "jdbc:oracle:thin:@192.168.0.79:1521:pys";
	String user = "hagada";
	String pass = "pass";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Statement stmt = null;
	PreparedStatement ps = null; 
	ResultSet rs = null;

	public WorkTeamDAO() throws Exception {
		connectDB();
	}

	void connectDB() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public void workerList(JTable dt) throws Exception{
		DefaultTableModel model = (DefaultTableModel) dt.getModel();

		int workerNo = 0;
		int workerCareer = 0;
		String workerName = "";
		String workerMajor = "";
		String sql = "select worker_no no, worker_name name, worker_major major, worker_career career "
				+ "from worker_tb where team_no is null";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			workerNo = rs.getInt("no");
			workerName = rs.getString("name");
			workerMajor = rs.getString("major");
			workerCareer = rs.getInt("career");
			
			String[] ob = {Integer.toString(workerNo), workerName, workerMajor, Integer.toString(workerCareer)};
			model.addRow(ob);

		}
		rs.close();
		stmt.close();

	}
	
	public void addteamList(int workerNum, JTable addteam, JTable workerlist, int row) throws Exception{
		DefaultTableModel addTeamModel = (DefaultTableModel) addteam.getModel();
		DefaultTableModel workerListModel = (DefaultTableModel) workerlist.getModel();
		int workerNo = 0;
		int workCareer = 0;
		String workName = "";
		String workMajor = "";
		String sql = "select worker_no no, worker_name name, worker_major major, worker_career career "
				+ "from worker_tb where worker_no = " + workerNum;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			workerNo = rs.getInt("no");
			workName = rs.getString("name");
			workMajor = rs.getString("major");
			workCareer = rs.getInt("career");
		}
		
		String[] ob = {Integer.toString(workerNo), workName, workMajor, Integer.toString(workCareer)};
		rs.close();
		stmt.close();
		addTeamModel.addRow(ob);
		workerListModel.removeRow(row);
	}
	
	public void deleteteamList(int workerNum, JTable addteam, JTable workerlist, int row) throws Exception{
		DefaultTableModel addTeamModel = (DefaultTableModel) addteam.getModel();
		DefaultTableModel workerListModel = (DefaultTableModel) workerlist.getModel();
		int workerNo = 0;
		int workCareer = 0;
		String workName = "";
		String workMajor = "";
		String sql = "select worker_no no, worker_name name, worker_major major, worker_career career "
				+ "from worker_tb where worker_no = " + workerNum;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			workerNo = rs.getInt("no");
			workName = rs.getString("name");
			workMajor = rs.getString("major");
			workCareer = rs.getInt("career");
		}
		
		String[] ob = {Integer.toString(workerNo), workName, workMajor, Integer.toString(workCareer)};
		rs.close();
		stmt.close();
		workerListModel.addRow(ob);
		addTeamModel.removeRow(row);
	}
	
	public void createTeam(JTable addteam, JTable teamList, String teamName)throws Exception{
		DefaultTableModel addTeamModel = (DefaultTableModel) addteam.getModel();
		DefaultTableModel TeamModel = (DefaultTableModel) teamList.getModel();
		int teamNo = 0;
		int teamCount = 0;
		int rowcount = addTeamModel.getRowCount();
		String state = "미배정";
		String teamState ="";
		String insertsql = "insert into team_tb(team_no, team_name, team_count, team_state) values(team_num.nextval, ?, ?, ?)";
		ps = con.prepareStatement(insertsql);
		ps.setString(1, teamName);
		ps.setInt(2, rowcount);
		ps.setString(3, state);
		ps.executeUpdate();
		
		String selectsql = "select team_no, team_count, team_state from team_tb where team_name = '"+ teamName +"'";
		stmt = con.createStatement();
		rs = stmt.executeQuery(selectsql);
		if(rs.next()) {
			teamNo = rs.getInt("team_no");
			teamCount = rs.getInt("team_count");
			teamState = rs.getString("team_state");
		}

		for(int i = 0; i<rowcount; i++) {
			int row = i;
			int col = 0;
			int data =Integer.parseInt(String.valueOf(addTeamModel.getValueAt(row, col)));
			
			String updatesql ="update worker_tb set team_no = ? where worker_no = ?";
			ps = con.prepareStatement(updatesql);
			ps.setInt(1, teamNo);
			ps.setInt(2, data);
			ps.executeUpdate();
		}
		
		ps.close();
		rs.close();
		stmt.close();
		
		String ob[] = {Integer.toString(teamNo), teamName, Integer.toString(teamCount), teamState};
		TeamModel.addRow(ob);
		addTeamModel.setNumRows(0);
	}
	
	public void finalTeamList(JTable teamList) throws Exception{
		DefaultTableModel TeamModel = (DefaultTableModel) teamList.getModel();
		int teamNo = 0;
		int teamCount = 0;
		String teamName = "";
		String teamState = "";
		String sql = "select team_no, team_name, team_count, team_state from team_tb";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			teamNo = rs.getInt("team_no");
			teamName = rs.getString("team_name");
			teamCount = rs.getInt("team_count");
			teamState = rs.getString("team_state");
			String[] ob = {Integer.toString(teamNo), teamName, Integer.toString(teamCount), teamState};
			TeamModel.addRow(ob);
		}
		rs.close();
		stmt.close();
	}
	
	public void teamInfo(int data, JTable teamInfo) throws Exception{
		DefaultTableModel TeamInfoModel = (DefaultTableModel) teamInfo.getModel();
		
		TeamInfoModel.setNumRows(0);
		int teamNo = 0;
		String workerName, workerMajor, workerCareer, workerSafe;

		
		String sql = "select team_no, worker_name, worker_major, worker_career, worker_safe_check "
				+ "from worker_tb w "
				+ "where w.team_no = " + data;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			teamNo = rs.getInt("team_no");
			workerName = rs.getString("worker_name");
			workerMajor = rs.getString("worker_major");
			workerCareer = rs.getString("worker_career");
			workerSafe =  rs.getString("worker_safe_check");
			String[] ob = {Integer.toString(teamNo), workerName, workerMajor, workerCareer, workerSafe};
			TeamInfoModel.addRow(ob);
		}
		
		rs.close();
		stmt.close();

	}
	
	public String workNameInfo(int data) throws Exception{
		String workName = "";
		
		String sql = "select work_name from work_tb where team_no = "+ data;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()) {
			workName = rs.getString("work_name");
		}
		
		rs.close();
		stmt.close();
		return workName;
	}

	public void deleteTeam(int teamNo) throws Exception{
		
		String sql = "delete from team_tb where team_no = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, teamNo);
		ps.executeUpdate();
		ps.close();
	}
}