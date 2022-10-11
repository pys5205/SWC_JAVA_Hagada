import javax.swing.border.*;

import model.LoginDao;
import model.SignUpDao;
import model.rec.LoginVo;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

import view.SignUpView.*;
import view.*;
import view.tab.*;

public class Login extends JFrame {

	private Image img_login = new ImageIcon(Login.class.getResource("res/loginIcon.png")).getImage()
			.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_loginbtn = new ImageIcon(Login.class.getResource("res/btLoginIcon.png")).getImage()
			.getScaledInstance(200, 60, Image.SCALE_SMOOTH);
	private Image img_signupbtn = new ImageIcon(Login.class.getResource("res/btSignupIcon.png")).getImage()
			.getScaledInstance(200, 55, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	JButton btSignup;
	JButton btLogin;
	private JTextField tfPW;
	private JTextField tfID;
	private Point initialClick;
	LoginDao dao;

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

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login login = new Login();
//					login.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			dao = new LoginDao();
			System.out.println("로그인 DB연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "로그인 DB 연결 실패 : " + e.getMessage());
		}

		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		setTitle("로그인창");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		// contentPane.setBackground(new Color(189, 204, 255));
		contentPane.setBackground(new Color(65, 65, 65));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon signupImg = new ImageIcon(img_signupbtn);
		btSignup = new JButton(signupImg);
		btSignup.setForeground(Color.BLACK);
		btSignup.setBackground(Color.YELLOW);
		btSignup.setBorderPainted(false);
		btSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				if (o == btSignup) {
					SignUpView signup = new SignUpView();
					signup.setVisible(true);
				}
			}
		});

		btSignup.setBounds(100, 485, 200, 55);
		contentPane.add(btSignup);

		tfPW = new JTextField();
		tfPW.setBounds(89, 348, 221, 30);
		contentPane.add(tfPW);
		tfPW.setColumns(10);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(89, 295, 221, 30);
		contentPane.add(tfID);

		JLabel lbID = new JLabel("ID");
		lbID.setFont(new Font("Arial", Font.BOLD, 20));
		lbID.setHorizontalAlignment(SwingConstants.CENTER);
		lbID.setForeground(new Color(246, 203, 0));
		lbID.setBounds(29, 295, 60, 30);
		contentPane.add(lbID);

		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setForeground(new Color(246, 203, 0));
		lblPw.setFont(new Font("Arial", Font.BOLD, 20));
		lblPw.setBounds(29, 348, 60, 30);
		contentPane.add(lblPw);

		ImageIcon loginImg = new ImageIcon(img_loginbtn);
		JButton btLogin = new JButton(loginImg);
		btLogin.setForeground(Color.BLACK);
		btLogin.setBackground(Color.YELLOW);
		btLogin.setBounds(100, 405, 200, 60);
		btLogin.setBorderPainted(false); // 버튼 테두리 없애기
		btLogin.setOpaque(false);
		contentPane.add(btLogin);

//		btLogin.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				Object o = ev.getSource();
//				if (o == btLogin) {
//					ManagementView main = new ManagementView();
//					main.setVisible(true);
//					dispose();
//				}
//
//			}
//		});
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Object o = ev.getSource();
				if (o == btLogin) {
					String id = tfID.getText();
					String pass = tfPW.getText();
					try {
						if (dao.login(id, pass) == true) {
							System.out.println(id + "로 로그인합니다");
							ManagementView main = new ManagementView();
							main.setVisible(true);
							
							main.tfusername.setText(tfID.getText());
							dispose();
						} else {
							System.out.println("비밀번호가틀렷습니다");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("로그인실패입니다");
					}
				}

			}
		});

		JLabel lbTitle = new JLabel("HAGADA");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(246, 203, 0));
		lbTitle.setFont(new Font("Arial", Font.BOLD, 50));
		lbTitle.setBounds(19, 194, 362, 75);
		contentPane.add(lbTitle);

		JLabel lbexit = new JLabel("X");
		lbexit.setForeground(Color.WHITE);
		lbexit.setHorizontalAlignment(SwingConstants.CENTER);
		lbexit.setBounds(374, 10, 26, 15);
		contentPane.add(lbexit);

		JLabel lbLoginIcon = new JLabel();
		lbLoginIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lbLoginIcon.setBounds(100, 36, 200, 200);
		lbLoginIcon.setIcon(new ImageIcon(img_login));
		contentPane.add(lbLoginIcon);

		lbexit.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent ev) {
				if (JOptionPane.showConfirmDialog(null, "프로그램을 종료 하시겠습니까?", "EXIT", JOptionPane.YES_NO_OPTION) == 0) {
					Login.this.dispose();
				}
			}

		});

		// bSignup.addActionListener(this);
	}

}
