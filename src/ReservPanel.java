import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.michaelbaranov.microba.calendar.DatePicker;

public class ReservPanel extends JPanel {

	public static final int width = 600;
	public static final int height = 250;

	private static final long serialVersionUID = 4475762149966994904L;

	private DatePicker datePicker;
	private JComboBox<String> resvTimes;
	private JComboBox<String> seminarRooms;
	private JTextField nameField;
	private JTextField phoneField;

	public ReservPanel() {
		initUI();
	}

	private void initUI() {
		setLayout(null);

		/* LABELS */{
			JLabel dateLabel = new JLabel("��¥ ����: ");
			dateLabel.setBounds(30, 30, 70, 30);
			this.add(dateLabel);

			JLabel timeLabel = new JLabel("�뿩�ð� : ");
			timeLabel.setBounds(30, 60, 70, 30);
			this.add(timeLabel);

			JLabel roomLabel = new JLabel("���̳��� : ");
			roomLabel.setBounds(30, 90, 70, 30);
			this.add(roomLabel);

			JLabel nameLabel = new JLabel("�̸� ����: ");
			nameLabel.setBounds(30, 120, 70, 30);
			this.add(nameLabel);

			JLabel phoneLabel = new JLabel("��ȭ��ȣ : ");
			phoneLabel.setBounds(30, 150, 70, 30);
			this.add(phoneLabel);
		}

		/* FIELDS */{
			datePicker = new DatePicker(new Date());
			datePicker.setBounds(120, 30, 150, 25);
			this.add(datePicker);

			resvTimes = new JComboBox<String>();
			resvTimes.setBounds(120, 60, 150, 25);
			this.add(resvTimes);
			{
				Vector<String> times = new Vector<String>();
				for (int i = 10; i <= 18; i++) {
					times.add(i + ":00");
				}
				resvTimes.setModel(new DefaultComboBoxModel<String>(times));
			}

			seminarRooms = new JComboBox<String>();
			seminarRooms.setBounds(120, 90, 150, 25);
			this.add(seminarRooms);
			{
				String[] a = DBWrapper.getInstance().querySeminarRoomList()
						.toArray(new String[0]);
				seminarRooms.setModel(new DefaultComboBoxModel<String>(a));
			}

			nameField = new JTextField();
			nameField.setBounds(120, 120, 150, 25);
			this.add(nameField);

			phoneField = new JTextField();
			phoneField.setBounds(120, 150, 150, 25);
			this.add(phoneField);
		}

		JButton startInquiry = new JButton("��ȸ����");
		startInquiry.setBounds(330, 30, 100, 25);
		this.add(startInquiry);

		JButton stopInquiry = new JButton("��ȸ����");
		stopInquiry.setBounds(460, 30, 100, 25);
		this.add(stopInquiry);

		// TABLE
		Object rowData[][] = { { "Row1-Column1", }, { "Row2-Column1", } };
		Object columnNames[] = { "Column One" };
		JTable table = new JTable(rowData, columnNames);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);

		scrPane.setBounds(330, 60, 230, 120);
		this.add(scrPane);

		// Buttons
		JButton resv = new JButton("����");
		resv.setBounds(30, 200 - 10, 600 - 60, 30);
		resv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				makeReservation();
			}
		});
		this.add(resv);

		/*
		 * JButton cancel = new JButton("���"); cancel.setBounds(330, 200, 70,
		 * 30); this.add(cancel);
		 */
	}

	private void makeReservation() {
		
		Date theDay = datePicker.getDate();
		String rentalTime = resvTimes.getSelectedItem().toString();
		String seminarRoomName = seminarRooms.getSelectedItem().toString();
		String name = nameField.getText();
		String phoneNumberStr = phoneField.getText();

		try {
			DBWrapper.getInstance().insertReservation(theDay, rentalTime,
					seminarRoomName, name, phoneNumberStr);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
