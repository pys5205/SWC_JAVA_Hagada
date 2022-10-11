package view.tab;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import view.model.*;
import view.model.CompanyDAO;
import view.model.rec.CompanyVO;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;
import javax.swing.table.*;

public class EquipCompanyViewTab extends JPanel implements ActionListener {

   private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
         .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
   ImageIcon icon_search = new ImageIcon(img_search);

   JTextField tfComPanyMGRName, tfComPanyMGRTel, tfSearch, tfCompanyAddr, tfCompanyTel, tfCompanyName, tfCompanyNo;

   JButton btnInsert, btnModify, btnDelete;

   CompanyDAO dao = null;
   JComboBox cbSearch;

   JTable TableEquip;
   EquipTableModel tmEquip;

   /**
    * Create the panel.
    */
   public EquipCompanyViewTab() {
      try {
         dao = new CompanyDAO();
         System.out.println("거래처 DB연결 성공");
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "거래처 DB 연결 실패 : " + e.getMessage());
      }
      addLayout();
      eventProc();
      setStyle();

   }

   void addLayout() {
      setBounds(262, 10, 1026, 680);

      setBackground(Color.gray);
      setLayout(null);

      btnInsert = new JButton("등록");
      btnInsert.setBounds(804, 22, 120, 30);
      btnInsert.setFont(new Font("굴림", Font.BOLD, 15));
      btnInsert.setBackground(new Color(144, 238, 144));
      btnInsert.setForeground(new Color(255, 255, 255));
      add(btnInsert);

      btnModify = new JButton("수정");
      btnModify.setBounds(804, 62, 120, 30);
      btnModify.setForeground(new Color(255, 255, 255));
      btnModify.setFont(new Font("굴림", Font.BOLD, 15));
      btnModify.setBackground(new Color(100, 149, 237));
      add(btnModify);

      btnDelete = new JButton("삭제");
      btnDelete.setBounds(804, 102, 120, 30);
      btnDelete.setForeground(new Color(255, 255, 255));
      btnDelete.setFont(new Font("굴림", Font.BOLD, 15));
      btnDelete.setBackground(new Color(255, 182, 193));
      add(btnDelete);

      JLabel lbSearch = new JLabel(icon_search);
      lbSearch.setBounds(375, 143, 25, 25);
      add(lbSearch);

      String SearchList[] = { "업체명", "업체 담당자명" };
      cbSearch = new JComboBox();
      cbSearch.setBounds(12, 143, 91, 30);
      add(cbSearch);
      cbSearch.addItem("업체명");
      cbSearch.addItem("업체 담당자명");

      tfSearch = new JTextField(15);
      tfSearch.setBounds(102, 143, 266, 30);
      add(tfSearch);

      JPanel panel = new JPanel();
      panel.setBounds(12, 22, 760, 110);
      panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
      panel.setBackground(Color.GRAY);
      add(panel);
      panel.setLayout(null);

      JLabel lbCompanyNo = new JLabel("거래처 번호");
      lbCompanyNo.setBounds(32, 15, 123, 18);
      panel.add(lbCompanyNo);
      lbCompanyNo.setForeground(Color.WHITE);
      lbCompanyNo.setFont(new Font("굴림", Font.BOLD, 15));

      tfCompanyNo = new JTextField();
      tfCompanyNo.setBounds(157, 15, 221, 18);
      panel.add(tfCompanyNo);
      tfCompanyNo.setColumns(10);

      JLabel lbCompanyName = new JLabel("업체명");
      lbCompanyName.setForeground(Color.WHITE);
      lbCompanyName.setFont(new Font("굴림", Font.BOLD, 15));
      lbCompanyName.setBounds(32, 45, 100, 18);
      panel.add(lbCompanyName);

      JLabel lbCompanyTel = new JLabel("업체 전화번호");
      lbCompanyTel.setForeground(Color.WHITE);
      lbCompanyTel.setFont(new Font("굴림", Font.BOLD, 15));
      lbCompanyTel.setBounds(32, 75, 100, 18);
      panel.add(lbCompanyTel);

      tfCompanyName = new JTextField();
      tfCompanyName.setColumns(10);
      tfCompanyName.setBounds(157, 45, 221, 18);
      panel.add(tfCompanyName);

      tfCompanyTel = new JTextField();
      tfCompanyTel.setColumns(10);
      tfCompanyTel.setBounds(157, 75, 221, 18);
      panel.add(tfCompanyTel);

      JLabel lbComPanyMGRName = new JLabel("담당자명");
      lbComPanyMGRName.setBounds(397, 15, 100, 18);
      panel.add(lbComPanyMGRName);
      lbComPanyMGRName.setForeground(Color.WHITE);
      lbComPanyMGRName.setFont(new Font("굴림", Font.BOLD, 15));

      tfComPanyMGRName = new JTextField();
      tfComPanyMGRName.setBounds(522, 15, 221, 18);
      panel.add(tfComPanyMGRName);
      tfComPanyMGRName.setColumns(10);

      JLabel lbComPanyMGRTel = new JLabel("담당자 연락처");
      lbComPanyMGRTel.setBounds(397, 45, 100, 18);
      panel.add(lbComPanyMGRTel);
      lbComPanyMGRTel.setForeground(Color.WHITE);
      lbComPanyMGRTel.setFont(new Font("굴림", Font.BOLD, 15));

      tfComPanyMGRTel = new JTextField();
      tfComPanyMGRTel.setBounds(522, 45, 221, 18);
      panel.add(tfComPanyMGRTel);
      tfComPanyMGRTel.setColumns(10);

      JLabel lbCompanyAddr = new JLabel("업체 주소");
      lbCompanyAddr.setForeground(Color.WHITE);
      lbCompanyAddr.setFont(new Font("굴림", Font.BOLD, 15));
      lbCompanyAddr.setBounds(397, 75, 110, 18);
      panel.add(lbCompanyAddr);

      tfCompanyAddr = new JTextField();
      tfCompanyAddr.setColumns(10);
      tfCompanyAddr.setBounds(522, 75, 221, 18);
      panel.add(tfCompanyAddr);

      JLabel lbEquipment = new JLabel("중장비");
      lbEquipment.setForeground(Color.ORANGE);
      lbEquipment.setFont(new Font("굴림", Font.BOLD, 15));
      lbEquipment.setBounds(709, 92, 51, 18);
      panel.add(lbEquipment);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 178, 1020, 492);
      scrollPane.getViewport().setBackground(Color.gray);
      add(scrollPane);

      tmEquip = new EquipTableModel();
      TableEquip = new JTable(tmEquip);
      TableEquip.setForeground(Color.WHITE);
      TableEquip.setBackground(Color.GRAY);

      scrollPane.setViewportView(TableEquip);
      TableEquip.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = TableEquip.getSelectedRow();
            int col = 0;

            int EquipNum = (Integer) TableEquip.getValueAt(row, col);
            CompanyVO vo = new CompanyVO();
            try {
               vo = dao.findByNum(EquipNum);
            } catch (Exception e2) {
               // TODO: handle exception
               System.out.println("마우스클릭 실패");
            }
            tfCompanyNo.setText(String.valueOf(vo.getEquip_company_no()));
            tfCompanyName.setText(vo.getCom_name());
            tfCompanyTel.setText(vo.getCom_tel());
            tfComPanyMGRName.setText(vo.getCom_mgr_name());
            tfComPanyMGRTel.setText(vo.getCom_mgr_tel());
            tfCompanyAddr.setText(vo.getCom_addr());
         }
      });

   }

   void eventProc() {
      btnInsert.addActionListener(this);
      btnModify.addActionListener(this);
      btnDelete.addActionListener(this);
      tfSearch.addActionListener(this);

   }

   public void actionPerformed(ActionEvent ev) {
      Object o = ev.getSource();

      if (o == btnInsert) {
         String name = tfCompanyName.getText();
         String tel = tfCompanyTel.getText();
         String mgrname = tfComPanyMGRName.getText();
         String mgrtel = tfComPanyMGRTel.getText();
         String addr = tfCompanyAddr.getText();

         CompanyVO vo = new CompanyVO(name, tel, mgrname, mgrtel, addr);
         try {
            dao.bComInsert(vo);
            System.out.println("중장비 거래처정보 입력 성공");
            clearScreen();
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "중장비 거래처정보 입력 실패" + e.getMessage());
         }
      } else if (o == btnModify) {
         String name = tfCompanyName.getText();
         String tel = tfCompanyTel.getText();
         String mgrname = tfComPanyMGRName.getText();
         String mgrtel = tfComPanyMGRTel.getText();
         String addr = tfCompanyAddr.getText();

         CompanyVO vo = new CompanyVO(name, tel, mgrname, mgrtel, addr);
         int num = Integer.parseInt(tfCompanyNo.getText());

         try {
            dao.bComUpdate(vo, num);
            System.out.println("중장비 거래처정보 수정 성공");
            clearScreen();
            selectTable();
         } catch (Exception e1) {
            System.out.println("중장비 거래처정보 수정 실패");
         }
      
      
      }else if (o == btnDelete) {
         int num = Integer.parseInt(tfCompanyNo.getText());
         try {
            dao.bComDelete(num);
            System.out.println("중장비 거래처정보 삭제 성공");
            clearScreen();
            selectTable();
         } catch (Exception e) {
            System.out.println("중장비 거래처정보 삭제 실패");
         }
      } else if (o == tfSearch) {
         int sel = cbSearch.getSelectedIndex();
         String text = tfSearch.getText();
         try {
            ArrayList list = dao.bComSearch(sel, text);
            tmEquip.data = list;
            TableEquip.setModel(tmEquip);
            tmEquip.fireTableDataChanged();
            System.out.println("중장비 거래처 검색 성공");
         } catch (Exception e) {
            System.out.println("중장비 거래처 검색 실패" + e.getMessage());
         }
      }
   }

   public void selectTable() {
      int sel = cbSearch.getSelectedIndex();
      String text = tfSearch.getText();
      try {
         ArrayList list = dao.bComSearch(sel, text);
         tmEquip.data = list;
         TableEquip.setModel(tmEquip);
         tmEquip.fireTableDataChanged();
      } catch (Exception e) {
         // TODO: handle exception
         System.out.println("검색 실패 " + e.getMessage());
      }
   }

   void setStyle() { // 수정하지 못하게 함.
      tfCompanyNo.setEditable(false);
   }

//   void selectTable()      // 입력 이벤트 이 후에 주석없애고 작업.
//   {
//      int sel = comVideoSearch.getSelectedIndex();    // 몇번을 선택했는지 순서 얻어오기
//      String text = tfVideoSearch.getText();
//      
//      try {
//         ArrayList list = dao.videoSearch(sel, text);
//         tmVideo.data = list;
//         tableVideo.setModel(tmVideo);
//         tmVideo.fireTableDataChanged(); // 내용 바뀐 것을 화면에 알려주는 것
//      } catch(Exception ex) {
//         JOptionPane.showMessageDialog(null, "검색 실패" + ex.getMessage());
//      }
//   }   

   void clearScreen() { // 입력 후 입력 받은 자리 지우기.
      // TODO Auto-generated method stub
      tfComPanyMGRName.setText("");
      tfComPanyMGRTel.setText("");
      tfCompanyAddr.setText("");
      tfCompanyTel.setText("");
      tfCompanyName.setText("");
      tfCompanyNo.setText("");
   }
}

class EquipTableModel extends AbstractTableModel {

   ArrayList data = new ArrayList();
   String[] columnNames = { "거래처 번호", "업체 명", "업체 연락처", "담당자 명", "담당자 연락처", "업체 주소" };

   public int getColumnCount() {
      return columnNames.length;
   }

   public int getRowCount() {
      return data.size();
   }
   @Override
   public Object getValueAt(int row, int col) {
      ArrayList temp = (ArrayList) data.get(row);
      return temp.get(col);
   }

   public String getColumnName(int col) {
      return columnNames[col];
   }


}