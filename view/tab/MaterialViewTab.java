package view.tab;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import view.model.*;
import view.model.MaterialDAO;
import view.model.rec.CommuteVO;
import view.model.rec.MaterialOrderVO;
import view.model.rec.MaterialVO;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;
import javax.swing.table.*;

public class MaterialViewTab extends JPanel implements ActionListener {

   private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
         .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
   ImageIcon icon_search = new ImageIcon(img_search);

   JTextField tfMatSearch, tfTypeNo, tfMaterialName, tfCount, tfMaterialNo, tfPrice, tfCompanyNo;

   JButton btnInsert, btnModify, btnDelete, btnMaterialOrder;

   MaterialDAO dao = null;
   JComboBox mbSearch;

   JTable TableMaterial;
   MatsTableModel tmMaterial;

   JTable TableOrder;
   OrderTableModel tmOrder;
   InstockTableModel tmInstock;
   JTextField tfOrder;

   /**
    * Create the panel.
    */
   public MaterialViewTab() {
      try {
         dao = new MaterialDAO();
         System.out.println("자재 DB연결 성공");
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "자재 DB 연결 실패 : " + e.getMessage());
      }
      addLayout();
      eventProc();
      setStyle();

   }

   public void addLayout() {
      setBounds(262, 10, 1026, 680);

      setBackground(Color.gray);
      setLayout(null);

      btnInsert = new JButton("등록");
      btnInsert.setBounds(465, 134, 120, 30);
      btnInsert.setFont(new Font("굴림", Font.BOLD, 15));
      btnInsert.setBackground(new Color(144, 238, 144));
      btnInsert.setForeground(new Color(255, 255, 255));
      add(btnInsert);

      btnModify = new JButton("수정");
      btnModify.setBounds(609, 134, 120, 30);
      btnModify.setForeground(new Color(255, 255, 255));
      btnModify.setFont(new Font("굴림", Font.BOLD, 15));
      btnModify.setBackground(new Color(100, 149, 237));
      add(btnModify);

      btnDelete = new JButton("삭제");
      btnDelete.setBounds(754, 134, 120, 30);
      btnDelete.setForeground(new Color(255, 255, 255));
      btnDelete.setFont(new Font("굴림", Font.BOLD, 15));
      btnDelete.setBackground(new Color(255, 182, 193));
      add(btnDelete);

      JLabel lbSearch = new JLabel(icon_search);
      lbSearch.setBounds(428, 134, 25, 25);
      add(lbSearch);

      String SearchList[] = { "자재명" };
      mbSearch = new JComboBox(SearchList);
      mbSearch.setBounds(12, 134, 91, 30);
      add(mbSearch);

      tfMatSearch = new JTextField();
      tfMatSearch.setColumns(10);
      tfMatSearch.setBounds(115, 135, 266, 30);
      add(tfMatSearch);

      JPanel panel = new JPanel();
      panel.setBounds(12, 22, 880, 110);
      panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
      panel.setBackground(Color.GRAY);
      add(panel);
      panel.setLayout(null);

      JLabel lbMaterialNo = new JLabel("자재 번호");
      lbMaterialNo.setBounds(32, 19, 100, 18);
      panel.add(lbMaterialNo);
      lbMaterialNo.setForeground(Color.WHITE);
      lbMaterialNo.setFont(new Font("굴림", Font.BOLD, 15));

      tfMaterialNo = new JTextField();
      tfMaterialNo.setBounds(157, 16, 221, 18);
      panel.add(tfMaterialNo);
      tfMaterialNo.setColumns(10);

      JLabel lbMaterialName = new JLabel("자재 명");
      lbMaterialName.setForeground(Color.WHITE);
      lbMaterialName.setFont(new Font("굴림", Font.BOLD, 15));
      lbMaterialName.setBounds(32, 46, 100, 18);
      panel.add(lbMaterialName);

      JLabel lbTypeNo = new JLabel("분야 번호");
      lbTypeNo.setForeground(Color.WHITE);
      lbTypeNo.setFont(new Font("굴림", Font.BOLD, 15));
      lbTypeNo.setBounds(32, 74, 100, 18);
      panel.add(lbTypeNo);

      tfMaterialName = new JTextField();
      tfMaterialName.setColumns(10);
      tfMaterialName.setBounds(157, 46, 221, 18);
      panel.add(tfMaterialName);

      tfTypeNo = new JTextField();
      tfTypeNo.setColumns(10);
      tfTypeNo.setBounds(157, 74, 221, 18);
      panel.add(tfTypeNo);

      JLabel lbCount = new JLabel("자재 수량");
      lbCount.setBounds(480, 16, 100, 18);
      panel.add(lbCount);
      lbCount.setForeground(Color.WHITE);
      lbCount.setFont(new Font("굴림", Font.BOLD, 15));

      tfCount = new JTextField();
      tfCount.setBounds(610, 17, 221, 18);
      panel.add(tfCount);
      tfCount.setColumns(10);

      JLabel lbPrice = new JLabel("자재 금액");
      lbPrice.setBounds(480, 46, 100, 18);
      panel.add(lbPrice);
      lbPrice.setForeground(Color.WHITE);
      lbPrice.setFont(new Font("굴림", Font.BOLD, 15));

      JLabel lbCompanyNo = new JLabel("거래처 번호");
      lbCompanyNo.setForeground(Color.WHITE);
      lbCompanyNo.setFont(new Font("굴림", Font.BOLD, 15));
      lbCompanyNo.setBounds(480, 76, 110, 18);
      panel.add(lbCompanyNo);

      JLabel lbMaterial = new JLabel("자재");
      lbMaterial.setForeground(Color.ORANGE);
      lbMaterial.setFont(new Font("굴림", Font.BOLD, 15));
      lbMaterial.setBounds(709, 92, 51, 18);
      panel.add(lbMaterial);

      tfPrice = new JTextField();
      tfPrice.setColumns(10);
      tfPrice.setBounds(610, 46, 221, 18);
      panel.add(tfPrice);

      tfCompanyNo = new JTextField();
      tfCompanyNo.setColumns(10);
      tfCompanyNo.setBounds(610, 74, 221, 18);
      panel.add(tfCompanyNo);

      btnMaterialOrder = new JButton("주문");

      btnMaterialOrder.setForeground(Color.WHITE);
      btnMaterialOrder.setFont(new Font("굴림", Font.BOLD, 22));
      btnMaterialOrder.setBackground(Color.ORANGE);
      btnMaterialOrder.setBounds(631, 421, 115, 30);
      add(btnMaterialOrder);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 202, 880, 202);
      add(scrollPane);

      tmMaterial = new MatsTableModel();
      TableMaterial = new JTable();
      TableMaterial.setModel(tmMaterial);

      scrollPane.setViewportView(TableMaterial);

      JLabel lbMatlist = new JLabel("자재 정보");
      lbMatlist.setForeground(Color.WHITE);
      lbMatlist.setFont(new Font("굴림", Font.BOLD, 15));
      lbMatlist.setBounds(22, 174, 100, 18);
      add(lbMatlist);

      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(12, 457, 692, 202);
      add(scrollPane_1);

      tmOrder = new OrderTableModel();
      TableOrder = new JTable();
      TableOrder.setModel(tmOrder);

      scrollPane_1.setViewportView(TableOrder);

      JLabel lbOrder = new JLabel("주문 내역");
      lbOrder.setForeground(Color.WHITE);
      lbOrder.setFont(new Font("굴림", Font.BOLD, 15));
      lbOrder.setBounds(12, 429, 100, 18);
      add(lbOrder);

      tmInstock = new InstockTableModel();

      tfOrder = new JTextField();
      tfOrder.setColumns(10);
      tfOrder.setBounds(488, 429, 131, 18);
      add(tfOrder);
//      tfOrder.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            MaterialOrderViewTab Mat = new MaterialOrderViewTab();
//            Mat.setVisible(true);
//            
//            int count = Integer.parseInt(Mat.tfOrderCount.getText());
//            tfOrder.setText(String.valueOf(count));
//            System.out.println(count);
//         }
//      });

      JLabel lbOrderCount = new JLabel("주문 수량");
      lbOrderCount.setForeground(Color.WHITE);
      lbOrderCount.setFont(new Font("굴림", Font.BOLD, 15));
      lbOrderCount.setBounds(388, 429, 100, 18);
      add(lbOrderCount);
      TableMaterial.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = TableMaterial.getSelectedRow();
            int col = 0;

            int MatNum = (Integer) TableMaterial.getValueAt(row, col);
            MaterialVO vo = new MaterialVO();
            try {
               vo = dao.findByNum(MatNum);
            } catch (Exception e2) {
               // TODO: handle exception
               System.out.println("마우스클릭 실패");
            }

            tfMaterialNo.setText(String.valueOf(vo.getMaterial_no()));
            tfMaterialName.setText(vo.getMat_Name());
            tfCount.setText(String.valueOf(vo.getMat_count()));
            tfTypeNo.setText(String.valueOf(vo.getMaterial_type_no()));
            tfCompanyNo.setText(String.valueOf(vo.getVender_no()));
            tfPrice.setText(String.valueOf(vo.getMat_Price()));
         }
      });

      TableOrder.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = TableOrder.getSelectedRow();
            int col = 0;

            int OrderNum = (Integer) TableOrder.getValueAt(row, col);
            MaterialOrderVO vo = new MaterialOrderVO();
            try {
               vo = dao.findByNum2(OrderNum);
            } catch (Exception e2) {
               // TODO: handle exception
               System.out.println("마우스클릭 실패");
            }

         }
      });

   }

   void eventProc() {
      btnInsert.addActionListener(this);
      btnModify.addActionListener(this);
      btnDelete.addActionListener(this);
      tfMatSearch.addActionListener(this);
      btnMaterialOrder.addActionListener(this);

   }

   public void actionPerformed(ActionEvent ev) {
      Object o = ev.getSource();

      if (o == btnInsert) {
         String name = tfMaterialName.getText();
         int type = Integer.parseInt(tfTypeNo.getText());
         int price = Integer.parseInt(tfPrice.getText());
         int count = Integer.parseInt(tfCount.getText());
         int company = Integer.parseInt(tfCompanyNo.getText());

         MaterialVO vo = new MaterialVO(name, type, price, count, company);
         try {
            dao.MaterialInsert(vo);
            System.out.println("자재 정보 입력 성공");
            clearScreen();
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "자재 정보 입력 실패" + e.getMessage());
         }
      } else if (o == btnModify) {
         String name = tfMaterialName.getText();
         int type = Integer.parseInt(tfTypeNo.getText());
         int price = Integer.parseInt(tfPrice.getText());
         int count = Integer.parseInt(tfCount.getText());
         int company = Integer.parseInt(tfCompanyNo.getText());

         MaterialVO vo = new MaterialVO(name, type, price, count, company);
         int num = Integer.parseInt(tfCompanyNo.getText());

         try {
            dao.MaterialUpdate(vo, num);
            System.out.println("자재 정보 수정 성공");
            selectTable();
         } catch (Exception e1) {
            System.out.println("자재 정보 수정 실패");
         }

      } else if (o == btnDelete) {
         int num = Integer.parseInt(tfMaterialNo.getText());
         try {
            dao.MaterialDelete(num);
            System.out.println("자재 정보 삭제 성공");
            selectTable();
         } catch (Exception e) {
            System.out.println("자재 정보 삭제 실패");
         }
      } else if (o == tfMatSearch) {
         int sel = mbSearch.getSelectedIndex();
         String text = tfMatSearch.getText();
         try {
            ArrayList list = dao.MaterialSearch(sel, text);
            tmMaterial.data = list;
            TableMaterial.setModel(tmMaterial);
            tmMaterial.fireTableDataChanged();
            System.out.println("자재 검색 성공");
         } catch (Exception e) {
            System.out.println("자재 검색 실패" + e.getMessage());
         }
      } else if (o == btnMaterialOrder) {

         int num = Integer.parseInt(tfOrder.getText());
         int num1 = Integer.parseInt(tfPrice.getText());

         int result = (num * num1);

         try {
            int num3 = Integer.parseInt(tfMaterialNo.getText());
            int count = Integer.parseInt(tfOrder.getText());
            System.out.println(num);
            System.out.println(count);
            System.out.println(result);

            dao.OrderInsert(num3, count, result);

            ArrayList list = dao.OrderSelect(num3, count, result);
            tmOrder.data = list;
            TableOrder.setModel(tmOrder);
            tmOrder.fireTableDataChanged();

            clearScreen();
            System.out.println("주문 성공");
         } catch (Exception e) {
            System.out.println("주문 실패" + e.getMessage());
         }
      }
   }

   public void selectTable() {
      int sel = mbSearch.getSelectedIndex();
      String text = tfMatSearch.getText();
      try {
         ArrayList list = dao.MaterialSearch(sel, text);
         tmMaterial.data = list;
         TableMaterial.setModel(tmMaterial);
         tmMaterial.fireTableDataChanged();
         System.out.println("새로고침 성공");
      } catch (Exception e) {
         // TODO: handle exception
         System.out.println("새로고침 실패 " + e.getMessage());
      }
   }

   void setStyle() { // 수정하지 못하게 함.
      tfMaterialNo.setEditable(false);
   }

   void clearScreen() { // 입력 후 입력 받은 자리 지우기.
      // TODO Auto-generated method stub
      tfMaterialNo.setText("");
      tfMaterialName.setText("");
      tfCount.setText("");
      tfTypeNo.setText("");
      tfPrice.setText("");
      tfCompanyNo.setText("");
      tfOrder.setText("");
   }
}

class MatsTableModel extends AbstractTableModel {

   ArrayList data = new ArrayList();
   String[] columnNames = { "자재 번호", "자재 명", "분야 번호", "자재 금액", "자재 수량", "거래처 번호" };

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

class OrderTableModel extends AbstractTableModel {

   ArrayList data = new ArrayList();
   String[] columnNames = { "주문 번호", "자재 번호", "날짜", "주문 수량", "총 주문 금액" };

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

/*
 * 학
 */
// 자재 입고 내역표
class InstockTableModel extends AbstractTableModel {

   ArrayList data = new ArrayList();
   String[] columnNames = { "입고 번호", "입고 수량", "입고 날짜", "주문 번호", "특이 사항" };

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