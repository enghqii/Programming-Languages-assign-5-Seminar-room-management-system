import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UsagePanel extends JPanel {

	private static final long serialVersionUID = -2195835028220798096L;

	public UsagePanel() {
		initUI();
	}

	private void initUI() {
		setLayout(null);

		JLabel titleLabel = new JLabel("실시간 사용 현황");
		titleLabel.setBounds(450, 20, 100, 30);
		this.add(titleLabel);

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

		Object rowData[][] = {
				{ "Row1-Column1", "Row1-Column2", "Row1-Column3" },
				{ "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		Object columnNames[] = { "Column One", "Column Two" };
		JTable table = new JTable(rowData, columnNames);

		table.setFillsViewportHeight(true);
		JScrollPane scrPane = new JScrollPane(table);
		
		scrPane.setBounds(30, 70, 600, 400);
		this.add(scrPane);

	}

}
