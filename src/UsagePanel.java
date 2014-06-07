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

		// Ÿ��Ʋ ��
		JLabel titleLabel = new JLabel("�ǽð� ��� ��Ȳ");
		titleLabel.setBounds(400 - 50, 20, 100, 30);
		this.add(titleLabel);

		// ������ ��
		DateFormat sdFormat = new SimpleDateFormat("yyyy�� MM�� dd��");
		Date nowDate = new Date();
		String tempDate = sdFormat.format(nowDate);

		JLabel todayLabel = new JLabel(tempDate);
		todayLabel.setBounds(400 + 300 - 50, 20, 100, 30);
		this.add(todayLabel);

		// �����Ȳ ���̺�
		JTable table = new JTable();
		
		usageTableModel = new DefaultTableModel();
		usageTableModel.addColumn("���̳� ��");
		usageTableModel.addColumn("��� ��Ȳ");
		
		updateUsageTable();
		table.setModel(usageTableModel);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);

		scrPane.setBounds(30, 70, 700, 400);
		this.add(scrPane);

		// ����Ʈ ��Ŀ
		datePicker = new DatePicker(new Date());
		datePicker.setBounds(30, 500, 300, 25);
		this.add(datePicker);

		// �޺��ڽ�
		comboBox = new JComboBox<String>();
		comboBox.setBounds(370, 500, 100, 25);
		this.add(comboBox);

		{
			String[] a = DBWrapper.getInstance().querySeminarRoomList()
					.toArray(new String[0]);
			comboBox.setModel(new DefaultComboBoxModel<String>(a));
		}

		// �� ���� ��ȸ
		JButton detail = new JButton("�� ���� ��ȸ");
		detail.setBounds(500, 500, 120, 25);
		detail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showDetailFrame();
			}
		});
		this.add(detail);

		// ����� ����
		JButton reserv = new JButton("����� ����");
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
		frame.setTitle("�� ���� ��ȸ");
		frame.setResizable(false);
		
		String slctd = comboBox.getSelectedItem().toString();
		Date theDate = datePicker.getDate();

		frame.add(new DetailInfoPanel(slctd, theDate));

		frame.setVisible(true);
	}

	private void showReservFrame() {

		JFrame frame = new JFrame();

		frame.setSize(ReservPanel.width, ReservPanel.height);
		frame.setTitle("����� ����");
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
