import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.michaelbaranov.microba.calendar.DatePicker;

public class UsagePanel extends JPanel {

	private static final long serialVersionUID = -2195835028220798096L;

	public UsagePanel() {
		initUI();
	}

	private void initUI() {
		
		setLayout(null);

		// 타이틀 라벨
		JLabel titleLabel = new JLabel("실시간 사용 현황");
		titleLabel.setBounds(450, 20, 100, 30);
		this.add(titleLabel);
		
		// 오늘의 라벨
		DateFormat sdFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);
		
		JLabel todayLabel = new JLabel(tempDate);
		todayLabel.setBounds(500 + 300 - 50, 20, 100, 30);
		this.add(todayLabel);

		/*
		 * JTable roomList = new JTable(); TableColumnModel columnModel = new
		 * DefaultTableColumnModel(); TableColumn col = new TableColumn();
		 * col.setHeaderValue("asdf"); columnModel.addColumn(col);
		 * 
		 * col = new TableColumn(); col.setHeaderValue("qwer");
		 * columnModel.addColumn(col);
		 * 
		 * roomList.setColumnModel(columnModel); //roomList.setBounds(0, 0, 600,
		 * 400);
		 */

		// 사용현황 테이블
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2"},
				{ "Row2-Column1", "Row2-Column2"} };
		Object columnNames[] = { "Column One", "Column Two" };
		JTable table = new JTable(rowData, columnNames);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);
		
		scrPane.setBounds(30, 70, 600, 400);
		this.add(scrPane);
		
		// 데이트 피커
		DatePicker datePicker = new DatePicker(new Date());
		datePicker.setBounds(30, 500, 300, 25);
		this.add(datePicker);
		
		// 콤보박스
		JComboBox<String> comboBox = new JComboBox<String>();
		String[] a = {"room1", "room2"};
		comboBox.setModel(new DefaultComboBoxModel<String>(a));
		comboBox.setBounds(370, 500, 100, 25);
		this.add(comboBox);
		
		// 상세 정보 조회
		JButton detail = new JButton("상세 정보 조회");
		detail.setBounds(500, 500, 120, 25);
		detail.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDetailFrame();
			}});
		this.add(detail);
		
		// 사용자 예약
		JButton reserv = new JButton("사용자 예약");
		reserv.setBounds(650, 500, 100, 25);
		reserv.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showReservFrame();
				
			}});
		this.add(reserv);
	}
	
	private void showDetailFrame(){
		
		JFrame frame = new JFrame();
		
		frame.setSize(DetailInfoPanel.width, DetailInfoPanel.height);
		frame.setTitle("상세 정보 조회");
		frame.setResizable(false);
		
		frame.add(new DetailInfoPanel("IT_101", new Date()));
		
		frame.setVisible(true);
	}
	
	private void showReservFrame(){
		
		JFrame frame = new JFrame();
		
		frame.setSize(ReservPanel.width,ReservPanel.height);
		frame.setTitle("사용자 예약");
		frame.setResizable(false);

		frame.add(new ReservPanel());
		
		frame.setVisible(true);
	}

}
