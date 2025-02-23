package Beans;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Bean:排名用，用于输出前十位信息的JLabel共有属性。 
 * @author Chenyanqian
 */
public class Info extends JLabel {
 	
	public Info(int x, int y) {
		this.setOpaque(false);
		this.setBounds(x, y, 520, 15);
		this.setFont(new Font("宋体", Font.BOLD, 20));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
	}
	
}
