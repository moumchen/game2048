package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 错误窗口
 * @author Chenyanqian
 */
public class ErrorFrame extends JFrame {
	
	 
	private static final long serialVersionUID = 1L;
	public ErrorFrame() {
		this.setTitle("程序发生错误");
		this.setSize(500, 100);
		JLabel bel = new JLabel("");
		bel.setText("不好意思，程序发生意外错误，我们将会收集错误信息解决，请关闭本窗口结束程序");
		bel.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.getContentPane().add(bel);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
	}
	 
}
