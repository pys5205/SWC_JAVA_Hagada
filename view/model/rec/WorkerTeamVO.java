package view.model.rec;

public class WorkerTeamVO {
	int workerNo, teamNo, teamCount;
	String resultdata, teamSelNo;
	
	
	public String getTeamSelNo() {
		return teamSelNo;
	}

	public void setTeamSelNo(String teamSelNo) {
		this.teamSelNo = teamSelNo;
	}

	public String getResultdata() {
		return resultdata;
	}

	public void setResultdata(String resultdata) {
		this.resultdata = resultdata;
	}

	String teamName;
	
	public int getTeamCount() {
		return teamCount;
	}

	public void setTeamCount(int teamCount) {
		this.teamCount = teamCount;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamInfo) {
		this.teamName = teamInfo;
	}

	public int getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}

	public int getWorkerNo() {
		return workerNo;
	}

	public void setWorkerNo(int workerNo) {
		this.workerNo = workerNo;
	}

	public WorkerTeamVO(){
		
	}


}
