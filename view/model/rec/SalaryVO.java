package view.model.rec;

public class SalaryVO {
	String name, year, month;
	int empno, monthsal, plussal;

	public SalaryVO() {

	}

	public SalaryVO(int emp, String year, String month, int monthsal, int plussal) {
		this.empno = emp;
		this.year = year;
		this.month = month;
		this.monthsal = monthsal;
		this.plussal = plussal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getMonthsal() {
		return monthsal;
	}

	public void setMonthsal(int monthsal) {
		this.monthsal = monthsal;
	}

	public int getPlussal() {
		return plussal;
	}

	public void setPlussal(int plussal) {
		this.plussal = plussal;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
