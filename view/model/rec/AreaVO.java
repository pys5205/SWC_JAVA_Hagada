package view.model.rec;

public class AreaVO {
	String area_detail, scaff_check, area_addr;
	int area_no;
	
	public AreaVO(String area_detail, String scaff_check, String area_addr) {
		this.area_detail = area_detail;
		this.scaff_check = scaff_check;
		this.area_addr = area_addr;
	}
	
	public AreaVO() {}

	public String getArea_detail() {
		return area_detail;
	}

	public void setArea_detail(String area_detail) {
		this.area_detail = area_detail;
	}

	public String getScaff_check() {
		return scaff_check;
	}

	public void setScaff_check(String scaff_check) {
		this.scaff_check = scaff_check;
	}

	public String getArea_addr() {
		return area_addr;
	}

	public void setArea_addr(String area_addr) {
		this.area_addr = area_addr;
	}

	public int getArea_no() {
		return area_no;
	}

	public void setArea_no(int area_no) {
		this.area_no = area_no;
	}

}
