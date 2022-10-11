package view.tab;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.border.*;

import view.model.*;
import view.model.rec.*;

public class EquipmentTotalPriceViewTab extends JFrame implements ActionListener {
	Point initialClick;
	JTextField tfEquipmentName;
	JTextField tfEquipmentStartDate;
	JTextField tfEquipmentEndDate;
	JTextField tfEquipmentUseTime;
	JTextField tfEquipmentHourPay;
	JTextField tfEquipmentTotalPrice;
	JButton btnTotalPrice;
	EquipmentDAO dao;

	// https://becca-codingdiary.tistory.com/entry/02-%ED%83%80%EC%9D%B4%ED%8B%80%EB%B0%94-%EC%82%AD%EC%A0%9C
	// 타이틀바 지운 상태에서 마우스로 화면 움직이기
	public class moveWindows extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

			initialClick = e.getPoint(); // 현재 좌표 저장
			getComponentAt(initialClick); // 저장한 좌표를 포함한 컴포넌트를 리턴 받음
		}

		public void mouseDragged(MouseEvent e) {
			JFrame jframe = (JFrame) e.getSource(); // 드래그 된 JFrame의 정보를 받아옴
			int thisX = jframe.getLocation().x; // jframe의 x 값을 저장함
			int thisY = jframe.getLocation().y; // jframe의 y 값을 저장함

			int xMoved = e.getX() - initialClick.x;
			int yMoved = e.getY() - initialClick.y;
			// 현재 마우스 위치의 x, y좌표 - 첫 마우스 클릭 위치 x, y좌표
			// initialClick를 하지 않으면 (0, 0)에 포인터가 고정된채로 움직임

			int X = thisX + xMoved; // jframe x값 + 이동한 x 값
			int Y = thisY + yMoved; // jframe y값 + 이동한 y 값

			jframe.setLocation(X, Y); // jframe의 위치 변경
		}
	}

	public EquipmentTotalPriceViewTab() {
		try {
			dao = new EquipmentDAO();
			System.out.println("정산 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "정산 DB 연결 실패 : " + e.getMessage());
		}
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		getContentPane().setLayout(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 400, 500);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lbEquipmentName = new JLabel("중장비명");
		lbEquipmentName.setForeground(Color.WHITE);
		lbEquipmentName.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentName.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentName.setBounds(40, 190, 108, 25);
		panel.add(lbEquipmentName);

		JLabel lbEquipmentStartDate = new JLabel("사용 시작일");
		lbEquipmentStartDate.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentStartDate.setForeground(Color.WHITE);
		lbEquipmentStartDate.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentStartDate.setBounds(40, 220, 108, 25);
		panel.add(lbEquipmentStartDate);

		JLabel lbExit = new JLabel("X");
		lbExit.setForeground(Color.WHITE);
		lbExit.setFont(new Font("굴림", Font.BOLD, 14));
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setBounds(331, 10, 57, 15);
		panel.add(lbExit);

		JLabel lbEquipmentEndDate = new JLabel("사용 만료일");
		lbEquipmentEndDate.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentEndDate.setForeground(Color.WHITE);
		lbEquipmentEndDate.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentEndDate.setBounds(40, 250, 108, 25);
		panel.add(lbEquipmentEndDate);

		JLabel lbEquipmentUseTime = new JLabel("사용 시간");
		lbEquipmentUseTime.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentUseTime.setForeground(Color.WHITE);
		lbEquipmentUseTime.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentUseTime.setBounds(40, 280, 108, 25);
		panel.add(lbEquipmentUseTime);

		JLabel lbEquipmentHourPay = new JLabel("시간 당 금액");
		lbEquipmentHourPay.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentHourPay.setForeground(Color.WHITE);
		lbEquipmentHourPay.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentHourPay.setBounds(40, 310, 108, 25);
		panel.add(lbEquipmentHourPay);

		JLabel lbEquipmentTotalPrice = new JLabel("총 사용 금액");
		lbEquipmentTotalPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lbEquipmentTotalPrice.setForeground(Color.WHITE);
		lbEquipmentTotalPrice.setFont(new Font("굴림", Font.BOLD, 17));
		lbEquipmentTotalPrice.setBounds(40, 340, 108, 25);
		panel.add(lbEquipmentTotalPrice);

		tfEquipmentName = new JTextField();
		tfEquipmentName.setBounds(180, 190, 150, 25);
		panel.add(tfEquipmentName);
		tfEquipmentName.setColumns(10);

		tfEquipmentStartDate = new JTextField();
		tfEquipmentStartDate.setColumns(10);
		tfEquipmentStartDate.setBounds(180, 220, 150, 25);
		panel.add(tfEquipmentStartDate);

		tfEquipmentEndDate = new JTextField();
		tfEquipmentEndDate.setColumns(10);
		tfEquipmentEndDate.setBounds(180, 250, 150, 25);
		panel.add(tfEquipmentEndDate);

		tfEquipmentUseTime = new JTextField();
		tfEquipmentUseTime.setColumns(10);
		tfEquipmentUseTime.setBounds(180, 280, 150, 25);
		panel.add(tfEquipmentUseTime);

		tfEquipmentHourPay = new JTextField();
		tfEquipmentHourPay.setColumns(10);
		tfEquipmentHourPay.setBounds(180, 310, 150, 25);
		panel.add(tfEquipmentHourPay);

		tfEquipmentTotalPrice = new JTextField();
		tfEquipmentTotalPrice.setColumns(10);
		tfEquipmentTotalPrice.setBounds(180, 341, 150, 25);
		panel.add(tfEquipmentTotalPrice);

		btnTotalPrice = new JButton("정산하기");
		btnTotalPrice.setFont(new Font("굴림", Font.BOLD, 25));
		btnTotalPrice.setForeground(Color.WHITE);
		btnTotalPrice.setBackground(Color.ORANGE);
		btnTotalPrice.setBounds(108, 71, 170, 60);
		panel.add(btnTotalPrice);
		lbExit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent ev) {
				EquipmentTotalPriceViewTab.this.dispose();
			}
		});
		btnTotalPrice.addActionListener(this);
		
		setedit();
	}
	void setedit() {
		tfEquipmentEndDate.setEditable(false);
		tfEquipmentHourPay.setEditable(false);
		tfEquipmentName.setEditable(false);
		tfEquipmentStartDate.setEditable(false);
		tfEquipmentTotalPrice.setEditable(false);
		tfEquipmentUseTime.setEditable(false);
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnTotalPrice) {
			EquipmentVO vo = new EquipmentVO();
			try {
				String name = tfEquipmentName.getText();
				int money = Integer.parseInt(tfEquipmentHourPay.getText());
				System.out.println(name);
				ArrayList list = dao.totalEquip(name, money);
				tfEquipmentStartDate.setText(String.valueOf((((ArrayList) list.get(0)).get(1))));
				tfEquipmentEndDate.setText(String.valueOf((((ArrayList) list.get(0)).get(2))));
				tfEquipmentUseTime.setText(String.valueOf((((ArrayList) list.get(0)).get(3))));
				tfEquipmentTotalPrice.setText(String.valueOf((((ArrayList) list.get(0)).get(5))));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("정산 실패" + e.getMessage());
			}
		}
	}
}
