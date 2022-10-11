package view.tab;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import view.model.CommuteDAO;
import view.model.WorkerDao;
import view.model.rec.CommuteVO;
import view.model.rec.WorkerVo;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

public class CommuteViewTab extends JPanel implements ActionListener {
	private JTextField tfWorkerNum;
	private JTextField tfWorkerName;
	private JTextField tfToday;
	private JTextField tfSearch;
	private JTextField tfInTime;
	private JTextField tfOutTime;
	CommuteDAO dao;
	JButton btIn;
	JButton btOut;
	JComboBox comWorkerSearch;

	CommuteTableModel tmCommute;
	JTable CommuteTable;
	JButton btUpdate;

	public CommuteViewTab() {
		try {
			dao = new CommuteDAO();
			System.out.println("출퇴근 DB연결 성공");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "출퇴근 DB 연결 실패 : " + e.getMessage());
		}
		setBackground(Color.GRAY);
		setBounds(12, 10, 989, 660);
		setLayout(null);

		// 출근 버튼
		btIn = new JButton("출근");
		btIn.setForeground(Color.WHITE);
		btIn.setBounds(368, 182, 120, 30);
		btIn.setFont(new Font("굴림", Font.PLAIN, 15));
		btIn.setBackground(new Color(144, 238, 144));
		add(btIn);

		// 퇴근 버튼
		btOut = new JButton("퇴근");
		btOut.setForeground(Color.WHITE);
		btOut.setBounds(500, 182, 120, 30);
		btOut.setFont(new Font("굴림", Font.PLAIN, 15));
		btOut.setBackground(new Color(100, 149, 237));
		add(btOut);

		// 이름 검색란
		String searchText[] = { "이름", "출근시간" };
		comWorkerSearch = new JComboBox();
		comWorkerSearch.setFont(new Font("굴림", Font.BOLD, 15));
		comWorkerSearch.setBounds(61, 182, 80, 30);
		add(comWorkerSearch);
		comWorkerSearch.addItem("이름");
		comWorkerSearch.addItem("출근시간");

		tfSearch = new JTextField(15);
		tfSearch.setBounds(142, 183, 183, 30);
		add(tfSearch);

		// 이름 검색란
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(61, 50, 559, 122);
		add(panel);
		panel.setLayout(null);

		// 작업자 번호 입력
		JLabel lblNewLabel = new JLabel("작업자번호");
		lblNewLabel.setBounds(25, 18, 100, 18);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);

		tfWorkerNum = new JTextField();
		tfWorkerNum.setBounds(104, 18, 221, 18);
		panel.add(tfWorkerNum);
		tfWorkerNum.setColumns(10);

		// 작업자 이름 입력
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(25, 41, 100, 18);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));

		tfWorkerName = new JTextField();
		tfWorkerName.setBounds(104, 41, 221, 18);
		panel.add(tfWorkerName);
		tfWorkerName.setColumns(10);

		// 출근시간 입력
		JLabel lblNewLabel_1_1 = new JLabel("출근시간");
		lblNewLabel_1_1.setBounds(25, 64, 100, 18);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));

		tfInTime = new JTextField();
		tfInTime.setBounds(104, 64, 221, 18);
		panel.add(tfInTime);
		tfInTime.setColumns(10);

		// 퇴근시간 입력
		JLabel lblNewLabel_1_2 = new JLabel("퇴근시간");
		lblNewLabel_1_2.setBounds(25, 87, 67, 18);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.PLAIN, 15));

		tfOutTime = new JTextField();
		tfOutTime.setBounds(104, 87, 221, 18);
		panel.add(tfOutTime);
		tfOutTime.setColumns(10);

		tfToday = new JTextField();
		tfToday.setBounds(73, 11, 194, 29);
		add(tfToday);
		tfToday.setBackground(Color.GRAY);
		tfToday.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 222, 928, 428);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		tmCommute = new CommuteTableModel();
		CommuteTable = new JTable(tmCommute);
		CommuteTable.setBackground(Color.GRAY);
		CommuteTable.setForeground(Color.WHITE);
		scrollPane.setViewportView(CommuteTable);

		btUpdate = new JButton("수정");
		btUpdate.setForeground(Color.WHITE);
		btUpdate.setFont(new Font("굴림", Font.PLAIN, 15));
		btUpdate.setBackground(new Color(255, 182, 193));
		btUpdate.setBounds(632, 182, 120, 30);
		add(btUpdate);

		// 조회 테이블

		eventProc();

	}

	void eventProc() {
		btIn.addActionListener(this);
		btOut.addActionListener(this);
		btUpdate.addActionListener(this);
		tfSearch.addActionListener(this);
		CommuteTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = CommuteTable.getSelectedRow();
				int col = 1;

				String num = (String) CommuteTable.getValueAt(row, col);
				CommuteVO vo = new CommuteVO();
				System.out.println(row);
				System.out.println(num);
				try {
					vo = dao.findByNum(num);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("마우스클릭 실패");
				}
				tfWorkerNum.setText(String.valueOf(vo.getWorkerno()));
				tfWorkerName.setText(vo.getName());
				tfInTime.setText(vo.getIntime());
				tfOutTime.setText(vo.getOuttime());
			}
		});
	}

	// 작업수행
	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		// System.out.println(main.tfusername.getText());
		if (o == btIn) {
			int workerno = Integer.parseInt(tfWorkerNum.getText());
			String name = tfWorkerName.getText();
			CommuteVO vo = new CommuteVO(workerno, name);

			try {
				dao.Intime(workerno);
				System.out.println("출근 등록 성공!");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "출근 등록 실패!" + e.getMessage());
			}
		} else if (o == btOut) {
			int workerno = Integer.parseInt(tfWorkerNum.getText());
			String name = tfWorkerName.getText();

			try {
				dao.Outtime(workerno);
				System.out.println("퇴근 등록 성공!");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "퇴근 등록 실패!" + e.getMessage());
			}

		} else if (o == tfSearch) {
			int sel = comWorkerSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.CommuteSearch(sel, text);
				tmCommute.data = list;
				CommuteTable.setModel(tmCommute);
				tmCommute.fireTableDataChanged();
				System.out.println("출퇴근검색성공");

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("출퇴근 검색 실패 " + e.getMessage());
			}
		} else if (o == btUpdate) {
			try {
				int workerno = Integer.parseInt(tfWorkerNum.getText());
				String name = tfWorkerName.getText();
				String intime = tfInTime.getText();
				String outtime = tfOutTime.getText();
				dao.commuteUpdate(workerno, intime, outtime);
				clearScreen();
				selectTable();
				System.out.println("업데이트 성공");
			} catch (Exception e) {
				System.out.println("업데이트 실패 " + e.getMessage());
			}
		}

	}

	private void clearScreen() {
		tfWorkerNum.setText(null);
		tfWorkerName.setText(null);
		tfInTime.setText(null);
		tfOutTime.setText(null);
	}

	public void selectTable() {
		int sel = comWorkerSearch.getSelectedIndex();
		String text = tfSearch.getText();

		try {
			ArrayList list = dao.CommuteSearch(sel, text);
			tmCommute.data = list;
			CommuteTable.setModel(tmCommute);
			tmCommute.fireTableDataChanged();
			System.out.println("새로고침 성공");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("새로고침 실패 " + e.getMessage());
		}
	}
}

class CommuteTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "관리자번호", "출퇴근번호", "작업자번호", "이름", "전문분야", "출근시간", "퇴근시간" };

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

}