package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.tab.*;

import javax.swing.JButton;
import javax.swing.JTextField;

public class WorkerView extends JPanel {


   public WorkerView() {
      WorkerViewTab workertab = new WorkerViewTab();
      SalaryViewTab salarytab = new SalaryViewTab();
      CommuteViewTab commutetab = new CommuteViewTab();
      
      setBounds(0, 0, 1049, 700);
      setBackground(Color.gray);
      setLayout(null);      

      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(0, 0, 1049, 700);
      tabbedPane.add(workertab,"인력관리");
      workertab.setLayout(null);
      add(tabbedPane);
      tabbedPane.add(commutetab,"출퇴근관리");
      add(tabbedPane);
      tabbedPane.add(salarytab,"월급관리");
      add(tabbedPane);
   }
}