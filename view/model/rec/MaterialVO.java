package view.model.rec;

public class MaterialVO {
	String mat_Name;
	int material_no, material_type_no, mat_Price, mat_count, vender_no;

	public int getMaterial_type_no() {
		return material_type_no;
	}

	public void setMaterial_type_no(int material_type_no) {
		this.material_type_no = material_type_no;
	}

	public int getVender_no() {
		return vender_no;
	}

	public void setVender_no(int vender_no) {
		this.vender_no = vender_no;
	}

	public String getMat_Name() {
		return mat_Name;
	}

	public int getMat_count() {
		return mat_count;
	}

	public void setMat_count(int mat_count) {
		this.mat_count = mat_count;
	}

	public void setMat_Name(String mat_Name) {
		this.mat_Name = mat_Name;
	}

	public int getMaterial_no() {
		return material_no;
	}

	public void setMaterial_no(int material_no) {
		this.material_no = material_no;
	}

	public int getMat_Price() {
		return mat_Price;
	}

	public void setMat_Price(int mat_Price) {
		this.mat_Price = mat_Price;
	}

	public MaterialVO() {

	}

	public MaterialVO(String mat_Name, int material_type_no, int mat_Price, int mat_count, int vender_no) {
		this.mat_Name = mat_Name;
		this.material_type_no = material_type_no;
		this.mat_Price = mat_Price;
		this.mat_count = mat_count;
		this.vender_no = vender_no;
	}

}