package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.tab.WorkCompleteViewTab;

import view.tab.WorkTeamViewTab;
import view.tab.WorkPlanViewTab;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class WorkView extends JPanel {

	/**
	 * Create the panel.
	 */
	public WorkView() {
		WorkPlanViewTab workTab = new WorkPlanViewTab();
	
		WorkCompleteViewTab workCompleteTab = new WorkCompleteViewTab();
		WorkTeamViewTab workTeamTab = new WorkTeamViewTab();
		setBounds(0, 0, 1049, 700);
		setBackground(Color.gray);
		setLayout(null);
		
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1049, 700);
		tabbedPane.add(workTab,"예정작업관리");

		tabbedPane.add(workCompleteTab,"완료작업관리");
		tabbedPane.add(workTeamTab,"팀관리");
		
		add(tabbedPane);
		
	}
}
