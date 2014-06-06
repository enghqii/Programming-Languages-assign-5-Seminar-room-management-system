import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 2269971701250845501L;

	public MainFrame() throws HeadlessException {

		this.setSize(UsagePanel.width, UsagePanel.height);

		this.setTitle("예약 및 사용현황");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DBWrapper.getInstance().closeDB();
				System.exit(1);
			}
		};
		this.addWindowListener(exitListener);

		this.add(new UsagePanel());

	}

}