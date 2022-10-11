package view.model.rec;

import java.util.ArrayList;

public class WorkVO {
	String workName, startDate, endDate;
	int teamNo;
	ArrayList matList = new ArrayList();
	ArrayList equipList = new ArrayList();
	
	public WorkVO(){
		
	}
	
	public WorkVO(String name, int teamNo, String sdate, String edate, ArrayList matList, ArrayList equipList) {
		this.workName = name;
		this.startDate = sdate;
		this.endDate = edate;
		this.teamNo = teamNo;
		this.matList = matList;
		this.equipList = equipList;
		
	}
	
	
	public ArrayList getEquipList() {
		return equipList;
	}

	public void setEquipList(ArrayList equipList) {
		this.equipList = equipList;
	}
	
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public ArrayList getMatList() {
		return matList;
	}
	public void setMatList(ArrayList matList) {
		this.matList = matList;
	} 

}
