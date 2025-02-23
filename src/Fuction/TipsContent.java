package Fuction;

import java.util.TimerTask;

import javax.swing.JLabel;
 
/**
 * Tip信息输出内容管理
 * @author Chenyanqian
 *
 */
public class TipsContent extends TimerTask{
	
 
	int round = 0;
	JLabel tip = null;

	public void setTip(JLabel tip) { 
		this.tip = tip;
	}
	
	public void changeTips(JLabel tip) {
		round++;
		if(round > 6) {
			round = 1;
		}
		switch(round) {
		case 1:tip.setText("你知道吗？：2048游戏最早发行于2014年3月20日，原版2048在GitHub上发布，作者是Gabriele Cirulli。");tip.setBounds(390,90,750,100);break;
		case 2:tip.setText("温馨小提示: 游戏过程中可能会出现失去焦点的情况，点击游戏面板即可重获焦点哟~ (#`O′)");tip.setBounds(450, 90, 700, 100);break;
		case 3:tip.setText("游戏技巧1：最大数尽可能放在角落，数字按顺序紧邻排列。首先满足最大数和次大数在的那一列/行是满的。"); tip.setBounds(400,90,750,100);break;
		case 4:tip.setText("游戏技巧2：时刻注意活动较大数（32以上）旁边要有相近的数,以大数所在的一行为主要移动方向!"); tip.setBounds(420,90,750,100);break;
		case 5:tip.setText("温馨小提示：游戏过程中使用按钮会让游戏面板失去焦点，点击游戏面板重获焦点畅快游戏吧！(#`O′");tip.setBounds(400,90,750,100);break;
		case 6:tip.setText("你知道吗?:右下角可以查看全球排名，还可以上传您的分数，快快点击与好友PK吧！（需要联网使用）");tip.setBounds(400,90,750,100);break;
		case 7:tip.setText("本游戏制作人员名单：王成 王翔 曹洋铭 陈颜乾 杨坤");tip.setBounds(400,90,750,100);break;
		}
		
	}
	 
	public void run() {
		changeTips(tip);
		
	}
	
	
}

 