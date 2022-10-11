package view.model.rec;

public class DrawingVO {
	String drawing_version, drawing_type, drawing_indate, drawing_image;
	int drawing_no, area_no;
	
	public DrawingVO() {}
	
	public DrawingVO(String drawing_version, String drawing_type, String drawing_image, int area_no) {

		this.drawing_version = drawing_version;
		this.drawing_type = drawing_type;
		this.drawing_image = drawing_image;
		this.area_no = area_no;
	}

	public String getDrawing_version() {
		return drawing_version;
	}

	public void setDrawing_version(String drawing_version) {
		this.drawing_version = drawing_version;
	}

	public String getDrawing_type() {
		return drawing_type;
	}

	public void setDrawing_type(String drawing_type) {
		this.drawing_type = drawing_type;
	}

	public String getDrawing_indate() {
		return drawing_indate;
	}

	public void setDrawing_indate(String drawing_indate) {
		this.drawing_indate = drawing_indate;
	}

	public String getDrawing_image() {
		return drawing_image;
	}

	public void setDrawing_image(String drawing_image) {
		this.drawing_image = drawing_image;
	}

	public int getDrawing_no() {
		return drawing_no;
	}

	public void setDrawing_no(int drawing_no) {
		this.drawing_no = drawing_no;
	}

	public int getArea_no() {
		return area_no;
	}

	public void setArea_no(int area_no) {
		this.area_no = area_no;
	}
}
