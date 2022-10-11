package view.tab;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import view.ManagementView;
import view.model.WorkCompleteDAO;
import view.model.WorkPlanDAO;
import view.model.WorkTeamDAO;
import view.model.rec.WorkVO;
import view.model.rec.WorkerTeamVO;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;

public class WorkPlanViewTab extends JPanel implements ActionListener{
	private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	ImageIcon icon_search = new ImageIcon(img_search);

	JTextField tfTeamNo, tfSearch, tfActualStartDate, tfActualEndDate, tfMaterCount, tfEquipTime, tfWorkName,
			tfSelecMater, tfSelecEquip;
	JTable tbMaterial, tbEquip, tbAddMater, tbAddEquip, tbSearchWork;

	JButton btnInsert, btnModify, btnDelete, btnPlaneStart, btEquipSelect, btEquipAdd, btMaterSelect, btMaterAdd,
			btMaterDelete, btEquipDelete, btSearchWork, btnworkComplete;
	WorkPlanDAO dao = null;
	JComboBox cbSearch;
	MaterialTableModel matModel = null;
	EquipmentTableModel equipModel = null;
	AllWorkTableModel allworkModel = null;
	DefaultTableModel dtmodel = null;
	DefaultTableModel dtmodel2 = null;
	String resultdata = null;
	String resultdata2 = null;
	WorkPlanViewTab vt = null;
	WorkVO vo = null;
	WorkCompleteViewTab wc = null;
	WorkCompleteDAO wcDao = null;
	WorkTeamDAO teamDao = null;

   public WorkPlanViewTab() {
      addLayout();
      eventProc();
      setStyle();
      
      
         try {
               dao = new WorkPlanDAO();
               System.out.println("예정 작업 연결");
            }catch (Exception e) {
               // TODO: handle exception
               JOptionPane.showMessageDialog(null, "예정 작업 연결 실패 : " + e.getMessage());
            }
   }
   
   void addLayout() {
      setBounds(262, 10, 1026, 680);
      setBackground(Color.gray);
      setLayout(null);

      btnInsert = new JButton("등록");
      btnInsert.setFont(new Font("굴림", Font.BOLD, 15));
      btnInsert.setBackground(new Color(144, 238, 144));
      btnInsert.setForeground(new Color(255, 255, 255));
      btnInsert.setBounds(22, 20, 120, 30);
      add(btnInsert);

      btnModify = new JButton("수정");
      btnModify.setForeground(new Color(255, 255, 255));
      btnModify.setFont(new Font("굴림", Font.BOLD, 15));
      btnModify.setBackground(new Color(100, 149, 237));
      btnModify.setBounds(154, 20, 120, 30);
      add(btnModify);

      btnDelete = new JButton("삭제");
      btnDelete.setForeground(new Color(255, 255, 255));
      btnDelete.setFont(new Font("굴림", Font.BOLD, 15));
      btnDelete.setBackground(new Color(255, 182, 193));
      btnDelete.setBounds(286, 20, 120, 30);
      add(btnDelete);
      
      tfSearch = new JTextField();
      tfSearch.setColumns(10);
      tfSearch.setBounds(521, 20, 255, 30);
      add(tfSearch);

      JLabel lbSearch = new JLabel(icon_search);
      lbSearch.setBounds(781, 25, 25, 25);
      add(lbSearch);

      String SearchList[] = { "작업명", "팀인원" };
      cbSearch = new JComboBox(SearchList);
      cbSearch.setForeground(Color.DARK_GRAY);
      cbSearch.setBackground(Color.LIGHT_GRAY);
      cbSearch.setFont(new Font("굴림", Font.BOLD, 12));
      cbSearch.setBounds(431, 20, 82, 30);
      add(cbSearch);



      btnPlaneStart = new JButton("작업 시작");
      btnPlaneStart.setForeground(Color.WHITE);
      btnPlaneStart.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      btnPlaneStart.setBackground(Color.ORANGE);
      btnPlaneStart.setBounds(817, 73, 196, 40);
      add(btnPlaneStart);

      JPanel panel = new JPanel();
      panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
      panel.setBackground(Color.GRAY);
      panel.setBounds(12, 60, 405, 98);
      add(panel);
      panel.setLayout(null);

      JLabel lbWorkName = new JLabel("작업명");
      lbWorkName.setBounds(34, 10, 100, 18);
      panel.add(lbWorkName);
      lbWorkName.setForeground(Color.WHITE);
      lbWorkName.setFont(new Font("굴림", Font.BOLD, 15));

      JLabel lbTeamNo = new JLabel("팀 번호");
      lbTeamNo.setBounds(34, 30, 100, 18);
      panel.add(lbTeamNo);
      lbTeamNo.setForeground(Color.WHITE);
      lbTeamNo.setFont(new Font("굴림", Font.BOLD, 15));

      tfTeamNo = new JTextField(resultdata);
      tfTeamNo.setBounds(159, 30, 221, 18);
      panel.add(tfTeamNo);
      tfTeamNo.setColumns(10);

      JLabel lbActualStartDate = new JLabel("시작예정일");
      lbActualStartDate.setBounds(24, 50, 100, 18);
      panel.add(lbActualStartDate);
      lbActualStartDate.setForeground(Color.WHITE);
      lbActualStartDate.setFont(new Font("굴림", Font.BOLD, 15));

      tfActualStartDate = new JTextField();
      tfActualStartDate.setBounds(159, 50, 221, 18);
      panel.add(tfActualStartDate);
      tfActualStartDate.setColumns(10);

      JLabel lbActualEndDate = new JLabel("종료예정일");
      lbActualEndDate.setBounds(24, 70, 100, 18);
      panel.add(lbActualEndDate);
      lbActualEndDate.setForeground(Color.WHITE);
      lbActualEndDate.setFont(new Font("굴림", Font.BOLD, 15));

      tfActualEndDate = new JTextField();
      tfActualEndDate.setBounds(159, 70, 221, 18);
      panel.add(tfActualEndDate);
      tfActualEndDate.setColumns(10);
      
      tfWorkName = new JTextField();
      tfWorkName.setBounds(159, 10, 221, 18);
      panel.add(tfWorkName);
      tfWorkName.setColumns(10);

      JPanel panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
      panel_1.setBackground(Color.GRAY);
      panel_1.setBounds(12, 418, 1020, 238);
      add(panel_1);

      JLabel lbEquipmentList = new JLabel("중장비 리스트");
      lbEquipmentList.setBounds(12, 6, 115, 18);
      panel_1.add(lbEquipmentList);
      lbEquipmentList.setForeground(Color.WHITE);
      lbEquipmentList.setFont(new Font("굴림", Font.BOLD, 15));

      btEquipSelect = new JButton("조회");
      btEquipSelect.setBounds(170, 3, 120, 20);
      panel_1.add(btEquipSelect);

      JLabel lblNewLabel_1 = new JLabel("사용시간");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setForeground(Color.WHITE);
      lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
      lblNewLabel_1.setBounds(465, 80, 78, 20);
      panel_1.add(lblNewLabel_1);

      tfEquipTime = new JTextField();
      tfEquipTime.setFont(new Font("굴림", Font.BOLD, 12));
      tfEquipTime.setForeground(Color.RED);
      tfEquipTime.setBackground(Color.WHITE);
      tfEquipTime.setColumns(10);
      tfEquipTime.setBounds(472, 105, 60, 20);
      panel_1.add(tfEquipTime);

      btEquipAdd = new JButton("추가");
      btEquipAdd.setBounds(472, 132, 60, 20);
      panel_1.add(btEquipAdd);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.getViewport().setBackground(Color.GRAY);
      scrollPane_1.setBounds(11, 32, 450, 196);
      panel_1.add(scrollPane_1);
      
      tbEquip = new JTable();
      tbEquip.setForeground(Color.WHITE);
      tbEquip.setBackground(Color.GRAY);
      scrollPane_1.setViewportView(tbEquip);
      
      JScrollPane scrollPane_3 = new JScrollPane();
      scrollPane_3.getViewport().setBackground(Color.GRAY);
      scrollPane_3.setBounds(545, 32, 463, 194);
      panel_1.add(scrollPane_3);
      
      String equipHeader[] = { "거래처명", "중장비번호", "중장비명", "사용시간" };
      dtmodel2 = new DefaultTableModel(equipHeader, 0) {
         public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
         }
      };
      tbAddEquip = new JTable(dtmodel2);
      tbAddEquip.setForeground(Color.WHITE);
      tbAddEquip.setBackground(Color.GRAY);
      //tbAddEquip.setEnabled(false);
      scrollPane_3.setViewportView(tbAddEquip);
      
      tfSelecEquip = new JTextField(){
    	  @Override
    	  public void setBorder(Border border) {
              
          }
      };
      tfSelecEquip.setForeground(Color.WHITE);
      tfSelecEquip.setBackground(Color.GRAY);
      tfSelecEquip.setEditable(false);
      tfSelecEquip.setColumns(10);
      tfSelecEquip.setBounds(435, 4, 25, 25);
      panel_1.add(tfSelecEquip);
      
      JLabel lbMaterialList_1_1 = new JLabel("작업 중장비 리스트");
      lbMaterialList_1_1.setForeground(Color.WHITE);
      lbMaterialList_1_1.setFont(new Font("굴림", Font.BOLD, 15));
      lbMaterialList_1_1.setBounds(545, 6, 140, 18);
      panel_1.add(lbMaterialList_1_1);
      
      btEquipDelete = new JButton("삭제");
      btEquipDelete.setBounds(948, 5, 60, 20);
      panel_1.add(btEquipDelete);

      JPanel panel_1_1 = new JPanel();
      panel_1_1.setLayout(null);
      panel_1_1.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
      panel_1_1.setBackground(Color.GRAY);
      panel_1_1.setBounds(12, 168, 1020, 238);
      add(panel_1_1);

      JLabel lbMaterialList = new JLabel("자재 리스트");
      lbMaterialList.setBounds(12, 6, 100, 18);
      panel_1_1.add(lbMaterialList);
      lbMaterialList.setForeground(Color.WHITE);
      lbMaterialList.setFont(new Font("굴림", Font.BOLD, 15));

      btMaterSelect = new JButton("조회");
      btMaterSelect.setBounds(170, 3, 120, 20);
      panel_1_1.add(btMaterSelect);

      btMaterAdd = new JButton("추가");
      btMaterAdd.setBounds(472, 133, 60, 20);
      panel_1_1.add(btMaterAdd);

      tfMaterCount = new JTextField();
      tfMaterCount.setFont(new Font("굴림", Font.BOLD, 12));
      tfMaterCount.setForeground(Color.RED);
      tfMaterCount.setBackground(Color.WHITE);
      tfMaterCount.setBounds(472, 106, 60, 20);
      panel_1_1.add(tfMaterCount);
      tfMaterCount.setColumns(10);

      JLabel lblNewLabel = new JLabel("수량");
      lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(472, 81, 60, 20);
      panel_1_1.add(lblNewLabel);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.getViewport().setBackground(Color.GRAY);
      scrollPane.setBounds(10, 32, 450, 196);
      panel_1_1.add(scrollPane);
      
      
      tbMaterial = new JTable();
      tbMaterial.setForeground(Color.WHITE);
      tbMaterial.setBackground(Color.GRAY);
      scrollPane.setViewportView(tbMaterial);
      
      tfSelecMater = new JTextField() {
    	  @Override
    	  public void setBorder(Border border) {
              
          }
      };
      tfSelecMater.setForeground(Color.WHITE);
      tfSelecMater.setBackground(Color.GRAY);
      tfSelecMater.setBounds(435, 4, 25, 25);
      panel_1_1.add(tfSelecMater);
      tfSelecMater.setColumns(10);
      tfSelecMater.setEditable(false);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.getViewport().setBackground(Color.GRAY);
      scrollPane_2.setBounds(544, 32, 464, 196);
      panel_1_1.add(scrollPane_2);
      
      String matHeader[] = {"자재분류 ","자재번호", "자재명", "수량"};
      // 셀 선택은 되지만, 내용 수정은 못하게 설정
      dtmodel = new DefaultTableModel(matHeader, 0) {
           public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
      tbAddMater = new JTable(dtmodel);
      tbAddMater.setForeground(Color.WHITE);
      tbAddMater.setBackground(Color.GRAY);
      //tbAddMater.setEnabled(false);
      scrollPane_2.setViewportView(tbAddMater);
      
      JLabel lbMaterialList_1 = new JLabel("작업 자재 리스트");
      lbMaterialList_1.setForeground(Color.WHITE);
      lbMaterialList_1.setFont(new Font("굴림", Font.BOLD, 15));
      lbMaterialList_1.setBounds(544, 6, 134, 18);
      panel_1_1.add(lbMaterialList_1);
      
      btMaterDelete = new JButton("삭제");
      btMaterDelete.setBounds(948, 6, 60, 20);
      panel_1_1.add(btMaterDelete);
      
      btSearchWork = new JButton("전체 작업 조회");
      btSearchWork.setForeground(Color.WHITE);
      btSearchWork.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      btSearchWork.setBackground(new Color(255, 160, 122));
      btSearchWork.setBounds(818, 20, 196, 40);
      add(btSearchWork);
      
      JScrollPane scrollPane_4 = new JScrollPane();
      scrollPane_4.setBounds(431, 60, 372, 98);
      add(scrollPane_4);
      
      tbSearchWork = new JTable();
      scrollPane_4.setViewportView(tbSearchWork);
      
      btnworkComplete = new JButton("작업 완료");
      btnworkComplete.setForeground(Color.WHITE);
      btnworkComplete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      btnworkComplete.setBackground(Color.ORANGE);
      btnworkComplete.setBounds(817, 117, 196, 40);
      add(btnworkComplete);

   }
   
   void eventProc() {
      btnInsert.addActionListener(this);
      btnModify.addActionListener(this);
      btnDelete.addActionListener(this);
      btnPlaneStart.addActionListener(this);
      btSearchWork.addActionListener(this);
      btMaterSelect.addActionListener(this);
      btMaterAdd.addActionListener(this);
      btEquipSelect.addActionListener(this);
      btEquipAdd.addActionListener(this);
      btEquipDelete.addActionListener(this);
      btMaterDelete.addActionListener(this);
      btnworkComplete.addActionListener(this);
      
      tbMaterial.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
         int row = tbMaterial.getSelectedRow();
         int col = 1;    //col은 0부터 시작
         int materNo = (Integer)tbMaterial.getValueAt(row, col);         
         tfSelecMater.setText(String.valueOf(materNo));
      }
      });
      
      tbEquip.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            int row = tbEquip.getSelectedRow();
            int col = 1;    //col은 0부터 시작
            int equipNo = (Integer)tbEquip.getValueAt(row, col);         
            tfSelecEquip.setText(String.valueOf(equipNo));
         }
      });
      
		tbSearchWork.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbSearchWork.getSelectedRow();
				int col = 0;
				int col_2 = 2;
				String workName = (String) tbSearchWork.getValueAt(row, col);
				String workState = (String) tbSearchWork.getValueAt(row, col_2);
				try {
					clearScreen();
					vo = dao.selectWork(workName);
					dao.mat_equip_View(workName, tbAddMater, tbAddEquip);
					if(workState.equals("완료")) {
						btnPlaneStart.setEnabled(false);
						btEquipAdd.setEnabled(false);
						btEquipDelete.setEnabled(false);
						btMaterAdd.setEnabled(false);
						btMaterDelete.setEnabled(false);
						btnInsert.setEnabled(false);
						btnModify.setEnabled(false);
						btnDelete.setEnabled(false);
						tfWorkName.setEditable(false);
						tfTeamNo.setEditable(false);
						tfActualEndDate.setEditable(false);
						tfActualStartDate.setEditable(false);
						tfEquipTime.setEditable(false);
						tfMaterCount.setEditable(false);
						btnworkComplete.setEnabled(false);
						btEquipSelect.setEnabled(false);
						btMaterSelect.setEnabled(false);
					}
					else if(workState.equals("진행")){
						btnPlaneStart.setEnabled(false);
						btEquipAdd.setEnabled(true);
						btEquipDelete.setEnabled(true);
						btMaterAdd.setEnabled(true);
						btMaterDelete.setEnabled(true);
						btnInsert.setEnabled(true);
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
						tfWorkName.setEditable(false);
						tfTeamNo.setEditable(false);
						tfActualEndDate.setEditable(false);
						tfActualStartDate.setEditable(false);
						tfEquipTime.setEditable(true);
						tfMaterCount.setEditable(true);
						btnworkComplete.setEnabled(true);
						btEquipSelect.setEnabled(true);
						btMaterSelect.setEnabled(true);
					}
					else {
						btnPlaneStart.setEnabled(true);
						btEquipAdd.setEnabled(true);
						btEquipDelete.setEnabled(true);
						btMaterAdd.setEnabled(true);
						btMaterDelete.setEnabled(true);
						btnInsert.setEnabled(true);
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
						tfWorkName.setEditable(true);
						tfTeamNo.setEditable(true);
						tfActualEndDate.setEditable(true);
						tfActualStartDate.setEditable(true);
						tfEquipTime.setEditable(true);
						tfMaterCount.setEditable(true);
						btnworkComplete.setEnabled(false);
						btEquipSelect.setEnabled(true);
						btMaterSelect.setEnabled(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "조회 실패" + e1.getMessage());
				}
				tfWorkName.setText(workName);
				tfTeamNo.setText(Integer.toString(vo.getTeamNo()));
				tfActualStartDate.setText(vo.getStartDate());
				tfActualEndDate.setText(vo.getEndDate());
			}
		});
   }
   
   void setStyle() {

   }
   

   
   public void actionPerformed(ActionEvent ev) {
      Object o = ev.getSource();
      matModel = new MaterialTableModel();
      equipModel = new EquipmentTableModel();
      allworkModel = new AllWorkTableModel();
      
      //자재 리스트 조회
      if (o == btMaterSelect) {
         try {
            ArrayList list = dao.materialCheck();
            matModel.data = list;
            tbMaterial.setModel(matModel);
            matModel.fireTableDataChanged();
         } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "조회 실패" + e.getMessage());
         }
      } 
      // 중장비 리스트 조회
      else if (o == btEquipSelect) {
         try {
            ArrayList list = dao.equipmentCheck();
            equipModel.data = list;
            tbEquip.setModel(equipModel);
            equipModel.fireTableDataChanged();
         } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "조회 실패" + e.getMessage());
         }
      } 
      // 작업자재추가
      else if (o == btMaterAdd) {
         try {
            int SelNum = Integer.parseInt(tfSelecMater.getText());
            int matCount = Integer.parseInt(tfMaterCount.getText());
            dao.addMaterial(SelNum, matCount, tbAddMater);
            tfMaterCount.setText("");
         } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "수량을 입력해주세요.");
         }
      } 
      // 작업중장비추가
      else if (o == btEquipAdd) {
         try {
            int SelNum = Integer.parseInt(tfSelecEquip.getText());
            int equipTime = Integer.parseInt(tfEquipTime.getText());
            dao.addEquipment(SelNum, equipTime, tbAddEquip);
            tfEquipTime.setText("");
         } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "수량을 입력해주세요.");
         }
      } 
      // 작업자재삭제
      else if (o == btMaterDelete) {
         try {
            int row = tbAddMater.getSelectedRow();
            int col = 1;    //col은 0부터 시작
            dtmodel.removeRow(row);
         }catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "삭제할 자재를 선택해주세요." );
         }
      }  
      // 작업중장비삭제
      else if(o == btEquipDelete) {
         try {
            int row = tbAddEquip.getSelectedRow();
            int col = 1;    //col은 0부터 시작
            dtmodel2.removeRow(row);
         }catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "삭제할 중장비를 선택해주세요." );
         }
      }
      else if(o == btnInsert) {
    	  String workName = tfWorkName.getText();
    	  int teamNo = Integer.parseInt(tfTeamNo.getText());
    	  String startDate = tfActualStartDate.getText();
    	  String endDate = tfActualEndDate.getText();
    	  
    	  System.out.println(startDate);
    	  System.out.println(endDate);
    	  ArrayList matList = new ArrayList<>();
    	  for(int i = 0; i<tbAddMater.getRowCount(); i++) {
    		  int col = 1;
    		  String count = String.valueOf(tbAddMater.getValueAt(i, 3));
    		  matList.add(tbAddMater.getValueAt(i, col) +"#" +count);
    	  }
    	  ArrayList equipList = new ArrayList<>();
    	  for(int i = 0; i<tbAddEquip.getRowCount(); i++) {
    		  int col = 1;
    		  String count = String.valueOf(tbAddEquip.getValueAt(i, 3));
    		  equipList.add(tbAddEquip.getValueAt(i, col) +"#" +count);
    	  }

    	  vo = new WorkVO(workName, teamNo, startDate, endDate, matList, equipList);
    	  try {
    		  dao.WorkInsert(vo);
    		  clearScreen();
    		  JOptionPane.showMessageDialog(null, "등록 성공!!" );
    	  }catch(Exception e){
              JOptionPane.showMessageDialog(null, "등록 실패" + e.getMessage() );
    	  }
      }
      else if(o == btSearchWork) {
    	  try {
    		  ArrayList list = dao.allWorkSearch();
    		  allworkModel.data = list;
              tbSearchWork.setModel(allworkModel);
              allworkModel.fireTableDataChanged();
				btnPlaneStart.setEnabled(true);
				btEquipAdd.setEnabled(true);
				btEquipDelete.setEnabled(true);
				btMaterAdd.setEnabled(true);
				btMaterDelete.setEnabled(true);
				btnInsert.setEnabled(true);
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				tfWorkName.setEditable(true);
				tfTeamNo.setEditable(true);
				tfActualEndDate.setEditable(true);
				tfActualStartDate.setEditable(true);
				tfEquipTime.setEditable(true);
				tfMaterCount.setEditable(true);
				btnworkComplete.setEnabled(false);
				btEquipSelect.setEnabled(true);
				btMaterSelect.setEnabled(true);
              clearScreen();
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "검색 실패" + e.getMessage() );
		}
      }
      else if(o == btnModify) {
    	  String workName = tfWorkName.getText();
    	  int teamNo = Integer.parseInt(tfTeamNo.getText());
    	  String startDate = tfActualStartDate.getText();
    	  String endDate = tfActualEndDate.getText();
    	  
    	  ArrayList matList = new ArrayList<>();
    	  for(int i = 0; i<tbAddMater.getRowCount(); i++) {
    		  int col = 1;
    		  String count = String.valueOf(tbAddMater.getValueAt(i, 3));
    		  matList.add(tbAddMater.getValueAt(i, col) +"#" +count);
    	  }
    	  ArrayList equipList = new ArrayList<>();
    	  for(int i = 0; i<tbAddEquip.getRowCount(); i++) {
    		  int col = 1;
    		  String count = String.valueOf(tbAddEquip.getValueAt(i, 3));
    		  equipList.add(tbAddEquip.getValueAt(i, col) +"#" +count);
    	  }

    	  WorkVO vo = new WorkVO(workName, teamNo, startDate, endDate, matList, equipList);
    	  try {
    		  //dao.deleteMatEquip(workName);
    		  dao.WorkUpdate(vo);
    		  clearScreen();
    		  JOptionPane.showMessageDialog(null, "수정 완료" );
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "수정 실패" + e.getMessage() );
		}
      }
      else if (o == btnDelete) {
    	  String workName = tfWorkName.getText();
    	  int teamNo =  Integer.parseInt(tfTeamNo.getText());
    	  try {
    		  dao.deleteWork(workName, teamNo);
    		  clearScreen();
              int row = tbSearchWork.getSelectedRow();
              int col = 1;    //col은 0부터 시작
              allworkModel.fireTableDataChanged();
    		  JOptionPane.showMessageDialog(null, "삭제 완료" );
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage() );
		}
      }
      else if(o == btnPlaneStart) {
    	  
			try {
				int row = tbSearchWork.getSelectedRow();
				int col = 0;
				String data = (String) tbSearchWork.getValueAt(row, col);
				dao.workProgress(data);

				ArrayList list = dao.allWorkSearch();
				allworkModel.data = list;
				tbSearchWork.setModel(allworkModel);
				allworkModel.fireTableDataChanged();
				clearScreen();

				
				JOptionPane.showMessageDialog(null, "작업이 시작되었습니다!!" );
             
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "작업 시작 실패" + e.getMessage() );
		}
      }
      else if(o == btnworkComplete) {
    	  try {
    		  
				int row = tbSearchWork.getSelectedRow();
				int col = 0;
				String data = (String) tbSearchWork.getValueAt(row, col);
    		  vo.setWorkName(data);
    		  String workName = vo.getWorkName();
    		  
    		  WorkCompleteMemo memo = new WorkCompleteMemo(workName);
    		  memo.setVisible(true);
    		  clearScreen();
    		  wcDao.allWorkView();
    		  
    	  }catch (Exception e) {
			// TODO: handle exception
		}
      }
   }
   
   public void clearScreen() {
	   tfWorkName.setText("");
	   tfTeamNo.setText("");
	   tfActualStartDate.setText("");
	   tfActualEndDate.setText("");
	   dtmodel.setNumRows(0);
	   dtmodel2.setNumRows(0);
   }
}

// 자재리스트
class MaterialTableModel extends AbstractTableModel{
   ArrayList data = new ArrayList();
   String [] columnNames = {"자재분류 ","자재번호", "자재명", "거래처명"};
    public int getColumnCount() { 
        return columnNames.length; 
    } 
     
    public int getRowCount() { 
        return data.size(); 
    } 

    public Object getValueAt(int row, int col) { 
       ArrayList temp = (ArrayList)data.get( row );
        return temp.get( col ); 
    }
    
    public String getColumnName(int col) { 
        return columnNames[col]; 
    } 
}

//중장비 리스트
class EquipmentTableModel extends AbstractTableModel{
   ArrayList data = new ArrayList();
   String [] columnNames = {"거래처명", "중장비번호", "중장비명"};
    public int getColumnCount() { 
        return columnNames.length; 
    } 
     
    public int getRowCount() { 
        return data.size(); 
    } 

    public Object getValueAt(int row, int col) { 
       ArrayList temp = (ArrayList)data.get( row );
        return temp.get( col ); 
    }

    public String getColumnName(int col) { 
        return columnNames[col]; 
    } 
}

//전체 작업 리스트
class AllWorkTableModel extends AbstractTableModel{
 ArrayList data = new ArrayList();
 String [] columnNames = {"작업명", "담당팀인원" ,"상태"};
  public int getColumnCount() { 
      return columnNames.length; 
  } 
   
  public int getRowCount() { 
      return data.size(); 
  } 

  public Object getValueAt(int row, int col) { 
     ArrayList temp = (ArrayList)data.get( row );
      return temp.get( col ); 
  }

  public String getColumnName(int col) { 
      return columnNames[col]; 
  } 
}

