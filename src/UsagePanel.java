import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.michaelbaranov.microba.calendar.DatePicker;

public class UsagePanel extends JPanel {

	public static int width = 800;
	public static int height = 600;

	private static final long serialVersionUID = -2195835028220798096L;
	
	private DatePicker datePicker;
	private JComboBox<String> comboBox;
	
	private DefaultTableModel usageTableModel;

	public UsagePanel() {
		initUI();
	}

	private void initUI() {

		setLayout(null);

		// 타이틀 라벨
		JLabel titleLabel = new JLabel("실시간 사용 현황");
		titleLabel.setBounds(400 - 50, 20, 100, 30);
		this.add(titleLabel);

		// 오늘의 라벨
		DateFormat sdFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);

		JLabel todayLabel = new JLabel(tempDate);
		todayLabel.setBounds(400 + 300 - 50, 20, 100, 30);
		this.add(todayLabel);

		// 사용현황 테이블
		JTable table = new JTable();
		
		usageTableModel = new DefaultTableModel();
		usageTableModel.addColumn("세미나 실");
		usageTableModel.addColumn("사용 현황");
		
		updateUsageTable();
		table.setModel(usageTableModel);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);

		scrPane.setBounds(30, 70, 700, 400);
		this.add(scrPane);

		// 데이트 피커
		datePicker = new DatePicker(new Date());
		datePicker.setBounds(30, 500, 300, 25);
		this.add(datePicker);

		// 콤보박스
		comboBox = new JComboBox<String>();
		comboBox.setBounds(370, 500, 100, 25);
		this.add(comboBox);

		{
			String[] a = DBWrapper.getInstance().querySeminarRoomList()
					.toArray(new String[0]);
			comboBox.setModel(new DefaultComboBoxModel<String>(a));
		}

		// 상세 정보 조회
		JButton detail = new JButton("상세 정보 조회");
		detail.setBounds(500, 500, 120, 25);
		detail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDetailFrame();
			}
		});
		this.add(detail);

		// 사용자 예약
		JButton reserv = new JButton("사용자 예약");
		reserv.setBounds(650, 500, 100, 25);
		reserv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showReservFrame();

			}
		});
		this.add(reserv);
	}

	private void showDetailFrame() {

		JFrame frame = new JFrame();

		frame.setSize(DetailInfoPanel.width, DetailInfoPanel.height);
		frame.setTitle("상세 정보 조회");
		frame.setResizable(false);
		
		String slctd = comboBox.getSelectedItem().toString();
		Date theDate = datePicker.getDate();

		frame.add(new DetailInfoPanel(slctd, theDate));

		frame.setVisible(true);
	}

	private void showReservFrame() {

		JFrame frame = new JFrame();

		frame.setSize(ReservPanel.width, ReservPanel.height);
		frame.setTitle("사용자 예약");
		frame.setResizable(false);

		frame.add(new ReservPanel());

		frame.setVisible(true);
	}
	
	private void updateUsageTable(){
		
		usageTableModel.setRowCount(0);
		
		ArrayList<String> roomList = DBWrapper.getInstance().querySeminarRoomList();
		ArrayList<Boolean> usages = DBWrapper.getInstance().queryNowUsage(new Date());
		
		int l = roomList.size();

		for (int i = 0; i < l; i++) {

			String[] strarr = new String[2];
			strarr[0] = roomList.get(i);
			strarr[1] = usages.get(i) ? "Available" : "Using";

			usageTableModel.addRow(strarr);
		}

	}

}
