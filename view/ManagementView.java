package view;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import view.SignUpView.moveWindows;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.JTextField;
public class ManagementView extends JFrame {
	private Image img_worker = new ImageIcon(ManagementView.class.getResource("/res/worker.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_work = new ImageIcon(ManagementView.class.getResource("/res/work.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_schedule = new ImageIcon(ManagementView.class.getResource("/res/schedule.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_area = new ImageIcon(ManagementView.class.getResource("/res/area.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_material = new ImageIcon(ManagementView.class.getResource("/res/material.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_equipment = new ImageIcon(ManagementView.class.getResource("/res/equipment.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_company = new ImageIcon(ManagementView.class.getResource("/res/company.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_logout = new ImageIcon(ManagementView.class.getResource("/res/logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_exit = new ImageIcon(ManagementView.class.getResource("/res/exit.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_main = new ImageIcon(ManagementView.class.getResource("/res/mainImage.jpg")).getImage().getScaledInstance(250, 135, Image.SCALE_SMOOTH);
	
	ImageIcon icon_worker = new ImageIcon(img_worker);
	ImageIcon icon_work = new ImageIcon(img_work);
	ImageIcon icon_schedule = new ImageIcon(img_schedule);
	ImageIcon icon_area = new ImageIcon(img_area);
	ImageIcon icon_material = new ImageIcon(img_material);
	ImageIcon icon_equipment = new ImageIcon(img_equipment);
	ImageIcon icon_company = new ImageIcon(img_company);
	ImageIcon icon_logout = new ImageIcon(img_logout);
	ImageIcon icon_exit = new ImageIcon(img_exit);
	ImageIcon icon_main = new ImageIcon(img_main);
	
	private WorkerView workerView;
	private WorkView workView;
	private ScheduleView scheduleView;
	private AreaView areaView;
	private MaterialView materialView;
	private EquipmentView equipmentView;
	private CompanyView companyView;

	private Point initialClick;
	private JPanel contentPane;
	public JTextField tfusername;
	
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

	public ManagementView() {
		
		workerView = new WorkerView();
		workView = new WorkView();
		scheduleView = new ScheduleView(); 
		areaView = new AreaView();
		materialView = new MaterialView();
		equipmentView = new EquipmentView();
		companyView = new CompanyView();
		
		
		this.addMouseListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		this.addMouseMotionListener(new moveWindows()); // 윈도우 이동하기 위해 설정
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel paneMenu = new JPanel();
		paneMenu.setBackground(new Color(65, 65, 65));
		paneMenu.setBounds(0, 0, 250, 700);
		contentPane.add(paneMenu);
		paneMenu.setLayout(null);
		
		JPanel paneWorker = new JPanel();
		paneWorker.addMouseListener(new PanelButtonMouseAdapter(paneWorker) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(workerView);
			}
		});
		paneWorker.setBackground(Color.DARK_GRAY);
		paneWorker.setBounds(0, 183, 250, 50);
		paneMenu.add(paneWorker);
		paneWorker.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("인력관리");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(90, 10, 128, 30);
		paneWorker.add(lblNewLabel);
		
		JLabel lbWorker = new JLabel(icon_worker);
		lbWorker.setBounds(30, 10, 30, 30);
		paneWorker.add(lbWorker);
		
		JPanel paneWork = new JPanel();
		paneWork.setBackground(Color.DARK_GRAY);
		paneWork.addMouseListener(new PanelButtonMouseAdapter(paneWork){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(workView);
			}
		});
		paneWork.setBounds(0, 233, 250, 50);
		paneMenu.add(paneWork);
		paneWork.setLayout(null);
		
		JLabel lblWork = new JLabel("작업관리");
		lblWork.setForeground(Color.ORANGE);
		lblWork.setHorizontalAlignment(SwingConstants.LEFT);
		lblWork.setFont(new Font("굴림", Font.BOLD, 18));
		lblWork.setBounds(90, 10, 128, 30);
		paneWork.add(lblWork);
		
		JLabel lbWork = new JLabel(icon_work);
		lbWork.setBounds(30, 10, 30, 30);
		paneWork.add(lbWork);
		
		JPanel paneSchedule = new JPanel();
		paneSchedule.setBackground(Color.DARK_GRAY);
		paneSchedule.addMouseListener(new PanelButtonMouseAdapter(paneSchedule){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(scheduleView);
			}
		});
		paneSchedule.setBounds(0, 283, 250, 50);
		paneMenu.add(paneSchedule);
		paneSchedule.setLayout(null);
		
		JLabel lblSchedule = new JLabel("스케줄관리");
		lblSchedule.setForeground(Color.ORANGE);
		lblSchedule.setHorizontalAlignment(SwingConstants.LEFT);
		lblSchedule.setFont(new Font("굴림", Font.BOLD, 18));
		lblSchedule.setBounds(90, 10, 128, 30);
		paneSchedule.add(lblSchedule);
		
		JLabel lbSchedule = new JLabel(icon_schedule);
		lbSchedule.setBounds(30, 10, 30, 30);
		paneSchedule.add(lbSchedule);
		
		JPanel paneArea = new JPanel();
		paneArea.setBackground(Color.DARK_GRAY);
		paneArea.addMouseListener(new PanelButtonMouseAdapter(paneArea){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(areaView);
			}
		});
		paneArea.setBounds(0, 333, 250, 50);
		paneMenu.add(paneArea);
		paneArea.setLayout(null);
		
		JLabel lblArea = new JLabel("공사부지관리");
		lblArea.setForeground(Color.ORANGE);
		lblArea.setHorizontalAlignment(SwingConstants.LEFT);
		lblArea.setFont(new Font("굴림", Font.BOLD, 18));
		lblArea.setBounds(90, 10, 128, 30);
		paneArea.add(lblArea);
		
		JLabel lbArea = new JLabel(icon_area);
		lbArea.setBounds(30, 10, 30, 30);
		paneArea.add(lbArea);
		
		JPanel paneMaterial = new JPanel();
		paneMaterial.setBackground(Color.DARK_GRAY);
		paneMaterial.addMouseListener(new PanelButtonMouseAdapter(paneMaterial){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(materialView);
			}
		});
		paneMaterial.setBounds(0, 383, 250, 50);
		paneMenu.add(paneMaterial);
		paneMaterial.setLayout(null);
		
		JLabel lblMaterial = new JLabel("자재관리");
		lblMaterial.setForeground(Color.ORANGE);
		lblMaterial.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaterial.setFont(new Font("굴림", Font.BOLD, 18));
		lblMaterial.setBounds(90, 10, 128, 30);
		paneMaterial.add(lblMaterial);
		
		JLabel lbMaterial = new JLabel(icon_material);
		lbMaterial.setBounds(30, 10, 30, 30);
		paneMaterial.add(lbMaterial);
		
		JPanel paneEquip = new JPanel();
		paneEquip.setBackground(Color.DARK_GRAY);
		paneEquip.addMouseListener(new PanelButtonMouseAdapter(paneEquip){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(equipmentView);
			}
		});
		paneEquip.setBounds(0, 433, 250, 50);
		paneMenu.add(paneEquip);
		paneEquip.setLayout(null);
		
		JLabel lblEquipment = new JLabel("중장비관리");
		lblEquipment.setForeground(Color.ORANGE);
		lblEquipment.setHorizontalAlignment(SwingConstants.LEFT);
		lblEquipment.setFont(new Font("굴림", Font.BOLD, 18));
		lblEquipment.setBounds(90, 10, 128, 30);
		paneEquip.add(lblEquipment);
		
		JLabel lbEquipment = new JLabel(icon_equipment);
		lbEquipment.setBounds(30, 10, 30, 30);
		paneEquip.add(lbEquipment);
		
		JPanel paneCompany = new JPanel();
		paneCompany.setBackground(Color.DARK_GRAY);
		paneCompany.addMouseListener(new PanelButtonMouseAdapter(paneCompany){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(companyView);
			}
		});
		paneCompany.setBounds(0, 483, 250, 50);
		paneMenu.add(paneCompany);
		paneCompany.setLayout(null);
		
		JLabel lblCompany = new JLabel("거래처관리");
		lblCompany.setForeground(Color.ORANGE);
		lblCompany.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompany.setFont(new Font("굴림", Font.BOLD, 18));
		lblCompany.setBounds(90, 10, 128, 30);
		paneCompany.add(lblCompany);
		
		JLabel lbCompany = new JLabel(icon_company);
		lbCompany.setBounds(30, 10, 30, 30);
		paneCompany.add(lbCompany);
		
		JPanel paneSignout = new JPanel();
		paneSignout.setLayout(null);
		paneSignout.setBackground(Color.DARK_GRAY);
		paneSignout.addMouseListener(new PanelButtonMouseAdapter(paneSignout));
		paneSignout.setBounds(0, 531, 250, 50);
		paneMenu.add(paneSignout);
		
		JLabel lblSignout = new JLabel("로그아웃");
		lblSignout.setForeground(Color.ORANGE);
		lblSignout.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignout.setFont(new Font("굴림", Font.BOLD, 18));
		lblSignout.setBounds(66, 10, 128, 30);
		paneSignout.add(lblSignout);
		
		JLabel lbLogout = new JLabel(icon_logout);
		lbLogout.setBounds(30, 10, 30, 30);
		paneSignout.add(lbLogout);
		
		JLabel lblNewLabel_2 = new JLabel("HAGADA");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(246, 203, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_2.setBounds(12, 134, 226, 50);
		paneMenu.add(lblNewLabel_2);
		
		JLabel lbExit = new JLabel(icon_exit);
		lbExit.setHorizontalAlignment(SwingConstants.CENTER);
		lbExit.setBounds(74, 620, 109, 60);
		paneMenu.add(lbExit);
		lbExit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent ev) {
				if(JOptionPane.showConfirmDialog(null, "프로그램을 종료 하시겠습니까?", "EXIT", JOptionPane.YES_NO_OPTION) == 0) {
					ManagementView.this.dispose();
				}
			}
		
		}); 
		
		JLabel lblNewLabel_1 = new JLabel("현재 사용자 : ");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(49, 600, 86, 15);
		paneMenu.add(lblNewLabel_1);
		
		JLabel lbMainImage = new JLabel(icon_main);
		lbMainImage.setBounds(0, 0, 250, 135);
		paneMenu.add(lbMainImage);
		
		tfusername = new JTextField();
		tfusername.setEditable(false);
		tfusername.setBackground(Color.DARK_GRAY);
		tfusername.setForeground(Color.WHITE);
		tfusername.setBounds(133, 600, 78, 15);
		paneMenu.add(tfusername);
		tfusername.setColumns(10);
		
		JPanel paneView = new JPanel();
		paneView.setBackground(Color.GRAY);
		paneView.setToolTipText("");
		paneView.setBounds(251, 0, 1049, 700);
		contentPane.add(paneView);
		setUndecorated(true);
		paneView.setLayout(null);
		
		paneView.add(workerView);
		paneView.add(workView);
		paneView.add(scheduleView);
		paneView.add(areaView);
		paneView.add(materialView);
		paneView.add(equipmentView);
		paneView.add(companyView);
		
		menuClicked(workerView);

	}
	public void menuClicked(JPanel panel) {
		workerView.setVisible(false);
		workView.setVisible(false);
		scheduleView.setVisible(false);
		areaView.setVisible(false);
		materialView.setVisible(false);
		equipmentView.setVisible(false);
		companyView.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(Color.black);
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(Color.DARK_GRAY);
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(Color.gray);
		
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(Color.DARK_GRAY);
			
		}
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			panel.setBackground(Color.black);
//			
//		}
	}
}
