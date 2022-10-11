package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.tab.MaterialViewTab;

public class MaterialView extends JPanel {

	/**
	 * Create the panel.
	 */
	public MaterialView() {
		setBounds(0, 0, 1049, 700);
		setBackground(Color.gray);
		setLayout(null);
		
		MaterialViewTab materialViewTab = new MaterialViewTab();
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1049, 700);
		tabbedPane.add(materialViewTab,"자재 관리");
		
		add(tabbedPane);
	}

}
