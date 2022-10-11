package view.tab;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import view.model.ChartDao;
import view.model.WorkCompleteDAO;
import view.model.WorkTeamDAO;

import javax.swing.JScrollPane;

public class WorkCompleteViewTab extends JPanel implements ActionListener {

	private Image img_search = new ImageIcon(WorkCompleteViewTab.class.getResource("/res/searchIcon.png")).getImage()
			.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
	private Image img_refresh = new ImageIcon(WorkCompleteViewTab.class.getResource("/res/refresh.png")).getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	ImageIcon icon_search = new ImageIcon(img_search);
	ImageIcon icon_refresh = new ImageIcon(img_refresh);
	JTextField tfSearch, tfMemo;
	JTable table;
	WorkCompleteDAO dao = null;
	ChartDao chartdao = null;
	JPanel panelChart;
	DefaultCategoryDataset database = null;
	JComboBox cbSearch;
	AllCompleteWork model = null;
	ArrayList<Integer> result = new ArrayList<Integer>();

	/**
	 * Create the panel.
	 */
	public WorkCompleteViewTab() {
		database = new DefaultCategoryDataset();
		model = new AllCompleteWork();
		addLayout();
		addChart();

		try {
			dao = new WorkCompleteDAO();

			System.out.println("완료 작업 연결");

			ArrayList list = dao.allWorkView();
			model.data = list;
			table.setModel(model);

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "완료 작업 연결 실패 : " + e.getMessage());
		}

	}

	void addChart() {

		try {
			chartdao = new ChartDao();

			result = chartdao.getCount();

			panelChart = new JPanel();
			panelChart.setBounds(461, 198, 587, 392);
			panelChart.setBackground(Color.GRAY);
			panelChart.setPreferredSize(new java.awt.Dimension(100, 100));
			add(panelChart);
			model.fireTableDataChanged();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "차트 로딩 실패 : " + e.getMessage());
		}

		database.addValue(result.get(0), "count", "대기");
		database.addValue(result.get(1), "count", "진행");
		database.addValue(result.get(2), "count", "완료");
		database.addValue(result.get(3), "count", "삭제");

		JFreeChart barChart = ChartFactory.createBarChart("작업상황차트", "작업상황", "작업수", database, PlotOrientation.VERTICAL,
				true, true, true);
		Font f = new Font("Gulim", Font.BOLD, 13);
		Font f2 = new Font("Gulim", Font.BOLD, 18);
		barChart.getTitle().setFont(f2);

		CategoryPlot plot = barChart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(f);
		plot.getDomainAxis().setTickLabelFont(f);
		plot.getRangeAxis().setLabelFont(f);
		plot.getRangeAxis().setTickLabelFont(f);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(Color.WHITE);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 380));
		panelChart.add(chartPanel);
	}

	void addLayout() {
		setBounds(262, 10, 1026, 680);
		setBackground(Color.gray);
		setLayout(null);

		JLabel lbrefresh = new JLabel(icon_refresh);
		lbrefresh.setBounds(745, 595, 50, 50);
		add(lbrefresh);
		lbrefresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addChart();
				JOptionPane.showMessageDialog(null, "차트 새로고침 완료!!");
			}
		});

		JPanel panelEndWorkSearch = new JPanel();
		panelEndWorkSearch.setBorder(new LineBorder(Color.WHITE, 1, true));
		panelEndWorkSearch.setBackground(Color.GRAY);
		panelEndWorkSearch.setBounds(12, 10, 1020, 178);
		add(panelEndWorkSearch);
		panelEndWorkSearch.setLayout(null);

		JLabel lbSearch = new JLabel(icon_search);
		lbSearch.setForeground(Color.ORANGE);
		lbSearch.setFont(new Font("굴림", Font.BOLD, 15));
		lbSearch.setBounds(324, 138, 30, 30);
		panelEndWorkSearch.add(lbSearch);

		String Search[] = { "작업명", "팀장", "작업 상세 위치" };
		cbSearch = new JComboBox(Search);
		cbSearch.setForeground(Color.DARK_GRAY);
		cbSearch.setFont(new Font("굴림", Font.BOLD, 12));
		cbSearch.setBackground(Color.LIGHT_GRAY);
		cbSearch.setBounds(12, 138, 82, 30);
		panelEndWorkSearch.add(cbSearch);

		tfSearch = new JTextField();
		tfSearch.setColumns(10);
		tfSearch.setBounds(97, 138, 221, 30);
		panelEndWorkSearch.add(tfSearch);
		tfSearch.addActionListener(this);

		JLabel lbMemo = new JLabel("특이사항");
		lbMemo.setForeground(Color.WHITE);
		lbMemo.setFont(new Font("굴림", Font.BOLD, 15));
		lbMemo.setBounds(12, 10, 102, 18);
		panelEndWorkSearch.add(lbMemo);

		tfMemo = new JTextField();
		tfMemo.setColumns(10);
		tfMemo.setBackground(Color.WHITE);
		tfMemo.setBounds(12, 30, 623, 98);
		tfMemo.setEditable(false);
		panelEndWorkSearch.add(tfMemo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 205, 423, 379);
		scrollPane.getViewport().setBackground(Color.gray);
		add(scrollPane);
		
		model = new AllCompleteWork();
		table = new JTable(model);
		table.setForeground(Color.WHITE);
		table.setBackground(Color.GRAY);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// String memo;
				int row = table.getSelectedRow();
				int col = 0; // col은 0부터 시작
				int data = (Integer) table.getValueAt(row, col);
				try {
					tfMemo.setText(dao.memoPrint(data));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "조회 실패" + e1.getMessage());
				}
			}
		});
	}

	public void actionPerformed(ActionEvent ev) {
		Object o = ev.getSource();
		if (o == tfSearch) {
			int sel = cbSearch.getSelectedIndex();
			String text = tfSearch.getText();
			try {
				ArrayList list = dao.workSearch(sel, text);
				model.data = list;
				table.setModel(model);
				model.fireTableDataChanged();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class AllCompleteWork extends AbstractTableModel {
	ArrayList data = new ArrayList();
	String[] columnNames = { "작업번호", "작업명", "담당팀", "시작날짜", "종료날짜", "작업상태" };

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int row, int col) {
		ArrayList temp = (ArrayList) data.get(row);
		return temp.get(col);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
}