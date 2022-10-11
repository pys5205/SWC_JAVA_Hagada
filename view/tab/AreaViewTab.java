package view.tab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import view.model.AreaDAO;
import view.model.rec.AreaVO;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AreaViewTab extends JPanel implements ActionListener {
	private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	ImageIcon icon_search = new ImageIcon(img_search);

	JTextField tfSearch, tfAreaAddr, tfAreaDetail, tfAreaNo, tfScaffCkeck;
	JButton btnInsert, btnModify, btnDelete, btnShowDrawing, btnDrawingInsert;
	JLabel lbSearch, lbScaffCheck, lbAreaDetail, lbAreaAddr;
	JPanel panel;
	JComboBox cbSearch;

	AreaDAO dao = null;
	JTable tbAreaList;
	AreaTableModel tmArea;
	LoadImageApp img = null;
	/**
	 * Create the panel.
	 */

	public AreaViewTab() {
		try {
			dao = new AreaDAO();
		} catch (Exception e) {
			// TODO: handle exception
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
		btnInsert.setFont(new Font("굴림", Font.BOLD, 15));
		btnInsert.setBackground(new Color(144, 238, 144));
		btnInsert.setForeground(new Color(255, 255, 255));
		btnInsert.setBounds(412, 102, 115, 30);
		add(btnInsert);

		btnModify = new JButton("수정");
		btnModify.setForeground(new Color(255, 255, 255));
		btnModify.setFont(new Font("굴림", Font.BOLD, 15));
		btnModify.setBackground(new Color(100, 149, 237));
		btnModify.setBounds(535, 102, 115, 30);
		add(btnModify);

		btnDelete = new JButton("삭제");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("굴림", Font.BOLD, 15));
		btnDelete.setBackground(new Color(255, 182, 193));
		btnDelete.setBounds(657, 102, 115, 30);
		add(btnDelete);

		lbSearch = new JLabel(icon_search);
		lbSearch.setBounds(375, 102, 25, 25);
		add(lbSearch);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(102, 102, 266, 30);
		add(tfSearch);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 22, 760, 70);
		add(panel);
		panel.setLayout(null);

		lbScaffCheck = new JLabel("비계설치여부");
		lbScaffCheck.setForeground(Color.WHITE);
		lbScaffCheck.setFont(new Font("굴림", Font.BOLD, 15));
		lbScaffCheck.setBounds(430, 17, 100, 18);
		panel.add(lbScaffCheck);

		lbAreaDetail = new JLabel("공사 상세 위치");
		lbAreaDetail.setForeground(Color.WHITE);
		lbAreaDetail.setFont(new Font("굴림", Font.BOLD, 15));
		lbAreaDetail.setBounds(32, 15, 104, 18);
		panel.add(lbAreaDetail);

		lbAreaAddr = new JLabel("공사 구역 주소");
		lbAreaAddr.setForeground(Color.WHITE);
		lbAreaAddr.setFont(new Font("굴림", Font.BOLD, 15));
		lbAreaAddr.setBounds(32, 45, 104, 18);
		panel.add(lbAreaAddr);

		tfAreaAddr = new JTextField();
		tfAreaAddr.setColumns(10);
		tfAreaAddr.setBounds(157, 45, 221, 18);
		panel.add(tfAreaAddr);

		tfAreaDetail = new JTextField();
		tfAreaDetail.setColumns(10);
		tfAreaDetail.setBounds(157, 15, 221, 18);
		panel.add(tfAreaDetail);

		tfScaffCkeck = new JTextField();
		tfScaffCkeck.setColumns(10);
		tfScaffCkeck.setBounds(542, 16, 170, 18);
		panel.add(tfScaffCkeck);

		String SearchList[] = { "상세위치", "주소" };
		cbSearch = new JComboBox();
		cbSearch.setForeground(Color.DARK_GRAY);
		cbSearch.setFont(new Font("굴림", Font.BOLD, 12));
		cbSearch.setBackground(Color.LIGHT_GRAY);
		cbSearch.setBounds(12, 102, 91, 30);
		add(cbSearch);
		cbSearch.addItem("상세 위치");
		cbSearch.addItem("주소");

		btnShowDrawing = new JButton("도면보기");
		btnShowDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AreaShowDrawingTab img = new AreaShowDrawingTab();
				//img.setVisible(true);
				String data = tfAreaNo.getText();
				//img = new LoadImageApp(data);
				
			      JFrame f = new JFrame("Load Image Sample");
			      
			      f.addWindowListener(new WindowAdapter() {
			         public void windowClosing(WindowEvent e) {
			            f.dispose();
			         }
			      });
			      
			      f.add(new LoadImageApp(data));
			      f.pack();
			      f.setVisible(true);
				

			}
		});
		btnShowDrawing.setForeground(Color.WHITE);
		btnShowDrawing.setFont(new Font("굴림", Font.BOLD, 22));
		btnShowDrawing.setBackground(Color.ORANGE);
		btnShowDrawing.setBounds(784, 22, 160, 30);
		add(btnShowDrawing);

		btnDrawingInsert = new JButton("도면 추가");
		btnDrawingInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AreaDrawingInsertTab imgInsert = new AreaDrawingInsertTab();
				imgInsert.setVisible(true);
				
				imgInsert.tfAreaNo.setText(tfAreaNo.getText());

			}
		});
		btnDrawingInsert.setForeground(Color.WHITE);
		btnDrawingInsert.setFont(new Font("굴림", Font.BOLD, 22));
		btnDrawingInsert.setBackground(new Color(255, 160, 122));
		btnDrawingInsert.setBounds(784, 62, 160, 30);
		add(btnDrawingInsert);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 142, 1020, 516);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		tmArea = new AreaTableModel();
		tbAreaList = new JTable(tmArea);
		tbAreaList.setForeground(Color.WHITE);
		tbAreaList.setBackground(Color.GRAY);
		scrollPane.setViewportView(tbAreaList);

		tfAreaNo = new JTextField();
		tfAreaNo.setBounds(784, 107, 96, 21);
		add(tfAreaNo);
		tfAreaNo.setColumns(10);

	}

	void setStyle() {
		// 텍스트필드 편집 못하게 지정
		tfAreaNo.setEditable(false);
	}

	void eventProc() {
		btnInsert.addActionListener(this);
		btnModify.addActionListener(this);
		btnDelete.addActionListener(this);
		btnShowDrawing.addActionListener(this);
		btnDrawingInsert.addActionListener(this);
		tfAreaAddr.addActionListener(this);
		tfSearch.addActionListener(this);
		tfAreaDetail.addActionListener(this);
		tfScaffCkeck.addActionListener(this);
		cbSearch.addActionListener(this);
		tbAreaList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbAreaList.getSelectedRow();
				int col = 0;
				int anum = (Integer) tbAreaList.getValueAt(row, col);
				AreaVO vo = new AreaVO();
				try {
					vo = dao.findByNum(anum);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "검색실패" + e2.getMessage());
				}
				tfAreaAddr.setText(vo.getArea_addr());
				tfAreaDetail.setText(vo.getArea_detail());
				tfScaffCkeck.setText(vo.getScaff_check());
				tfAreaNo.setText(String.valueOf(vo.getArea_no()));
			}
		});
		setStyle();
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnInsert) {
			String Adetail = tfAreaDetail.getText();
			String acheck = tfScaffCkeck.getText();
			String aAddr = tfAreaAddr.getText();
			AreaVO vo = new AreaVO(Adetail, acheck, aAddr);
			try {
				dao.AreaInsert(vo);
				System.out.println("부지 등록 완료");
				clearScreen();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "부지 등록 실패" + e.getMessage());
			}
		} else if (o == btnModify) {
			String Adetail = tfAreaDetail.getText();
			String acheck = tfScaffCkeck.getText();
			String aAddr = tfAreaAddr.getText();
			AreaVO vo = new AreaVO(Adetail, acheck, aAddr);
			int num = Integer.parseInt(tfAreaNo.getText());
			try {
				dao.AreaModify(vo, num);
				System.out.println("부지 수정 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "부지 수정 실패" + e.getMessage());
			}
		} else if (o == btnDelete) {
			int num = Integer.parseInt(tfAreaNo.getText());
			try {
				dao.AreaDelete(num);
				System.out.println("부지 삭제 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "부지 삭제 실패" + e.getMessage());
			}
		} else if (o == tfSearch) {
			int sel = cbSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.AreaSearch(sel, text);
				tmArea.data = list;
				tbAreaList.setModel(tmArea);
				tmArea.fireTableDataChanged();
				System.out.println("부지 검색 완료");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "부지 검색 실패" + e.getMessage());
			}
		}
	}

	private void selectTable() {
		// TODO Auto-generated method stub
		int sel = cbSearch.getSelectedIndex();
		String text = tfSearch.getText();

		try {
			ArrayList list = dao.AreaSearch(sel, text);
			tmArea.data = list;
			tbAreaList.setModel(tmArea);
			tmArea.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "부지 검색 실패" + e.getMessage());
		}
	}

	private void clearScreen() {
		tfAreaAddr.setText("");
		tfAreaDetail.setText("");
		tfScaffCkeck.setText("");
		tfAreaNo.setText("");
	}
}

class AreaTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] colName = { "부지 번호", "부지 상세 위치", "비계 설치 여부", "부지 주소" };

	public int getColumnCount() {
		return colName.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return colName[col];
	}
}
