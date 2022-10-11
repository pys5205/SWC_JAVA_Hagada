package view.tab;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;
import javax.swing.table.*;

import model.*;
import model.rec.LoginVo;
import view.ManagementView;
import view.model.*;
import view.model.rec.WorkerVo;

import javax.swing.border.LineBorder;

public class WorkerViewTab extends JPanel implements ActionListener {
	private JTextField tfWorkerNum;
	private JTextField tfWorkerName;
	private JTextField tfWorkerJumin;
	private JTextField tfWorkerTel;
	private JTextField tfWorkerMajor;
	private JTextField tfWorkerAcc;
	private JTextField tfWorkerCareer;
	private JTextField tfWorkerPer_hour;
	private JTextField tfWorkerSafe;
	private JTextField tfWorkerEmp;
	private JTextField tfSearch;
	public JTextField tfCountWorker;
	JButton btInsert, btUpdate, btDelete;
	WorkerDao dao;

	JComboBox comWorkerSearch;

	JTable TableWorker;
	private WorkerTableModel tmWorker;

	public WorkerViewTab() {
		try {
			dao = new WorkerDao();
			System.out.println("노동자 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "노동자 DB 연결 실패 : " + e.getMessage());
		}
		setBackground(Color.GRAY);
		setForeground(Color.DARK_GRAY);
		setBounds(12, 10, 989, 660);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("주민번호");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(45, 96, 100, 18);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("연락처");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(45, 126, 100, 18);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		add(lblNewLabel_1);

		btInsert = new JButton("등록");
		btInsert.setForeground(Color.WHITE);
		btInsert.setBackground(new Color(153, 255, 153));
		btInsert.setBounds(387, 188, 120, 30);
		btInsert.setFont(new Font("굴림", Font.PLAIN, 15));
		add(btInsert);

		btUpdate = new JButton("수정");
		btUpdate.setForeground(Color.WHITE);
		btUpdate.setBackground(new Color(100, 149, 237));
		btUpdate.setBounds(522, 188, 120, 30);
		btUpdate.setFont(new Font("굴림", Font.PLAIN, 15));
		add(btUpdate);

		btDelete = new JButton("삭제");
		btDelete.setForeground(Color.WHITE);
		btDelete.setBackground(new Color(255, 182, 193));
		btDelete.setBounds(654, 188, 120, 30);
		btDelete.setFont(new Font("굴림", Font.PLAIN, 15));
		add(btDelete);

		JLabel lblNewLabel_7 = new JLabel("담당직원");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(444, 36, 115, 18);
		lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 15));
		add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("이름");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(45, 66, 130, 18);
		lblNewLabel_8.setFont(new Font("굴림", Font.PLAIN, 15));
		add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("작업자번호");
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBounds(45, 36, 100, 18);
		lblNewLabel_9.setFont(new Font("굴림", Font.PLAIN, 15));
		add(lblNewLabel_9);

		tfWorkerNum = new JTextField();
		tfWorkerNum.setBounds(170, 35, 221, 18);
		add(tfWorkerNum);
		tfWorkerNum.setColumns(10);

		tfWorkerName = new JTextField();
		tfWorkerName.setBounds(170, 65, 221, 18);
		tfWorkerName.setColumns(10);
		add(tfWorkerName);

		tfWorkerJumin = new JTextField();
		tfWorkerJumin.setBounds(170, 95, 221, 18);
		tfWorkerJumin.setColumns(10);
		add(tfWorkerJumin);

		tfWorkerTel = new JTextField();
		tfWorkerTel.setBounds(170, 125, 221, 18);
		tfWorkerTel.setColumns(10);
		add(tfWorkerTel);

		JLabel lblNewLabel_9_1 = new JLabel("총인원");
		lblNewLabel_9_1.setForeground(Color.WHITE);
		lblNewLabel_9_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_9_1.setBounds(889, 22, 74, 22);
		add(lblNewLabel_9_1);

		tfCountWorker = new JTextField();
		tfCountWorker.setForeground(Color.WHITE);
		tfCountWorker.setFont(new Font("굴림", Font.BOLD, 17));
		tfCountWorker.setBackground(Color.GRAY);
		tfCountWorker.setColumns(10);
		tfCountWorker.setBounds(889, 57, 57, 34);
		add(tfCountWorker);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 22, 820, 156);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("안전교육이수여부");
		lblNewLabel_6.setBounds(31, 129, 115, 18);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 15));

		tfWorkerSafe = new JTextField();
		tfWorkerSafe.setBounds(158, 129, 221, 18);
		panel.add(tfWorkerSafe);
		tfWorkerSafe.setColumns(10);

		tfWorkerEmp = new JTextField();
		tfWorkerEmp.setBounds(515, 15, 221, 18);
		panel.add(tfWorkerEmp);
		tfWorkerEmp.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("전문분야");
		lblNewLabel_2.setBounds(432, 43, 74, 22);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));

		JLabel lblNewLabel_5 = new JLabel("시급");
		lblNewLabel_5.setBounds(432, 70, 74, 22);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 15));

		JLabel lblNewLabel_3 = new JLabel("계좌번호");
		lblNewLabel_3.setBounds(432, 102, 74, 22);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));

		JLabel lblNewLabel_4 = new JLabel("경력");
		lblNewLabel_4.setBounds(432, 127, 74, 22);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));

		tfWorkerMajor = new JTextField();
		tfWorkerMajor.setBounds(515, 43, 221, 18);
		panel.add(tfWorkerMajor);
		tfWorkerMajor.setColumns(10);

		tfWorkerPer_hour = new JTextField();
		tfWorkerPer_hour.setBounds(515, 72, 221, 18);
		panel.add(tfWorkerPer_hour);
		tfWorkerPer_hour.setColumns(10);

		tfWorkerAcc = new JTextField();
		tfWorkerAcc.setBounds(515, 104, 221, 18);
		panel.add(tfWorkerAcc);
		tfWorkerAcc.setColumns(10);
		
				tfWorkerCareer = new JTextField();
				tfWorkerCareer.setBounds(515, 129, 221, 18);
				panel.add(tfWorkerCareer);
				tfWorkerCareer.setColumns(10);

		String searchText[] = { "이름", "직원" };
		comWorkerSearch = new JComboBox();
		comWorkerSearch.setFont(new Font("굴림", Font.BOLD, 15));
		comWorkerSearch.setBounds(12, 188, 74, 30);
		add(comWorkerSearch);
		comWorkerSearch.addItem("이름");
		comWorkerSearch.addItem("직원");

		tfSearch = new JTextField(15);
		tfSearch.setBounds(89, 188, 183, 30);
		add(tfSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 228, 1020, 422);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		tmWorker = new WorkerTableModel();
		TableWorker = new JTable(tmWorker);
		TableWorker.setForeground(Color.WHITE);
		TableWorker.setBackground(Color.GRAY);


		scrollPane.setViewportView(TableWorker);
		btInsert.addActionListener(this);
		btUpdate.addActionListener(this);
		btDelete.addActionListener(this);

		tfSearch.addActionListener(this);
		TableWorker.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = TableWorker.getSelectedRow();
				int col = 0;

				int workernum = (Integer) TableWorker.getValueAt(row, col);
				WorkerVo vo = new WorkerVo();
				try {
					vo = dao.findByNum(workernum);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("마우스클릭 실패");
				}
				tfWorkerNum.setText(String.valueOf(vo.getWorkerno()));
				tfWorkerName.setText(vo.getName());
				tfWorkerJumin.setText(vo.getJumin());
				tfWorkerTel.setText(vo.getTel());
				tfWorkerAcc.setText(vo.getAcc());
				tfWorkerPer_hour.setText(String.valueOf(vo.getPer_hour()));
				tfWorkerEmp.setText(String.valueOf(vo.getEmpno()));
				tfWorkerSafe.setText(vo.getSafe());
				tfWorkerMajor.setText(vo.getMajor());
				tfWorkerCareer.setText(String.valueOf(vo.getCareer()));
			}
		});
		setedit();
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		//System.out.println(main.tfusername.getText());
		if (o == btInsert) {
			String name = tfWorkerName.getText();
			String jumin = tfWorkerJumin.getText();
			String tel = tfWorkerTel.getText();
			String acc = tfWorkerAcc.getText();
			int per = Integer.parseInt(tfWorkerPer_hour.getText());
			int empno = Integer.parseInt(tfWorkerEmp.getText());
			String safe = tfWorkerSafe.getText();
			String major = tfWorkerMajor.getText();
			int career = Integer.parseInt(tfWorkerCareer.getText());

			WorkerVo vo = new WorkerVo(name, jumin, tel, safe, empno, major, per, acc, career);
			try {
				dao.WorkerInsert(vo);
				System.out.println("노동자 등록 성공");
				clearScreen();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "노동자입력실패" + e.getMessage());
			}
		} else if (o == btUpdate) {
			String name = tfWorkerName.getText();
			String jumin = tfWorkerJumin.getText();
			String tel = tfWorkerTel.getText();
			String acc = tfWorkerAcc.getText();
			int per = Integer.parseInt(tfWorkerPer_hour.getText());
			int empno = Integer.parseInt(tfWorkerEmp.getText());
			String safe = tfWorkerSafe.getText();
			String major = tfWorkerMajor.getText();
			int career = Integer.parseInt(tfWorkerCareer.getText());
			WorkerVo vo = new WorkerVo(name, jumin, tel, safe, empno, major, per, acc, career);
			int num = Integer.parseInt(tfWorkerNum.getText());

			try {
				dao.workerUpdate(vo, num);
				System.out.println("작업자 수정 성공");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("작업자 수정 실패");
			}
		} else if (o == btDelete) {
			int num = Integer.parseInt(tfWorkerNum.getText());
			try {
				dao.workerDelete(num);
				System.out.println("작업자 삭제 성공");
				selectTable();
				clearScreen();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("작업자 삭제 실패");
			}
		} else if (o == tfSearch) {
			int sel = comWorkerSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.workerSearch(sel, text);
				tmWorker.data = list;
				TableWorker.setModel(tmWorker);
				tmWorker.fireTableDataChanged();
				System.out.println("작업자검색성공");

				String res = dao.countworkers();
				System.out.println(res);
				tfCountWorker.setText(res);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("작업자 검색 실패 " + e.getMessage());
			}
		}
	}

	public void selectTable() {
		int sel = comWorkerSearch.getSelectedIndex();
		String text = tfSearch.getText();
		try {
			ArrayList list = dao.workerSearch(sel, text);
			tmWorker.data = list;
			TableWorker.setModel(tmWorker);
			tmWorker.fireTableDataChanged();
			System.out.println("검색성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("검색 실패 " + e.getMessage());
		}
	}

	void setedit() {
		tfWorkerNum.setEditable(false);
		tfCountWorker.setEditable(false);
		//tfWorkerEmp.setEditable(false);
	}

	void clearScreen() {
		tfWorkerName.setText(null);
		tfWorkerJumin.setText(null);
		tfWorkerTel.setText(null);
		tfWorkerAcc.setText(null);
		tfWorkerPer_hour.setText(null);
		tfWorkerEmp.setText(null);
		tfWorkerSafe.setText(null);
		tfWorkerMajor.setText(null);
		tfWorkerCareer.setText(null);
	}
}

class WorkerTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "작업자번호", "이름", "주민번호", "연락처", "안전교육", "전문분야", "경력", "담당직원" };

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
