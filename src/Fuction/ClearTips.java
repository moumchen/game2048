package Fuction;

import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * 清楚Tip栏的消息
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
