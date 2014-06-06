import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2269971701250845501L;

	public MainFrame() throws HeadlessException {
		
		this.setSize(UsagePanel.width, UsagePanel.height);
		
		this.setTitle("���� �� �����Ȳ");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(new UsagePanel());
		
	}

}
