package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.tab.AreaViewTab;
import view.tab.ScheduleViewTab;
import javax.swing.border.EmptyBorder;

public class AreaView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AreaView() {
		setBounds(0, 0, 1049, 700);
		setBackground(Color.gray);
		setLayout(null); 
		
		AreaViewTab areaTab = new AreaViewTab();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1049, 700);
		tabbedPane.add(areaTab,"공사부지관리");
		add(tabbedPane);
	}

}
