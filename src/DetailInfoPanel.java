import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class DetailInfoPanel extends JPanel {
	
	public static final int width = 600;
	public static final int height= 400;
	
	private static final long serialVersionUID = 6584784002346447677L;

	public DetailInfoPanel(String roomName, Date date) {
		
		initUI(roomName, date);
	}

	private void initUI(String roomName, Date date){
		setLayout(null);
		
		JLabel nameLabel = new JLabel(roomName);
		nameLabel.setBounds(30, 10, 100, 30);
		this.add(nameLabel);

		DateFormat sdFormat = new SimpleDateFormat("yyyy³â MM¿ù ddÀÏ");
		String tempDate = sdFormat.format(date);
		
		JLabel theDay = new JLabel(tempDate);
		theDay.setBounds(600 - 130, 10, 100, 30);
		this.add(theDay);
		
		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2"},
				{ "Row2-Column1", "Row2-Column2"} };
		Object columnNames[] = { "Column One", "Column Two" };
		JTable table = new JTable(rowData, columnNames);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);
		
		scrPane.setBounds(30, 50, 540, 300);
		this.add(scrPane);
	}
}
