package view.model.rec;

public class EquipmentVO {
	int money;
	String name, company, check;
	public EquipmentVO() {
		
	}
	public EquipmentVO(String name, int money, String company, String check) {
		this.name = name;
		this.money = money;
		this.company = company;
		this.check = check;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
}
