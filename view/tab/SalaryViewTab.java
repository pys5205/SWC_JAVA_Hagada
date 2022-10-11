package view.tab;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.table.*;

import view.model.*;
import view.model.rec.*;

public class SalaryViewTab extends JPanel implements ActionListener {
	private JTextField tfWorkerNo;
	private JTextField tfWorkerName;
	private JTextField tfSalYear;

	JButton btSalInsert;
	JButton btSum;

	SalaryDAO dao;
	JTable searchSal;
	salaryTableModel tmSelSal;

	TaxTableModel tmSelTax;
	JTable searchTax;

	TotalTableModel tmSelTotal;
	JTable searchTotal;

	public SalaryViewTab() {
		try {
			dao = new SalaryDAO();
			System.out.println("월급 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "월급 DB 연결 실패 : " + e.getMessage());
		}
		setBackground(Color.GRAY);
		setBounds(12, 10, 989, 660);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(53, 39, 576, 136);
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("작업자번호");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(23, 25, 100, 18);
		panel.add(lblNewLabel);

		tfWorkerNo = new JTextField();
		tfWorkerNo.setColumns(10);
		tfWorkerNo.setBounds(124, 24, 221, 18);
		panel.add(tfWorkerNo);

		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(23, 53, 100, 18);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("년");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(23, 85, 100, 18);
		panel.add(lblNewLabel_2);

		tfWorkerName = new JTextField();
		tfWorkerName.setColumns(10);
		tfWorkerName.setBounds(124, 53, 221, 18);
		panel.add(tfWorkerName);

		tfSalYear = new JTextField();
		tfSalYear.setColumns(10);
		tfSalYear.setBounds(124, 84, 221, 18);
		panel.add(tfSalYear);

		btSum = new JButton("계산");
		btSum.setForeground(Color.WHITE);
		btSum.setBounds(404, 79, 120, 30);
		panel.add(btSum);
		btSum.setFont(new Font("굴림", Font.PLAIN, 15));
		btSum.setBackground(new Color(100, 149, 237));

		btSalInsert = new JButton("조회");
		btSalInsert.setForeground(Color.WHITE);
		btSalInsert.setFont(new Font("굴림", Font.PLAIN, 15));
		btSalInsert.setBackground(new Color(144, 238, 144));
		btSalInsert.setBounds(404, 23, 120, 30);
		panel.add(btSalInsert);

		JLabel lblNewLabel_3 = new JLabel("공제내역");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(53, 387, 100, 18);
		add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("월급통계");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3_1.setBounds(569, 185, 144, 24);
		add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("월급내역");
		lblNewLabel_3_1_1.setForeground(Color.WHITE);
		lblNewLabel_3_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_3_1_1.setBounds(53, 185, 268, 24);
		add(lblNewLabel_3_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 219, 420, 158);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		tmSelSal = new salaryTableModel();
		searchSal = new JTable(tmSelSal);
		searchSal.setForeground(Color.WHITE);
		searchSal.setBackground(Color.GRAY);
		scrollPane.setViewportView(searchSal);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(53, 415, 936, 235);
		scrollPane_1.getViewport().setBackground(Color.gray);
		add(scrollPane_1);

		tmSelTax = new TaxTableModel();
		searchTax = new JTable(tmSelTax);
		searchTax.setForeground(Color.WHITE);
		searchTax.setBackground(Color.GRAY);
		scrollPane_1.setViewportView(searchTax);

//		searchTax = new JTable();
//		searchTax.setModel(new DefaultTableModel(new Object[][] {},
//				new String[] { "작업자번호", "년", "월", "소득세", "지방소득세", "국민연금", "건강보험료", "고용보험료", "세금총액" }));
//		scrollPane_1.setViewportView(searchTax);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(569, 219, 420, 158);
		scrollPane_2.getViewport().setBackground(Color.gray);
		add(scrollPane_2);

		tmSelTotal = new TotalTableModel();
		searchTotal = new JTable(tmSelTotal);
		searchTotal.setForeground(Color.WHITE);
		searchTotal.setBackground(Color.GRAY);
		scrollPane_2.setViewportView(searchTotal);

		btSalInsert.addActionListener(this);
		btSum.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btSalInsert) {
			int empno = Integer.parseInt(tfWorkerNo.getText());
			String name = tfWorkerName.getText();
			String year = tfSalYear.getText();
			// MonthSalVO vo = new MonthSalVO(empno, name, month);
			try {
				ArrayList list = dao.salarySelect(empno, year);
				tmSelSal.data = list;
				searchSal.setModel(tmSelSal);
				tmSelSal.fireTableDataChanged();
				System.out.println("월급내역 검색성공");
				
				dao.salDel(empno, year);
				dao.salaryInsert(list);
				
				ArrayList tax = dao.taxselect(empno, year);
				tmSelTax.data = tax;
				searchTax.setModel(tmSelTax);
				tmSelTax.fireTableDataChanged();
				
				dao.taxInsert(tax);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("검색 실패 " + e.getMessage());
			}
		} else if (o == btSum) {
			int empno = Integer.parseInt(tfWorkerNo.getText());
			String name = tfWorkerName.getText();
			String year = tfSalYear.getText();
			try {
				ArrayList list = dao.selTotal(empno, year);
				tmSelTotal.data = list;
				searchTotal.setModel(tmSelTotal);
				tmSelTotal.fireTableDataChanged();

				dao.totalInsert(list);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("검색 실패 " + e.getMessage());
			}

		}
	}
}

class salaryTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "작업자번호", "년", "월", "기본급", "추가수당" };

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

class TaxTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "작업자번호", "세금번호", "년", "월", "소득세", "지방소득세", "국민연금", "건강보험료", "고용보험료", "세금총액" };

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

class TotalTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "작업자번호", "이름", "년", "월", "세전", "세후" };

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
