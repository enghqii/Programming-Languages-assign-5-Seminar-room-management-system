import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DetailInfoPanel extends JPanel {
	
	public static final int width = 600;
	public static final int height= 400;
	
	private static final long serialVersionUID = 6584784002346447677L;
	
	private DefaultTableModel detailTableModel;

	public DetailInfoPanel(String roomName, Date date) {
		
		initUI(roomName, date);
	}

	private void initUI(String roomName, Date date){
		setLayout(null);
		
		JLabel nameLabel = new JLabel(roomName);
		nameLabel.setBounds(30, 10, 100, 30);
		this.add(nameLabel);

		DateFormat sdFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		String tempDate = sdFormat.format(date);
		
		JLabel theDay = new JLabel(tempDate);
		theDay.setBounds(600 - 130, 10, 100, 30);
		this.add(theDay);
		
		detailTableModel = new DefaultTableModel();

		detailTableModel.addColumn("시간");
		detailTableModel.addColumn("사용현황");
		detailTableModel.addColumn("이름");
		detailTableModel.addColumn("연락처");
		JTable table = new JTable(detailTableModel);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);
		
		scrPane.setBounds(30, 50, 540, 300);
		this.add(scrPane);
	}
	
	private void updateDetail(){
		detailTableModel.setRowCount(0);
	}
}
