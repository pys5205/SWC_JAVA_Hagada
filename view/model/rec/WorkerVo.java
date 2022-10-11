package view.model.rec;

public class WorkerVo {
	String name, jumin, tel, major, safe, acc;
	int per_hour, empno,career, Workerno;

	public WorkerVo() {

	}

	public WorkerVo(String name, String jumin, String tel, String safe, int empno, String major, int per_hour, String acc,
			int career) {
		this.name = name;
		this.jumin = jumin;
		this.tel = tel;
		this.safe = safe;
		this.empno = empno;
		this.major = major;
		this.per_hour = per_hour;
		this.acc = acc;
		this.career = career;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

	public int getPer_hour() {
		return per_hour;
	}

	public void setPer_hour(int per_hour) {
		this.per_hour = per_hour;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public int getWorkerno() {
		return Workerno;
	}

	public void setWorkerno(int workerno) {
		Workerno = workerno;
	}
}
