package GUI;

 
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ������Ϸ ����
 * @author Chenyanqian
 *
 */
public class AboutGameFrame extends JFrame {
	
	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;
	int xOld = 0;
	int yOld = 0;
	
	public AboutGameFrame() {
		
		//ͼ��
		ImageIcon BackgroundIcon = new ImageIcon("images//BackgroundIMG//AboutGame.png");
		ImageIcon Submit = new ImageIcon("images//Button//Submit.png");
		ImageIcon Submit1 = new ImageIcon("images//Button//Submit-1.png");	
		ImageIcon Submit2 = new ImageIcon("images//Button//Submit-2.png");
		this.setSize(632, 709);
		Container contain = this.getContentPane();
		//�϶�����
		this.addMouseListener(new MouseAdapter() {
		 
			public void mousePressed(MouseEvent e) {
				 xOld = e.getX();
				 yOld = e.getY();
			}	
			
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
		
			public void mouseDragged(MouseEvent e) {
				 int xNow = e.getXOnScreen() - xOld;
				 int yNow = e.getYOnScreen() - yOld;
				 AboutGameFrame.this.setLocation(xNow,yNow);
				 
			}
		});
		//��������
		JLabel backgroundLabel = new JLabel(BackgroundIcon);
		this.getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		backgroundLabel.setBounds(0, 0, BackgroundIcon.getIconWidth(), BackgroundIcon.getIconHeight());
		((JPanel)contain).setOpaque(false);
	
		//�˳���ť
		JButton SubmitBT = new JButton("");
		SubmitBT.setSize(220, 46);
		SubmitBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SubmitBT.setIcon(Submit);
		SubmitBT.setRolloverIcon(Submit1);
		SubmitBT.setPressedIcon(Submit2);
		SubmitBT.setBounds(this.getWidth()/2-110, this.getHeight()-60, 220, 46);
		SubmitBT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				 AboutGameFrame.this.dispose();
			}
			
			
		});
		 
		this.setLocationRelativeTo(null); 
		//������
		this.setLayout(null);
		contain.add(SubmitBT);
	 
		//�����ޱ�����
		this.dispose();
		this.setUndecorated(true);
		//�ɼ�
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new AboutGameFrame();
	}
}
