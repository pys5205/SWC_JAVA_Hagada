package view;

import java.awt.event.*;

import model.*;
import model.rec.SignUpVo;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.SignUpDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SignUpView extends JFrame {
	private Point initialClick;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfPW;
	private JTextField tfPWCheck;
	private JTextField tfName;
	private JTextField tfJumin;
	private JTextField tfTel;
	private JButton btSignup;
	SignUpDao dao;
	private Image signupbtn = new ImageIcon(SignUpView.class.getResource("/res/btSignupIcon.png")).getImage()
			.getScaledInstance(200, 55, Image.SCALE_SMOOTH);

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

	public SignUpView() {
		try {
			dao = new SignUpDao();
			System.out.println("회원가입 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "회원가입 DB 연결 실패 : " + e.getMessage());
		}
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(65, 65, 65));
		setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbName = new JLabel("이름");
		lbName.setForeground(Color.WHITE);
		lbName.setHorizontalAlignment(SwingConstants.RIGHT);
		lbName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lbName.setBounds(62, 303, 60, 30);
		contentPane.add(lbName);

		JLabel lbJumin = new JLabel("주민번호");
		lbJumin.setForeground(Color.WHITE);
		lbJumin.setHorizontalAlignment(SwingConstants.RIGHT);
		lbJumin.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lbJumin.setBounds(40, 363, 82, 30);
		contentPane.add(lbJumin);

		JLabel lbTel = new JLabel("연락처");
		lbTel.setForeground(Color.WHITE);
		lbTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTel.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lbTel.setBounds(62, 423, 60, 30);
		contentPane.add(lbTel);

		JLabel lblId = new JLabel("아이디");
		lblId.setForeground(Color.WHITE);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblId.setBounds(62, 123, 60, 30);
		contentPane.add(lblId);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(133, 123, 180, 30);
		contentPane.add(tfID);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setForeground(Color.WHITE);
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblPw.setBounds(36, 183, 86, 30);
		contentPane.add(lblPw);

		JButton btOverlapCheck = new JButton("중복확인");
		btOverlapCheck.setFont(new Font("굴림", Font.BOLD, 13));
		btOverlapCheck.setForeground(Color.WHITE);
		btOverlapCheck.setBackground(Color.BLACK);
		btOverlapCheck.setBounds(326, 123, 100, 30);
		contentPane.add(btOverlapCheck);

		JLabel lblPWCheck = new JLabel("비밀번호 확인");
		lblPWCheck.setForeground(Color.WHITE);
		lblPWCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPWCheck.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblPWCheck.setBounds(0, 243, 122, 30);
		contentPane.add(lblPWCheck);

		ImageIcon signupImg = new ImageIcon(signupbtn);
		btSignup = new JButton(signupImg);
		btSignup.setBorderPainted(false);
		btSignup.setBounds(126, 478, 197, 55);
		contentPane.add(btSignup);
		btSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				if(o == btSignup) {
					String emp_name = tfName.getText();
					String emp_id = tfID.getText();
					String emp_pw = tfPW.getText();
					String emp_pw2 = tfPWCheck	.getText();
					String emp_ssn = tfJumin.getText();
					String emp_tel = tfTel.getText();
					if(emp_pw.equals(emp_pw2)) {
						SignUpVo vo = new SignUpVo(emp_name, emp_id, emp_pw, emp_ssn, emp_tel);
						try {
							dao.regist(vo);
							System.out.println("회원가입 성공");
							clearScreen();
							dispose();
						} catch (Exception e2) {
							// TODO: handle exception
							System.out.println("회원가입실패"+e2.getMessage());
						}
					}else {
						System.out.println("비번1과 비번2가틀립니다");
					}
				}
			}
		});

		JLabel lbexit = new JLabel("X");
		lbexit.setFont(new Font("굴림", Font.BOLD, 12));
		lbexit.setHorizontalAlignment(SwingConstants.CENTER);
		lbexit.setForeground(Color.WHITE);
		lbexit.setBounds(412, 10, 26, 15);
		contentPane.add(lbexit);

		tfPW = new JTextField();
		tfPW.setColumns(10);
		tfPW.setBounds(133, 183, 180, 30);
		contentPane.add(tfPW);

		tfPWCheck = new JTextField();
		tfPWCheck.setColumns(10);
		tfPWCheck.setBounds(133, 243, 180, 30);
		contentPane.add(tfPWCheck);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(133, 303, 180, 30);
		contentPane.add(tfName);

		tfJumin = new JTextField();
		tfJumin.setColumns(10);
		tfJumin.setBounds(133, 363, 180, 30);
		contentPane.add(tfJumin);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(133, 423, 180, 30);
		contentPane.add(tfTel);

		JLabel lblNewLabel_2 = new JLabel("HAGADA");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(new Color(246, 203, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_2.setBounds(12, 10, 261, 36);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("간단한 인적사항을 입력해주세요.");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(12, 47, 261, 36);
		contentPane.add(lblNewLabel_2_1);
		lbexit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent ev) {
				if (JOptionPane.showConfirmDialog(null, "로그인화면으로 돌아가시겠습니까?", "SignUp", JOptionPane.YES_NO_OPTION) == 0) {
					SignUpView.this.dispose();
				}
			}

		});
	}
	public void clearScreen() {
		tfName.setText(null);
		tfTel.setText(null);
		tfPW.setText(null);
		tfID.setText(null);
		tfPWCheck.setText(null);
		tfJumin.setText(null);
	}
}
