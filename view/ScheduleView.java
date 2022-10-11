package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.tab.ScheduleViewTab;

public class ScheduleView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ScheduleView() {
		setBounds(0, 0, 1049, 700);
		setBackground(Color.gray);
		setLayout(null); 
		
		ScheduleViewTab scheduleTab = new ScheduleViewTab();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1049, 700);
		tabbedPane.add(scheduleTab,"스케줄관리");
		add(tabbedPane);
		
		
	}

}
