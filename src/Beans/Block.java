package Beans;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Bean:��������ã����������Ϸ�������Ĺ������ԡ� 
 * @author Chenyanqian
 */
public class Block extends JLabel{

	private static final long serialVersionUID = 1L;

	/**
	 * ��ʼ������
	 * @param x
	 * @param y
	 * @param Diff
	 */
	public void InitBlock(int x, int y, int Diff) {
		if(Diff==4)
		this.setBounds(x, y, 130, 127);
		if(Diff==5)
		this.setBounds(x, y, 102, 101);
		this.setFont(new Font("Arial Black", Font.BOLD, 30));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.YELLOW);
		this.setOpaque(true);
	}
}