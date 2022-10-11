package view.model.rec;

public class CompanyVO {   // 중장비 거래처
   String com_name, com_tel, com_mgr_name, com_mgr_tel, com_addr;
   int equip_company_no;
   
   
   public CompanyVO() {
      
   }
   
   
   public CompanyVO(String com_name, String com_tel, String com_mgr_name, String com_mgr_tel, String com_addr) {
      this.com_name = com_name;
      this.com_tel = com_tel;
      this.com_mgr_name = com_mgr_name;
      this.com_mgr_tel = com_mgr_tel;
      this.com_addr = com_addr;
   }
   
   public String getCom_name() {
      return com_name;
   }
   public void setCom_name(String com_name) {
      this.com_name = com_name;
   }
   public String getCom_tel() {
      return com_tel;
   }
   public void setCom_tel(String com_tel) {
      this.com_tel = com_tel;
   }
   public String getCom_mgr_name() {
      return com_mgr_name;
   }
   public void setCom_mgr_name(String com_mgr_name) {
      this.com_mgr_name = com_mgr_name;
   }
   public String getCom_mgr_tel() {
      return com_mgr_tel;
   }
   public void setCom_mgr_tel(String com_mgr_tel) {
      this.com_mgr_tel = com_mgr_tel;
   }
   public String getCom_addr() {
      return com_addr;
   }
   public void setCom_addr(String com_addr) {
      this.com_addr = com_addr;
   }
   public int getEquip_company_no() {
      return equip_company_no;
   }
   public void setEquip_company_no(int equip_company_no) {
      this.equip_company_no = equip_company_no;
   }

}