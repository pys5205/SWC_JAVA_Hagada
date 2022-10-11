import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;

import view.ManagementView;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;

public class SplashScreen extends JWindow {
	
//	private Image img_loading = new ImageIcon(ManagementView.class.getResource("/res/loading.gif")).getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
//	
//	 ImageIcon icon_worker = new ImageIcon(img_loading);
	 Icon loading = new ImageIcon(getClass().getResource("/res/loading2.gif"));
	Login login = new Login();

	private JPanel contentPane;


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 100, 500, 300);
		
		SplashScreen sp = new SplashScreen(frame);
	}


	public SplashScreen(JFrame frame) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(loading);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(0, 0, 500, 300);
		contentPane.add(lblNewLabel);
		
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		
		int width = 500, height = 300;

//		outerHeight = 1040
//		outerWidth = 1920
		int totalWidth = 1536, totalHeight = 816;
		
		this.setSize(width, height);
		
		this.setLocation((int)(totalWidth-width)/2, (int)(totalHeight - height)/2);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				login.setVisible(true);
				dispose();
			}
		}, 4000);
	}
}
