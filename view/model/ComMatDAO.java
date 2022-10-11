package view.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.model.rec.ComMatVO;
import view.model.rec.CompanyVO;

public class ComMatDAO {         // 중장비 거래처
   public ComMatDAO() throws Exception {
      // TODO Auto-generated constructor stub
      connectDB();
   }

   // ###########################################################
   // DB control method
   Connection con;
   String url = "jdbc:oracle:thin:@localhost:1521:pys";
   String user = "hagada";
   String pass = "pass";
   String driver = "oracle.jdbc.driver.OracleDriver";
   Statement stmt = null;
   PreparedStatement ps = null;
   
   ComMatVO vo = new ComMatVO();

   void connectDB() throws Exception {
      /*
       * 1. 드라이버를 드라이버매니저에 등록 2. 연결 객체 얻어오기
       */
      Class.forName(driver);
      con = DriverManager.getConnection(url, user, pass);
   }
   
   public void bComMatInsert(ComMatVO vo) throws SQLException{      // 중장비 정보 등록
       String name = vo.getVen_name();
       String tel = vo.getVen_tel();
           String mgrname = vo.getVen_mgr_name();
           String mgrtel = vo.getVen_mgr_tel();
           String addr = vo.getVen_addr();
       
       String sql = "insert into vender_tb(vender_no, ven_name, ven_tel, ven_mgr_name, ven_mgr_tel, ven_addr) "
             + "values (matcom_num.nextval, ?, ?, ?, ?, ?)";
       ps = con.prepareStatement(sql);
       ps.setString(1, name);   
       ps.setString(2, tel);
       ps.setString(3, mgrname);
       ps.setString(4, mgrtel);
       ps.setString(5, addr);
       
       
       ps.executeUpdate();       
       ps.close();
    }
   
   public void bComMatUpdate(ComMatVO vo, int num) throws Exception {   // 중장비 정보 수정
      
      String sql = "UPDATE vender_tb set ven_name = ?, ven_tel = ?, ven_mgr_name = ?, ven_mgr_tel =?, ven_addr =? where vender_no = ?";
      ps = con.prepareStatement(sql);
      
      ps.setString(1, vo.getVen_name());
      ps.setString(2, vo.getVen_tel());
      ps.setString(3, vo.getVen_mgr_name());
      ps.setString(4, vo.getVen_mgr_tel());
      ps.setString(5, vo.getVen_addr());
      ps.setInt(6, num);
                                 
      ps.executeUpdate();
      ps.close();
   }
   
   public void bComMatDelete(int num) throws Exception{         // 중장비 정보 삭제
      String sql = "DELETE vender_tb where vender_no = ? ";
      
      ps = con.prepareStatement(sql);
      
      ps.setInt(1, num);
      ps.executeUpdate();
      ps.close();
      
   }
   public ArrayList bComMatSearch(int sel, String text) throws Exception {
      String[] selCol = { "ven_name", "ven_mgr_name" };
      String sql = "select vender_no, ven_name, ven_tel, ven_mgr_name, ven_mgr_tel, ven_addr "
            + "from vender_tb "
            + "where " + selCol[sel]
            + " like '%" + text + "%'";
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      ArrayList list = new ArrayList();

      while (rs.next()) {
         ArrayList temp = new ArrayList();
         temp.add(rs.getInt("vender_no"));
         temp.add(rs.getString("ven_name"));
         temp.add(rs.getString("ven_tel"));
         temp.add(rs.getString("ven_mgr_name"));
         temp.add(rs.getString("ven_mgr_tel"));
         temp.add(rs.getString("ven_addr"));
         list.add(temp);
      }
      rs.close();
      stmt.close();
      return list;
   }
   
   
   public ComMatVO findByNum(int vNum) throws Exception {

      ComMatVO vo = new ComMatVO();
      stmt = con.createStatement();
      String sql = "SELECT * FROM vender_tb WHERE vender_no=" + vNum;
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
         vo.setVender_no(rs.getInt("vender_no"));
         vo.setVen_name(rs.getString("ven_name"));
         vo.setVen_tel(rs.getString("ven_tel"));
         vo.setVen_mgr_name(rs.getString("ven_mgr_name"));
         vo.setVen_mgr_tel(rs.getString("ven_mgr_tel"));
         vo.setVen_addr(rs.getString("ven_addr"));
         
      }
      rs.close();
      stmt.close();

      return vo;
   }
}