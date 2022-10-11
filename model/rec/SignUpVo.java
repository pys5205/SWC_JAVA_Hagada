package model.rec;

public class SignUpVo {
	String emp_name, emp_id, emp_pw, emp_ssn, emp_tel;

	public SignUpVo() {

	}

	public SignUpVo(String emp_name, String emp_id, String emp_pw, String emp_ssn, String emp_tel) {
		this.emp_name = emp_name;
		this.emp_id = emp_id;
		this.emp_pw = emp_pw;
		this.emp_ssn = emp_ssn;
		this.emp_tel = emp_tel;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_pw() {
		return emp_pw;
	}

	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}

	public String getEmp_ssn() {
		return emp_ssn;
	}

	public void setEmp_ssn(String emp_ssn) {
		this.emp_ssn = emp_ssn;
	}

	public String getEmp_tel() {
		return emp_tel;
	}

	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}

}
