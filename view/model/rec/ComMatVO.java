package view.model.rec;

public class ComMatVO {      // 자재 거래처
   String ven_name, ven_tel, ven_mgr_name, ven_mgr_tel, ven_addr;
   int vender_no;
   
   public ComMatVO() {
      
   }
   
   
   public ComMatVO(String ven_name, String ven_tel, String ven_mgr_name, String ven_mgr_tel, String ven_addr) {
      this.ven_name = ven_name;
      this.ven_tel = ven_tel;
      this.ven_mgr_name = ven_mgr_name;
      this.ven_mgr_tel = ven_mgr_tel;
      this.ven_addr = ven_addr;
   }

   
   public String getVen_name() {
      return ven_name;
   }
   public void setVen_name(String ven_name) {
      this.ven_name = ven_name;
   }
   public String getVen_tel() {
      return ven_tel;
   }
   public void setVen_tel(String ven_tel) {
      this.ven_tel = ven_tel;
   }
   public String getVen_mgr_name() {
      return ven_mgr_name;
   }
   public void setVen_mgr_name(String ven_mgr_name) {
      this.ven_mgr_name = ven_mgr_name;
   }
   public String getVen_mgr_tel() {
      return ven_mgr_tel;
   }
   public void setVen_mgr_tel(String ven_mgr_tel) {
      this.ven_mgr_tel = ven_mgr_tel;
   }
   public String getVen_addr() {
      return ven_addr;
   }
   public void setVen_addr(String ven_addr) {
      this.ven_addr = ven_addr;
   }
   public int getVender_no() {
      return vender_no;
   }
   public void setVender_no(int vender_no) {
      this.vender_no = vender_no;
   }
}