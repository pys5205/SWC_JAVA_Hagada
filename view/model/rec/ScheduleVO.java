package view.model.rec;

public class ScheduleVO {
	String sname, asDate, aeDate, psDate, peDate;
	int schedule_no, work_no;

	public ScheduleVO() {
	}

	public ScheduleVO(String sname, String asDate, String aeDate, String psDate, String peDate) {	
		this.sname = sname;
		this.asDate = asDate;
		this.aeDate = aeDate;
		this.psDate = psDate;
		this.peDate = peDate;
	}
	
	public ScheduleVO(String sname) {
		this.sname = sname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getAsDate() {
		return asDate;
	}

	public void setAsDate(String asDate) {
		this.asDate = asDate;
	}

	public String getAeDate() {
		return aeDate;
	}

	public void setAeDate(String aeDate) {
		this.aeDate = aeDate;
	}

	public String getPsDate() {
		return psDate;
	}

	public void setPsDate(String psDate) {
		this.psDate = psDate;
	}

	public String getPeDate() {
		return peDate;
	}

	public void setPeDate(String peDate) {
		this.peDate = peDate;
	}

	public int getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}

	public int getWork_no() {
		return work_no;
	}

	public void setWork_no(int work_no) {
		this.work_no = work_no;
	}

}
