package view.tab;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import view.ManagementView;
import view.model.WorkPlanDAO;
import view.model.WorkTeamDAO;
import view.model.rec.WorkerTeamVO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;

public class WorkTeamViewTab extends JPanel implements ActionListener {
	
	private Image img_arrow = new ImageIcon(ManagementView.class.getResource("/res/arrow.png")).getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);
	private Image img_arrow_ver = new ImageIcon(ManagementView.class.getResource("/res/arrow_vertical.png")).getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
	
	ImageIcon icon_arrow= new ImageIcon(img_arrow);
	ImageIcon icon_arrow_ver= new ImageIcon(img_arrow_ver);
	
	JLabel lbworkName_1, lbworkName_2;
   JTextField tfNewTeamName, tfTotalWorkerCount;
   JButton btnInsertTeam, btnInsertTeamList, btDeleteWorker, btnRefresh, btnDeleteTeam;
   JButton btnrefresh2;
   WorkTeamDAO dao = null;
   JTable tbWorkerList, tbAddTeam, tbTeamList;
   DefaultTableModel dtmodel = null;
   DefaultTableModel dtmodelworker = null;
   DefaultTableModel dtmodelTeam = null;
   DefaultTableModel dtmodelTeamInfo = null;
   WorkerTeamVO vo = new WorkerTeamVO();
   int count = 0;
   private JTable tbTeamWorkerInfo;
   String wName;


   public WorkTeamViewTab() {

      addLayout();
      eventProc();
      setStyle();
      buttonEvent();

      try {
         dao = new WorkTeamDAO();
         System.out.println("팀 편성 연결");
         dao.workerList(tbWorkerList);
         dao.finalTeamList(tbTeamList);
         dtmodelTeam.fireTableDataChanged();
         
         JLabel lbArrow = new JLabel(icon_arrow);
         lbArrow.setBounds(316, 264, 57, 60);
         add(lbArrow);
         
         JLabel lbArrow_1 = new JLabel(icon_arrow);
         lbArrow_1.setBounds(655, 264, 57, 60);
         add(lbArrow_1);
      } catch (Exception e) {
         // TODO: handle exception
         JOptionPane.showMessageDialog(null, "팀 편성 연결 실패 : " + e.getMessage());
      }
   }

   void addLayout() {
      setBounds(262, 10, 1026, 680);
      setBackground(Color.gray);
      setLayout(null);

      JPanel panelWorker = new JPanel();
      panelWorker.setBounds(26, 58, 294, 574);
      panelWorker.setBorder(new LineBorder(Color.WHITE, 1, true));
      panelWorker.setBackground(Color.GRAY);
      add(panelWorker);
      panelWorker.setLayout(null);

      btnInsertTeam = new JButton("인원 추가");
      btnInsertTeam.setBounds(162, 539, 120, 25);
      panelWorker.add(btnInsertTeam);
      btnInsertTeam.setForeground(Color.BLACK);
      btnInsertTeam.setBackground(Color.LIGHT_GRAY);
      btnInsertTeam.setFont(new Font("굴림", Font.BOLD, 15));

      JLabel lblWorkList = new JLabel("인력 LIST");
      lblWorkList.setForeground(Color.WHITE);
      lblWorkList.setFont(new Font("굴림", Font.BOLD, 15));
      lblWorkList.setBounds(12, 28, 92, 22);
      panelWorker.add(lblWorkList);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 63, 270, 466);
      scrollPane.getViewport().setBackground(Color.GRAY);
      panelWorker.add(scrollPane);
     
      String workerHeader[] = {"인력번호 ","인력명", "전문분야", "경력"};
      dtmodelworker = new DefaultTableModel(workerHeader, 0) {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      };
      tbWorkerList = new JTable(dtmodelworker);
      tbWorkerList.setForeground(Color.WHITE);
      tbWorkerList.setBackground(Color.GRAY);
      scrollPane.setViewportView(tbWorkerList);
      
      btnrefresh2 = new JButton("새로고침");
      btnrefresh2.setForeground(Color.BLACK);
      btnrefresh2.setFont(new Font("굴림", Font.BOLD, 15));
      btnrefresh2.setBackground(Color.LIGHT_GRAY);
      btnrefresh2.setBounds(12, 540, 120, 25);
      panelWorker.add(btnrefresh2);

      JPanel panelTeamProduce = new JPanel();
      panelTeamProduce.setBounds(366, 58, 294, 574);
      panelTeamProduce.setBorder(new LineBorder(Color.WHITE, 1, true));
      panelTeamProduce.setBackground(Color.GRAY);
      add(panelTeamProduce);
      panelTeamProduce.setLayout(null);

      JLabel lbNewTeamName = new JLabel("팀 이름");
      lbNewTeamName.setFont(new Font("굴림", Font.BOLD, 15));
      lbNewTeamName.setForeground(Color.WHITE);
      lbNewTeamName.setBounds(12, 30, 60, 20);
      panelTeamProduce.add(lbNewTeamName);

      tfNewTeamName = new JTextField();
      tfNewTeamName.setBounds(80, 30, 120, 20);
      panelTeamProduce.add(tfNewTeamName);
      tfNewTeamName.setColumns(10);

      JLabel lbTotalWorkerCount = new JLabel("총인원");
      lbTotalWorkerCount.setFont(new Font("굴림", Font.BOLD, 16));
      lbTotalWorkerCount.setForeground(Color.WHITE);
      lbTotalWorkerCount.setBounds(12, 490, 55, 20);
      panelTeamProduce.add(lbTotalWorkerCount);

      tfTotalWorkerCount = new JTextField();
      tfTotalWorkerCount.setBounds(80, 490, 40, 20);
      panelTeamProduce.add(tfTotalWorkerCount);
      tfTotalWorkerCount.setColumns(10);

      btnInsertTeamList = new JButton("팀 생성");
      btnInsertTeamList.setFont(new Font("굴림", Font.BOLD, 15));
      btnInsertTeamList.setBackground(Color.LIGHT_GRAY);
      btnInsertTeamList.setBounds(162, 540, 120, 25);
      panelTeamProduce.add(btnInsertTeamList);
      
      JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.getViewport().setBackground(Color.GRAY);
      scrollPane_1.setBounds(12, 63, 270, 420);
      panelTeamProduce.add(scrollPane_1);
      
      String addTeamHeader[] = {"인력번호 ","인력명", "전문분야", "경력"};
      dtmodel = new DefaultTableModel(addTeamHeader, 0) {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      };
      tbAddTeam = new JTable(dtmodel);
      tbAddTeam.setForeground(Color.WHITE);
      tbAddTeam.setBackground(Color.GRAY);
      scrollPane_1.setViewportView(tbAddTeam);
      
      btDeleteWorker = new JButton("인원 제외");
      btDeleteWorker.setForeground(Color.BLACK);
      btDeleteWorker.setFont(new Font("굴림", Font.BOLD, 15));
      btDeleteWorker.setBackground(Color.LIGHT_GRAY);
      btDeleteWorker.setBounds(12, 540, 120, 25);
      panelTeamProduce.add(btDeleteWorker);

      JPanel panelTeamList = new JPanel();
      panelTeamList.setBounds(706, 58, 320, 574);
      panelTeamList.setBorder(new LineBorder(Color.WHITE, 1, true));
      panelTeamList.setBackground(Color.GRAY);
      add(panelTeamList);
      panelTeamList.setLayout(null);

      JLabel lbTeamList = new JLabel("팀 LIST");
      lbTeamList.setFont(new Font("굴림", Font.BOLD, 15));
      lbTeamList.setForeground(Color.WHITE);
      lbTeamList.setBounds(12, 30, 65, 22);
      panelTeamList.add(lbTeamList);
      
      JScrollPane scrollPane_2 = new JScrollPane();
      scrollPane_2.getViewport().setBackground(Color.GRAY);
      scrollPane_2.setBounds(12, 63, 296, 249);
      panelTeamList.add(scrollPane_2);
      
      String TeamListHeader[] = {"팀번호 ","팀명",  "인원", "배정상태"};
      dtmodelTeam = new DefaultTableModel(TeamListHeader, 0) {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      };
      tbTeamList = new JTable(dtmodelTeam);
      tbTeamList.setForeground(Color.WHITE);
      tbTeamList.setBackground(Color.GRAY);
      scrollPane_2.setViewportView(tbTeamList);
      
      JLabel lbTeamList_1 = new JLabel("팀원 정보");
      lbTeamList_1.setForeground(Color.WHITE);
      lbTeamList_1.setFont(new Font("굴림", Font.BOLD, 15));
      lbTeamList_1.setBounds(12, 364, 78, 22);
      panelTeamList.add(lbTeamList_1);
      
      JScrollPane scrollPane_3 = new JScrollPane();
      scrollPane_3.getViewport().setBackground(Color.GRAY);
      scrollPane_3.setBounds(12, 396, 296, 133);
      panelTeamList.add(scrollPane_3);
      
      
      String TeamWorkerInfoHeader[] = {"팀번호", "인력명 ","전문분야", "경력", "안전교육이수여부"};
      dtmodelTeamInfo = new DefaultTableModel(TeamWorkerInfoHeader, 0) {
          public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
          }
      };
      tbTeamWorkerInfo = new JTable(dtmodelTeamInfo);
      tbTeamWorkerInfo.setForeground(Color.WHITE);
      tbTeamWorkerInfo.setBackground(Color.GRAY);
      scrollPane_3.setViewportView(tbTeamWorkerInfo);
      
      JLabel lbArrow_1 = new JLabel(icon_arrow_ver);
      lbArrow_1.setBounds(138, 342, 57, 60);
      panelTeamList.add(lbArrow_1);
      
      btnRefresh = new JButton("새로고침");
      btnRefresh.setForeground(Color.BLACK);
      btnRefresh.setBackground(Color.LIGHT_GRAY);
      btnRefresh.setFont(new Font("굴림", Font.BOLD, 13));
      btnRefresh.setBounds(211, 30, 97, 23);
      panelTeamList.add(btnRefresh);
      
      lbworkName_1 = new JLabel("배정된 작업 : ");
      lbworkName_1.setFont(new Font("굴림", Font.PLAIN, 13));
      lbworkName_1.setForeground(Color.WHITE);
      lbworkName_1.setBounds(76, 322, 88, 15);
      panelTeamList.add(lbworkName_1);
      
      lbworkName_2 = new JLabel();
      lbworkName_2.setForeground(Color.WHITE);
      lbworkName_2.setFont(new Font("굴림", Font.PLAIN, 13));
      lbworkName_2.setBounds(162, 322, 146, 15);
      panelTeamList.add(lbworkName_2);
      
      btnDeleteTeam = new JButton("팀 삭제");
      btnDeleteTeam.setFont(new Font("굴림", Font.BOLD, 15));
      btnDeleteTeam.setBackground(Color.LIGHT_GRAY);
      btnDeleteTeam.setBounds(188, 539, 120, 25);
      panelTeamList.add(btnDeleteTeam);
      
   }

   void eventProc() {
       btnInsertTeam.addActionListener(this);
       btnInsertTeamList.addActionListener(this);
       btDeleteWorker.addActionListener(this);
       btnRefresh.addActionListener(this);
       btnDeleteTeam.addActionListener(this);
       btnrefresh2.addActionListener(this);
       

       
       tbWorkerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbWorkerList.getSelectedRow();
				int col = 0;
				int data =Integer.parseInt(String.valueOf(tbWorkerList.getValueAt(row, col)));
				buttonEvent();
				vo.setWorkerNo(data);
			}
		});
       
       tbAddTeam.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbAddTeam.getSelectedRow();
				int col = 0;
				int data =Integer.parseInt(String.valueOf(tbAddTeam.getValueAt(row, col)));
				buttonEvent();
				vo.setWorkerNo(data);
			}
		});
       
       tbTeamList.addMouseListener(new MouseAdapter() {
    	   public void mouseClicked(MouseEvent e) {
    			int row = tbTeamList.getSelectedRow();
				int col = 0;
				int data =Integer.parseInt(String.valueOf(tbTeamList.getValueAt(row, col)));
				
				System.out.println(data);
				try {
					dao.teamInfo(data, tbTeamWorkerInfo);
					wName = dao.workNameInfo(data);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "팀인원 조회 실패" + e1.getMessage());
				}
				lbworkName_2.setText(wName);
    	   }
	});

   }

   void setStyle() {
	   tfTotalWorkerCount.setEditable(false);

   }
   
   void buttonEvent() {
		if (tbWorkerList.getSelectedRow() == -1) {
			btnInsertTeam.setEnabled(false);
		} else 
			btnInsertTeam.setEnabled(true);

		 
		if (tbAddTeam.getSelectedRow() == -1) {
			btDeleteWorker.setEnabled(false);
		} else
			btDeleteWorker.setEnabled(true);
		
		if(tbAddTeam.getRowCount() <= 3) {
			btnInsertTeamList.setEnabled(false);
		} else
			btnInsertTeamList.setEnabled(true);
	}

   public void actionPerformed(ActionEvent ev) {
	   Object o = ev.getSource();

      if( o == btnInsertTeam) {
    	  try {

    		  int row = tbWorkerList.getSelectedRow();
    		  int workerNo = vo.getWorkerNo();
    		  dao.addteamList(workerNo, tbAddTeam, tbWorkerList, row);
    		  buttonEvent();
    		  ++count;
    		  tfTotalWorkerCount.setText(Integer.toString(count));
    		  
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "추가 실패" + e.getMessage());
		}
      }
      else if( o == btDeleteWorker) {
    	  try {
    		  int row = tbAddTeam.getSelectedRow();
    		  int workerNo = vo.getWorkerNo();
    		  dao.deleteteamList(workerNo, tbAddTeam, tbWorkerList, row);
    		  buttonEvent();
    		  --count;
    		  tfTotalWorkerCount.setText(Integer.toString(count));
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage());
		}
      }
      else if( o == btnInsertTeamList) {
    	  try {
    		  String teamName = tfNewTeamName.getText();
    		  dao.createTeam(tbAddTeam, tbTeamList, teamName);
    		  buttonEvent();
    		  tfNewTeamName.setText("");
    		  tfTotalWorkerCount.setText("");
    		  count = 0;
    		  JOptionPane.showMessageDialog(null, teamName + "팀생성 완료!");
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "팀생성 실패" + e.getMessage());
		}
      }
      else if(o == btnRefresh) {
    	  try {
    		  dtmodelTeam.setNumRows(0);
    		  dao.finalTeamList(tbTeamList);
    		  dtmodelTeam.fireTableDataChanged();
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "새로고침 실패" + e.getMessage());
		}
      }
      else if(o == btnDeleteTeam) {
    	  try {
              int row = tbTeamList.getSelectedRow();
              int col = 0;    //col은 0부터 시작
              int teamNo = Integer.parseInt((String)tbTeamList.getValueAt(row, col));
              dao.deleteTeam(teamNo);
              dtmodelTeam.removeRow(row);
              dtmodelTeamInfo.setRowCount(0);
              dtmodelworker.setRowCount(0);
              dao.workerList(tbWorkerList);
              JOptionPane.showMessageDialog(null, "삭제 완료" );
    	  }catch (Exception e) {
			// TODO: handle exception
    		  JOptionPane.showMessageDialog(null, "삭제 실패" + e.getMessage());
		}
      }else if (o == btnrefresh2) {
    	  try {
    		  dtmodelworker.setNumRows(0);
    		  System.out.println("123");
    		  dao.workerList(tbWorkerList);
		} catch (Exception e) {
			// TODO: handle exception
		}
      }
}
}
