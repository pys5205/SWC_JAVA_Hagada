package view.model.rec;

import java.util.Date;

public class MaterialOrderVO {
	int order_no, material_no, order_count, order_total_price;
	Date order_date;

	public MaterialOrderVO() {

	}

	public MaterialOrderVO(int material_no, Date order_date, int order_count, int order_total_price) {
		this.material_no = material_no;
		this.order_date = order_date;
		this.order_count = order_count;
		this.order_total_price = order_total_price;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getMaterial_no() {
		return material_no;
	}

	public void setMaterial_no(int material_no) {
		this.material_no = material_no;
	}

	public int getOrder_total_price() {
		return order_total_price;
	}

	public void setOrder_total_price(int order_total_price) {
		this.order_total_price = order_total_price;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
}