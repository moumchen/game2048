package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * ���󴰿�
 * @author Chenyanqian
 */
public class ErrorFrame extends JFrame {
	
	 
	private static final long serialVersionUID = 1L;
	public ErrorFrame() {
		this.setTitle("����������");
		this.setSize(500, 100);
		JLabel bel = new JLabel("");
		bel.setText("������˼������������������ǽ����ռ�������Ϣ�������رձ����ڽ�������");
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
