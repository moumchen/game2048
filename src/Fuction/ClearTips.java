package Fuction;

import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * ���Tip������Ϣ
 * @author Chenyanqian
 *
 */
public class ClearTips extends TimerTask {
	JLabel tip = null;
	 
	public void setTip(JLabel tip) { 
		this.tip = tip;
	}
	
	public void run() {
		tip.setText(" ");
	}
}
