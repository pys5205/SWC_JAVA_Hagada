package view.tab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import view.model.ScheduleDAO;
import view.model.rec.ScheduleVO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScheduleViewTab extends JPanel implements ActionListener {

	private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	ImageIcon icon_search = new ImageIcon(img_search);

	JTextField tfSearch, tfScheduleName, tfWorkScheduleNo;
	JButton btnInsert, btnModify, btnDelete;
	JComboBox cbSearch;
	JTable tbScheduleList;
	ScheduleDAO dao = null;
	scheduleTableModel mdSchedule;

	/**
	 * Create the panel.
	 */
	
	public ScheduleViewTab() {
		try {
			dao = new ScheduleDAO();
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
		btnInsert.setBounds(415, 70, 115, 30);
		add(btnInsert);

		btnModify = new JButton("수정");
		btnModify.setForeground(new Color(255, 255, 255));
		btnModify.setFont(new Font("굴림", Font.BOLD, 15));
		btnModify.setBackground(new Color(100, 149, 237));
		btnModify.setBounds(538, 70, 115, 30);
		add(btnModify);

		btnDelete = new JButton("삭제");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("굴림", Font.BOLD, 15));
		btnDelete.setBackground(new Color(255, 182, 193));
		btnDelete.setBounds(660, 70, 115, 30);
		add(btnDelete);

		JLabel lbSearch = new JLabel(icon_search);
		lbSearch.setBounds(375, 68, 25, 25);
		add(lbSearch);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(102, 68, 266, 30);
		add(tfSearch);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 22, 570, 36);
		add(panel);
		panel.setLayout(null);

		JLabel lbScheduleName = new JLabel("스케줄명");
		lbScheduleName.setForeground(Color.WHITE);
		lbScheduleName.setFont(new Font("굴림", Font.BOLD, 15));
		lbScheduleName.setBounds(210, 12, 70, 18);
		panel.add(lbScheduleName);

		tfScheduleName = new JTextField();
		tfScheduleName.setColumns(10);
		tfScheduleName.setBounds(300, 12, 221, 18);
		panel.add(tfScheduleName);
		
		JLabel lbWorkScheduleNo = new JLabel("스케줄 번호");
		lbWorkScheduleNo.setForeground(Color.WHITE);
		lbWorkScheduleNo.setFont(new Font("굴림", Font.BOLD, 15));
		lbWorkScheduleNo.setBounds(12, 12, 92, 18);
		panel.add(lbWorkScheduleNo);
		
		tfWorkScheduleNo = new JTextField();
		tfWorkScheduleNo.setBounds(120, 12, 60, 18);
		panel.add(tfWorkScheduleNo);
		tfWorkScheduleNo.setColumns(10);

		String SearchList[] = { "작업번호", "스케줄명"};
		cbSearch = new JComboBox();
		cbSearch.setForeground(Color.DARK_GRAY);
		cbSearch.setFont(new Font("굴림", Font.BOLD, 12));
		cbSearch.setBackground(Color.LIGHT_GRAY);
		cbSearch.setBounds(12, 68, 91, 30);
		add(cbSearch);
		cbSearch.addItem("작업번호");
		cbSearch.addItem("스케줄명");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 103, 1020, 567);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		mdSchedule = new scheduleTableModel();
		tbScheduleList = new JTable(mdSchedule);
		tbScheduleList.setForeground(Color.WHITE);
		tbScheduleList.setBackground(Color.GRAY);
		scrollPane.setViewportView(tbScheduleList);
		
		
	}

	void setStyle() {
		// 텍스트필드 편집 못하게 지정
		tfWorkScheduleNo.setEditable(false);
	}

	void eventProc() {
		// 이벤트 등록
		btnInsert.addActionListener(this);
		btnModify.addActionListener(this);
		btnDelete.addActionListener(this);
		tfSearch.addActionListener(this);
		cbSearch.addActionListener(this);
		tfScheduleName.addActionListener(this);
		tfWorkScheduleNo.addActionListener(this);
		tbScheduleList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbScheduleList.getSelectedRow();
				int col = 0; // col은 0부터 시작
				int ssnum = (Integer) tbScheduleList.getValueAt(row, col);
				ScheduleVO vo = new ScheduleVO();
				try {
					vo = dao.findByNum(ssnum);
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "검색실패" + e2.getMessage());
				}
				tfScheduleName.setText(vo.getSname());
				tfWorkScheduleNo.setText(String.valueOf(vo.getSchedule_no()));
			}
		});
		setStyle();
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnInsert) {
			String sname = tfScheduleName.getText();
			ScheduleVO vo = new ScheduleVO(sname);
			int num = Integer.parseInt(tfWorkScheduleNo.getText());
			try {
				dao.scheduleInsert(vo, num);
				System.out.println("스케줄명 등록 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "스케줄명 등록 실패" + e.getMessage());
			}
		} else if(o == btnModify) {
			String sname = tfScheduleName.getText();
			ScheduleVO vo = new ScheduleVO(sname);
			int num = Integer.parseInt(tfWorkScheduleNo.getText());
			try {
				dao.schedulModify(vo, num);
				System.out.println("스케줄명 수정 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "스케줄명 수정 실패" + e.getMessage());
			}
		} else if(o == btnDelete) {
			int num = Integer.parseInt(tfWorkScheduleNo.getText());
			try {
				dao.schedulDelete(num);
				System.out.println("스케줄명 삭제 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "스케줄명 삭제 실패" + e.getMessage());
			}
		} else if(o == tfSearch) {
			int sel = cbSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.scheduleSearch(sel, text);
				mdSchedule.data = list;
				tbScheduleList.setModel(mdSchedule);
				mdSchedule.fireTableDataChanged();
				System.out.println("스케줄 검색 완료");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "스케줄 검색 실패" + e.getMessage());
			}
		}
	}

	private void selectTable() {
		// TODO Auto-generated method stub
		int sel = cbSearch.getSelectedIndex();
		String text = tfSearch.getText();
		
		try {
			ArrayList list = dao.scheduleSearch(sel, text);
			mdSchedule.data = list;
			tbScheduleList.setModel(mdSchedule);
			mdSchedule.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "스케줄 검색 실패" + e.getMessage());
		}
	}
	
	private void clearScreen() {
		tfScheduleName.setText("");
		tfWorkScheduleNo.setText("");
	}
}

class scheduleTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] colName = {"스케줄 번호", "스케줄명", "시작일", "종료일"};
	
	public int getColumnCount() {
		return colName.length;
	}
	
	public int getRowCount() {
		return data.size();
	}
	
	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList)data.get(row);
		return temp.get(col);
	}
	
	public String getColumnName(int col) {
		return colName[col];
	}
}
