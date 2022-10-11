package view.tab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.model.DrawingDAO;
import view.model.rec.DrawingVO;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AreaDrawingInsertTab extends JFrame implements ActionListener {
	private Point initialClick;
	JTextField tfDrawingVer, tfDrawingIMG, tfAreaNo, tfDrawingType;
	JButton btnDrawingInsert;
	DrawingDAO dao = null;

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

	public AreaDrawingInsertTab() {

		try {
			dao = new DrawingDAO();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addLayout();
		eventProc();
		setStyle();
	}

	void addLayout() {
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 320);
		getContentPane().setLayout(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 400, 320);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lbDrawingVer = new JLabel("도면 버전");
		lbDrawingVer.setForeground(Color.WHITE);
		lbDrawingVer.setHorizontalAlignment(SwingConstants.LEFT);
		lbDrawingVer.setFont(new Font("굴림", Font.BOLD, 18));
		lbDrawingVer.setBounds(50, 75, 85, 30);
		panel.add(lbDrawingVer);

		JLabel lbExit = new JLabel("X");
		lbExit.setForeground(Color.WHITE);
		lbExit.setFont(new Font("굴림", Font.BOLD, 14));
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setBounds(331, 19, 57, 15);
		panel.add(lbExit);

		tfDrawingVer = new JTextField();
		tfDrawingVer.setColumns(10);
		tfDrawingVer.setBounds(185, 75, 135, 30);
		panel.add(tfDrawingVer);

		JLabel lbDrawingType = new JLabel("도면 분야");
		lbDrawingType.setHorizontalAlignment(SwingConstants.LEFT);
		lbDrawingType.setForeground(Color.WHITE);
		lbDrawingType.setFont(new Font("굴림", Font.BOLD, 18));
		lbDrawingType.setBounds(50, 115, 85, 30);
		panel.add(lbDrawingType);

		JLabel lbDrawingIMG = new JLabel("도면 이미지");
		lbDrawingIMG.setHorizontalAlignment(SwingConstants.LEFT);
		lbDrawingIMG.setForeground(Color.WHITE);
		lbDrawingIMG.setFont(new Font("굴림", Font.BOLD, 18));
		lbDrawingIMG.setBounds(50, 155, 101, 30);
		panel.add(lbDrawingIMG);

		tfDrawingIMG = new JTextField();
		tfDrawingIMG.setColumns(10);
		tfDrawingIMG.setBounds(185, 155, 135, 30);
		panel.add(tfDrawingIMG);

		String drawingType[] = { "건축도면", "구조도면", "전기설비도면", "위생설비도면", "기계설비도면" };

		btnDrawingInsert = new JButton("추가하기");
		btnDrawingInsert.setForeground(Color.WHITE);
		btnDrawingInsert.setFont(new Font("굴림", Font.BOLD, 18));
		btnDrawingInsert.setBackground(Color.ORANGE);
		btnDrawingInsert.setBounds(117, 243, 156, 45);
		panel.add(btnDrawingInsert);
		
		JLabel lbAreaNo = new JLabel("부지 번호");
		lbAreaNo.setHorizontalAlignment(SwingConstants.LEFT);
		lbAreaNo.setForeground(Color.WHITE);
		lbAreaNo.setFont(new Font("굴림", Font.BOLD, 18));
		lbAreaNo.setBounds(50, 35, 101, 30);
		panel.add(lbAreaNo);
		
		tfAreaNo = new JTextField();
		tfAreaNo.setColumns(10);
		tfAreaNo.setBounds(185, 35, 135, 30);
		panel.add(tfAreaNo);
		
		tfDrawingType = new JTextField();
		tfDrawingType.setColumns(10);
		tfDrawingType.setBounds(185, 115, 135, 30);
		panel.add(tfDrawingType);
		lbExit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent ev) {
				if (JOptionPane.showConfirmDialog(null, "도면 추가를 취소하시겠습니까?", "Notice", JOptionPane.YES_NO_OPTION) == 0) {
					AreaDrawingInsertTab.this.dispose();
				}
			}

		});
	}

	void setStyle() {
		tfAreaNo.setEditable(false);
	}

	void eventProc() {
		btnDrawingInsert.addActionListener(this);
		tfDrawingVer.addActionListener(this);
		tfDrawingIMG.addActionListener(this);
		tfDrawingType.addActionListener(this);
		tfAreaNo.addActionListener(this);
		setStyle();
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == btnDrawingInsert) {
			String dVersion = tfDrawingVer.getText();
			String dType = tfDrawingType.getText();
			String dImage = tfDrawingIMG.getText();
			int aNum = Integer.parseInt(tfAreaNo.getText());
			
			DrawingVO vo = new DrawingVO(dVersion, dType, dImage, aNum);
			try {
				dao.DrawingInsert(vo);
				System.out.println("도면 등록 완료");
				clearScreen();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showConfirmDialog(null, "도면 등록 실패" + e.getMessage());
			}
		}
	}

	private void clearScreen() {
		// TODO Auto-generated method stub
		tfDrawingIMG.setText("");
		tfDrawingVer.setText("");
		tfDrawingType.setText("");
		tfAreaNo.setText("");
	}
}
