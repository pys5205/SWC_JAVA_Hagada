package view.tab;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import view.model.*;
import view.model.rec.*;

public class EquipmentViewTab extends JPanel implements ActionListener {
	private Image img_search = new ImageIcon(WorkPlanViewTab.class.getResource("/res/searchIcon.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	ImageIcon icon_search = new ImageIcon(img_search);
	JTextField tfSearch;
	JTextField tfHourPay;
	JTextField tfEquipmentName;
	JTextField tfInputCheck;
	JTextField tfCompanyChoice;
	JComboBox cbSearch;
	EquipsTableModel tmEquip;
	JTable tbEquip;

	EquipmentDAO dao;
	JButton btnInsert, btnModify, btnDelete, btnTotalPrice;

	/**
	 * Create the panel.
	 */
	public EquipmentViewTab() {
		try {
			dao = new EquipmentDAO();
			System.out.println("중장비 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "중장비 DB 연결 실패 : " + e.getMessage());
		}
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

		JLabel lbSearch = new JLabel(icon_search);
		lbSearch.setBounds(375, 102, 25, 25);
		add(lbSearch);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(102, 102, 266, 30);
		add(tfSearch);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 22, 760, 70);
		add(panel);
		panel.setLayout(null);

		JLabel lbInputCheck = new JLabel("투입 가능 여부");
		lbInputCheck.setForeground(Color.WHITE);
		lbInputCheck.setFont(new Font("굴림", Font.BOLD, 15));
		lbInputCheck.setBounds(430, 17, 130, 18);
		panel.add(lbInputCheck);

		JLabel lbEquipmentName = new JLabel("중장비명");
		lbEquipmentName.setForeground(Color.WHITE);
		lbEquipmentName.setFont(new Font("굴림", Font.BOLD, 15));
		lbEquipmentName.setBounds(32, 15, 100, 18);
		panel.add(lbEquipmentName);

		JLabel lbHourPay = new JLabel("시간당 금액");
		lbHourPay.setForeground(Color.WHITE);
		lbHourPay.setFont(new Font("굴림", Font.BOLD, 15));
		lbHourPay.setBounds(32, 45, 100, 18);
		panel.add(lbHourPay);

		tfHourPay = new JTextField();
		tfHourPay.setColumns(10);
		tfHourPay.setBounds(157, 45, 221, 18);
		panel.add(tfHourPay);

		tfEquipmentName = new JTextField();
		tfEquipmentName.setColumns(10);
		tfEquipmentName.setBounds(157, 15, 221, 18);
		panel.add(tfEquipmentName);

		JLabel lbCompanyChoice = new JLabel("업체 선택");
		lbCompanyChoice.setForeground(Color.WHITE);
		lbCompanyChoice.setFont(new Font("굴림", Font.BOLD, 15));
		lbCompanyChoice.setBounds(430, 45, 73, 18);
		panel.add(lbCompanyChoice);

		tfInputCheck = new JTextField();
		tfInputCheck.setColumns(10);
		tfInputCheck.setBounds(568, 17, 180, 18);
		panel.add(tfInputCheck);

		tfCompanyChoice = new JTextField();
		tfCompanyChoice.setColumns(10);
		tfCompanyChoice.setBounds(568, 44, 180, 18);
		panel.add(tfCompanyChoice);

		String SearchList[] = { "중장비명", "업체명" };
		cbSearch = new JComboBox(SearchList);
		cbSearch.setForeground(Color.DARK_GRAY);
		cbSearch.setFont(new Font("굴림", Font.BOLD, 12));
		cbSearch.setBackground(Color.LIGHT_GRAY);
		cbSearch.setBounds(12, 102, 91, 30);
		add(cbSearch);

		btnTotalPrice = new JButton("정산");
		btnTotalPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipmentTotalPriceViewTab totalPrice = new EquipmentTotalPriceViewTab();
				totalPrice.setVisible(true);
				
				String name = tfEquipmentName.getText();
				String money = tfHourPay.getText();
				totalPrice.tfEquipmentName.setText(name);
				totalPrice.tfEquipmentHourPay.setText(money);
			}
		});
		btnTotalPrice.setForeground(Color.WHITE);
		btnTotalPrice.setFont(new Font("굴림", Font.BOLD, 22));
		btnTotalPrice.setBackground(Color.ORANGE);
		btnTotalPrice.setBounds(784, 22, 175, 70);
		add(btnTotalPrice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 142, 1020, 516);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);

		tmEquip = new EquipsTableModel();
		tbEquip = new JTable(tmEquip);
		tbEquip.setForeground(Color.WHITE);
		tbEquip.setBackground(Color.GRAY);
		scrollPane.setViewportView(tbEquip);

		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnModify.addActionListener(this);
		tfSearch.addActionListener(this);
		btnTotalPrice.addActionListener(this);
		tbEquip.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbEquip.getSelectedRow();
				int col = 0;

				int num = (Integer) tbEquip.getValueAt(row, col);
				EquipmentVO vo = new EquipmentVO();
				try {
					vo = dao.findByNum(num);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("마우스클릭 실패");
				}
				tfEquipmentName.setText(vo.getName());
				tfHourPay.setText(String.valueOf(vo.getMoney()));
				tfCompanyChoice.setText(vo.getCompany());
				tfInputCheck.setText(vo.getCheck());
			}
		});
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnInsert) {
			String name = tfEquipmentName.getText();
			int money = Integer.parseInt(tfHourPay.getText());
			String company = tfCompanyChoice.getText();
			String check = tfInputCheck.getText();

			EquipmentVO vo = new EquipmentVO(name, money, company, check);
			try {
				dao.equipInsert(vo);
				clearScreen();
				System.out.println("중장비 입력 완료");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("입력 실패 " + e.getMessage());
			}
		} else if (o == btnDelete) {
			String name = tfEquipmentName.getText();
			try {
				dao.equipDelete(name);
				System.out.println("중장비 삭제 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (o == btnModify) {
			String name = tfEquipmentName.getText();
			int money = Integer.parseInt(tfHourPay.getText());
			String company = tfCompanyChoice.getText();
			String check = tfInputCheck.getText();

			EquipmentVO vo = new EquipmentVO(name, money, company, check);
			try {
				dao.equipUpdate(vo, name);
				System.out.println("중장비 수정 완료");
				clearScreen();
				selectTable();
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (o == tfSearch) {
			int sel = cbSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.equipSearch(sel, text);
				tmEquip.data = list;
				tbEquip.setModel(tmEquip);
				tmEquip.fireTableDataChanged();
				System.out.println("중장비검색성공");
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("중장비 검색 실패 " + e.getMessage());
			}
		}
	}

	public void selectTable() {
		int sel = cbSearch.getSelectedIndex();
		String text = tfSearch.getText();
		try {
			ArrayList list = dao.equipSearch(sel, text);
			tmEquip.data = list;
			tbEquip.setModel(tmEquip);
			tmEquip.fireTableDataChanged();
			System.out.println("새로고침 성공");
		} catch (Exception e) {
			System.out.println("새로고침 실패 " + e.getMessage());
		}

	}

	public void clearScreen() {
		tfCompanyChoice.setText(null);
		tfEquipmentName.setText(null);
		tfHourPay.setText(null);
		tfInputCheck.setText(null);
	}
	

}

class EquipsTableModel extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "중장비번호", "중장비명", "시간당 금액", "업체번호", "업체 명", "투입 가능 여부" };

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
