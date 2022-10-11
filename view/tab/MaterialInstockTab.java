package view.tab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MaterialInstockTab extends JFrame {
	private Point initialClick;
	private JTextField tfInstockMemo;
	private JTextField tfInstockCount;

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

	public MaterialInstockTab() {
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
		
		JLabel lbInstockCount = new JLabel("입고수량");
		lbInstockCount.setForeground(Color.WHITE);
		lbInstockCount.setHorizontalAlignment(SwingConstants.LEFT);
		lbInstockCount.setFont(new Font("굴림", Font.BOLD, 17));
		lbInstockCount.setBounds(60, 85, 75, 30);
		panel.add(lbInstockCount);
		
		JLabel lbInstockMemo = new JLabel("특이사항");
		lbInstockMemo.setHorizontalAlignment(SwingConstants.LEFT);
		lbInstockMemo.setForeground(Color.WHITE);
		lbInstockMemo.setFont(new Font("굴림", Font.BOLD, 17));
		lbInstockMemo.setBounds(60, 130, 75, 30);
		panel.add(lbInstockMemo);
		
		JButton btnInstock = new JButton("입고하기");
		btnInstock.setBackground(Color.ORANGE);
		btnInstock.setForeground(Color.WHITE);
		btnInstock.setFont(new Font("굴림", Font.BOLD, 18));
		btnInstock.setBounds(120, 425, 156, 45);
		panel.add(btnInstock);
		
		JLabel lbExit = new JLabel("X");
		lbExit.setForeground(Color.WHITE);
		lbExit.setFont(new Font("굴림", Font.BOLD, 14));
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setBounds(331, 10, 57, 15);
		panel.add(lbExit);
		
		tfInstockMemo = new JTextField();
		tfInstockMemo.setBounds(60, 170, 290, 220);
		panel.add(tfInstockMemo);
		tfInstockMemo.setColumns(10);
		
		tfInstockCount = new JTextField();
		tfInstockCount.setBounds(147, 85, 90, 30);
		panel.add(tfInstockCount);
		tfInstockCount.setColumns(10);
		lbExit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent ev) {
				if (JOptionPane.showConfirmDialog(null, "입고를 취소하시겠습니까?", "Notice", JOptionPane.YES_NO_OPTION) == 0) {
					MaterialInstockTab.this.dispose();
				}
			}

		});

	}
}
