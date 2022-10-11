package view.tab;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.SignUpView;
import view.ManagementView.moveWindows;
import view.model.WorkPlanDAO;
import view.model.rec.WorkVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class WorkCompleteMemo extends JFrame implements ActionListener{
	JTextField tfMemo;
	Point initialClick;
	JButton btSend;
	JLabel lbworkName;

	WorkPlanViewTab vt = null;
	WorkPlanDAO dao = null;
	WorkVO vo = null;
	
	String workName = "";
	
	
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




	public WorkCompleteMemo(String workName) {
		addLayout();
		eventProc();
		this.workName = workName;
		lbworkName.setText(workName);
        try {
            dao = new WorkPlanDAO();
            System.out.println("특이사항 전송 연결");
         }catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "특이사항 전송 연결 실패 : " + e.getMessage());
         }
	}
	
	void addLayout() {
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
		
		JLabel label = new JLabel("작업명");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(60, 86, 57, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("특이사항");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(60, 115, 75, 20);
		panel.add(label_1);
		
		tfMemo = new JTextField();
		tfMemo.setBounds(59, 140, 265, 194);
		panel.add(tfMemo);
		tfMemo.setColumns(10);
		
		btSend = new JButton("특이사항 전송");
		btSend.setBackground(Color.ORANGE);
		btSend.setForeground(Color.WHITE);
		btSend.setFont(new Font("굴림", Font.BOLD, 18));
		btSend.setBounds(114, 350, 156, 45);
		panel.add(btSend);
		
		JLabel lbExit = new JLabel("X");
		lbExit.setForeground(Color.WHITE);
		lbExit.setFont(new Font("굴림", Font.BOLD, 14));
		lbExit.setHorizontalAlignment(SwingConstants.RIGHT);
		lbExit.setBounds(331, 10, 57, 15);
		panel.add(lbExit);
		
		lbworkName = new JLabel();
		lbworkName.setForeground(Color.ORANGE);
		lbworkName.setFont(new Font("굴림", Font.BOLD, 20));
		lbworkName.setBounds(114, 64, 210, 40);
		panel.add(lbworkName);
		lbExit.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent ev) {
				if (JOptionPane.showConfirmDialog(null, "작업이 완료되지 않았습니까?", "Notice", JOptionPane.YES_NO_OPTION) == 0) {
					WorkCompleteMemo.this.dispose();
				}
			}
		});
	}

	void eventProc() {
		btSend.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		
		if(o == btSend) {
			
			String memo = tfMemo.getText();
			
			try {
				
				dao.workComplete(memo, workName);
				 JOptionPane.showMessageDialog(null, "작업이 완료되었습니다!!" );
				 WorkCompleteMemo.this.dispose();
			}catch (Exception e) {
				// TODO: handle exception
				 JOptionPane.showMessageDialog(null, "특이사항 전송 실패" + e.getMessage() );
			}
		}
	}

}
