package Beans;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Bean:�����ã��������ǰʮλ��Ϣ��JLabel�������ԡ� 
 * @author Chenyanqian
 */
public class Info extends JLabel {
 	
	public Info(int x, int y) {
		this.setOpaque(false);
		this.setBounds(x, y, 520, 15);
		this.setFont(new Font("����", Font.BOLD, 20));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
	}
	
}
