import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2269971701250845501L;

	public MainFrame() throws HeadlessException {
		
		this.setSize(1000, 600);
		this.setTitle("예약 및 사용현황");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new UsagePanel());
		
	}

}
