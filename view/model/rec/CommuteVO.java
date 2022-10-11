package view.model.rec;

public class CommuteVO {
	String name, intime, outtime, major, date;
	int empno, workerno;

	// 작업자번호, 이름, 출퇴근시간, 관리자번호, 이름 전문분야, 날짜

	public CommuteVO(int empno, int workerno, String name, String major, String intime, String outtime) {
		this.empno = empno;
		this.workerno = workerno;
		this.name = name;
		this.major = major;
		this.intime = intime;
		this.outtime = outtime;
	}

	public CommuteVO(int workerno, String name, String intime, String outtime) {
		this.workerno = workerno;
		this.name = name;
		this.intime = intime;
		this.outtime = outtime;
	}

	public CommuteVO(int workerno, String name) {
		this.workerno = workerno;
		this.name = name;
	}

	public CommuteVO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getWorkerno() {
		return workerno;
	}

	public void setWorkerno(int workerno) {
		this.workerno = workerno;
	}

}