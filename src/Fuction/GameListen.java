package Fuction;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beans.Block;
 
/**
 * 游戏事件监听
 * @author Chenyanqian
 *
 */
public class GameListen implements KeyListener{
	int flag = 0;
	Block[][] bs;//当前方块矩阵
	String[][] bstemp;//上一次方块矩阵
	int Diff;
	createRandom c;
	int Score = 0;
	int lastScore = 0;
	int Step = 0;
	int lastStep = 0;
	int MaxScore;
	JButton srollBT;
	JLabel score;
	JLabel maxscore;
	JLabel step;
	JLabel always;
	SaveAndRead sr;
	int AlwaysFlag = 0;
	int Always=0;//连击标志数
	int srollFlag = 0; //可否回退标志
	ImageIcon sroll = new ImageIcon("Images//Button//srollback.png");
	ImageIcon unsrollback = new ImageIcon("Images//Button//unsrollback.png");
	
	public GameListen(Block[][] bs, int Diff, createRandom c, int Score, int MaxScore, int Step, JLabel score, JLabel maxscore, JLabel step, SaveAndRead sr, JLabel always, JButton srollBT) {
		this.bs = bs;
		this.Diff = Diff;
		this.c = c;
		this.Score = Score;
		this.Step = Step;
		this.MaxScore = MaxScore;
		this.score = score;
		this.maxscore = maxscore;
		this.step = step;
		this.sr = sr;
		this.always = always;
		this.srollBT = srollBT;
		//初始化记录数据 
		bstemp = new String[Diff][Diff];
		for(int i=0;i<Diff;i++) {
			for(int j=0; j<Diff; j++) {
				bstemp[i][j] = bs[i][j].getText();	 
			}
		}
	}
	
	public void reset(Block[][] bs, int Diff, createRandom c, int Score, int MaxScore, int Step, JLabel score, JLabel maxscore, JLabel step) {
		this.bs = bs;
		this.Diff = Diff;
		this.c = c;
		c.reset(bs, Diff);
		this.Score = Score;
		this.Step = Step;
		this.MaxScore = MaxScore;
		this.score = score;
		this.maxscore = maxscore;
		this.step = step;
		srollFlag = 0;
		
		DrewColor.drewColor(bs, Diff);
		//初始化记录数据 
		bstemp = new String[Diff][Diff];
		for(int i=0;i<Diff;i++) {
			for(int j=0; j<Diff; j++) {
				bstemp[i][j] = bs[i][j].getText();	 
			}
		}
		
	}
	
	
	public void srollback() {
		
		Score = lastScore;
		Step = lastStep;
		step.setText("已走步数:"+Step);
		score.setText("得分:"+Score);
		for(int i=0; i<Diff; i++) {
			for(int j=0; j<Diff; j++) {
				bs[i][j].setText(bstemp[i][j]);
			}
		}
		srollFlag = 0;
		srollBT.setIcon(unsrollback);
		Always=0;
		DrewColor.drewColor(bs, Diff);
	}
	
		
		
	 
	//是否可以获取上一次记录的标志；
	public int getSrollFlag() {
		return srollFlag;
	}

	//获取上一次记录
	public String[][] getBstemp() {
		return bstemp;
	}

	public void setBstemp(String[][] bstemp) {
		this.bstemp = bstemp;
	}

	public int getScore() {
		return GameListen.this.Score;
	}
	
	public int getStep() {
		return Step;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Integer temp;
		int value;
		int gamejudge=0;
		int fulljudge=0;
		//备份上一次记录
	    for(int i=0; i<Diff; i++) {
	    	for(int j=0;j<Diff; j++) {
	    		bstemp[i][j] = bs[i][j].getText();
	    	}
	    }
	    lastScore = Score;
	    lastStep = Step;
	 	srollFlag++;
	 	if(srollFlag==1) {
	 		srollBT.setIcon(sroll);
	 	}
	 	
	 	
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			 for(int i=0; i<Diff; i++) {
				 for(int j=0; j<Diff-1; j++) {
					 if(bs[j][i].getText()=="") {
						 for(int k=j+1; k<=Diff-1; k++) {
							 if(bs[k][i].getText()!="") {
								 bs[j][i].setText(bs[k][i].getText());
								 bs[k][i].setText("");
								 flag=1;
								 break;
							 }
						 }
					 }
				 }
				 for(int j=1; j<Diff; j++) {
					 if(bs[j][i].getText()!="" && bs[j-1][i].getText()!="" && bs[j][i].getText().equals(bs[j-1][i].getText())) {
						 bs[j][i].setText("");
						 temp = Integer.valueOf(bs[j-1][i].getText());
						 value = temp.intValue() * 2;
						 temp = Integer.valueOf(value);
						 if(value<=128) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
							 score.setText("得分:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("得分:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024方块出现啦！您依旧可以继续游戏哟~", "游戏通关提示",JOptionPane.WARNING_MESSAGE);  
						 }
						 bs[j-1][i].setText(temp.toString());
						 flag = 1;
						 AlwaysFlag =1;
						 break;
					 }
				 }
				 for(int j=0; j<Diff-1; j++) {
					 if(bs[j][i].getText()=="") {
						 for(int k=j+1; k<=Diff-1; k++) {
							 if(bs[k][i].getText()!="") {
								 bs[j][i].setText(bs[k][i].getText());
								 bs[k][i].setText("");
								 flag=1;
								 break;
							 }
						 }
					 }
				 }
				 for(int j=0; j<Diff;j++) {
					 if(bs[i][j].getText()=="") {
						 fulljudge =1; 
						 break;
					 }
				 }
			 }
		
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			for(int i=0; i<Diff; i++) {
				for(int j=Diff-1; j>0; j--) {
					if(bs[j][i].getText()=="") {
						for(int k=j-1; k>=0; k--) {
							if(bs[k][i].getText()!="") {
								bs[j][i].setText(bs[k][i].getText());
								bs[k][i].setText("");
								flag=1;
								break;
							}
						}
					}
				}
				for(int j=Diff-2; j>=0;j--) {
					if(bs[j][i].getText()!="" && bs[j+1][i].getText()!="" && bs[j][i].getText().equals(bs[j+1][i].getText())) {
						bs[j][i].setText("");
						temp = Integer.valueOf(bs[j+1][i].getText());
						value = temp.intValue()*2;
						temp = Integer.valueOf(value);
						 if(value<=128) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
							 score.setText("得分:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("得分:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024方块出现啦！您依旧可以继续游戏哟~", "游戏通关提示",JOptionPane.WARNING_MESSAGE);  
						 }
						bs[j+1][i].setText(temp.toString());
						flag = 1;
						AlwaysFlag =1;
						break;
					}
				}
				for(int j=Diff-1; j>0; j--) {
					if(bs[j][i].getText()=="") {
						for(int k=j-1; k>=0; k--) {
							if(bs[k][i].getText()!="") {
								bs[j][i].setText(bs[k][i].getText());
								bs[k][i].setText("");
								flag=1;
								break;
							}
						}
					}
				}
				 for(int j=0; j<Diff;j++) {
					 if(bs[i][j].getText()=="") {
						 fulljudge =1; 
						 break;
					 }
				 }
				
			}
			 
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			 for(int i=0; i<Diff; i++) {
				for(int j=0; j<Diff-1; j++) {
					if(bs[i][j].getText()=="") {
						for(int k=j+1; k<=Diff-1; k++) {
							if(bs[i][k].getText()!="") {
								bs[i][j].setText(bs[i][k].getText());
								bs[i][k].setText("");
								flag =1;
								break;
							}
						}
					}
				}
				 
				 for(int j=1;j<Diff;j++) {
					 if(bs[i][j].getText()!="" && bs[i][j-1].getText()!="" && bs[i][j].getText().equals(bs[i][j-1].getText())) {
						 bs[i][j].setText("");
						 temp = Integer.valueOf(bs[i][j-1].getText());
						 value = temp.intValue()*2;
						 temp = Integer.valueOf(value);
						 if(value<=128) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
							 score.setText("得分:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("得分:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024方块出现啦！您依旧可以继续游戏哟~", "游戏通关提示",JOptionPane.WARNING_MESSAGE);  
						 }
						 bs[i][j-1].setText(temp.toString());
						 flag = 1;
						 AlwaysFlag =1;
						 break;
					 }
				 }
				 
				 for(int j=0; j<Diff-1; j++) {
						if(bs[i][j].getText()=="") {
							for(int k=j+1; k<=Diff-1; k++) {
								if(bs[i][k].getText()!="") {
									bs[i][j].setText(bs[i][k].getText());
									bs[i][k].setText("");
									flag =1;
									break;
								}
							}
						}
					}
				 for(int j=0; j<Diff;j++) {
					 if(bs[i][j].getText()=="") {
						 fulljudge =1; 
						 break;
					 }
				 }
			 }
			 
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			for(int i=0; i<Diff; i++) {
				for(int j=Diff-1; j>=0; j--) {
					if(bs[i][j].getText()=="") {
						for(int k=j-1; k>=0; k--) {
							if(bs[i][k].getText()!="") {
								bs[i][j].setText(bs[i][k].getText());
								bs[i][k].setText("");
								flag =1;
								break;
							}
						}
					}
				}
				for(int j=Diff-2;j>=0;j--) {
					if(bs[i][j].getText()!="" && bs[i][j+1].getText()!="" && bs[i][j].getText().equals(bs[i][j+1].getText())) {
						bs[i][j].setText("");
						 temp = Integer.valueOf(bs[i][j+1].getText());
						 value = temp.intValue()*2;
						 temp = Integer.valueOf(value);
						 if(value<=128) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
							 score.setText("得分:"+SCORE);
						 }else if(value<=512 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("得分:"+SCORE);
						 }else if(value<=700 && value>512){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 25;
							 score.setText("得分:"+SCORE);
						 }else if(value<1024 && value>700){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 45;
							 score.setText("得分:"+SCORE);
						 }else if(value == 1024) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 1024;
							 score.setText("得分:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "1024方块出现啦！加分1024分！", "游戏提示",JOptionPane.WARNING_MESSAGE);  
						 }else if(value<=1500 && value>1024) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 50;
							 score.setText("得分:"+SCORE);
						 }else if(value<=1750 && value>1500) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 70;
							 score.setText("得分:"+SCORE);
						 }else if(value<2048 && value>1750) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 100;
							 score.setText("得分:"+SCORE);
						 }else if(value==2048){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 2048;
							 score.setText("得分:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "2048方块出现啦！理论游戏通关，但您仍然可以继续游戏，加分1024 * 2 = 2048分！", "游戏通关提示",JOptionPane.WARNING_MESSAGE); 	
						 }else if(value > 2048 && value<=4096) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 4096;
							 score.setText("得分:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "4096方块出现啦！您阵厉害，加分2048 * 2 = 4096分！", "游戏提示",JOptionPane.WARNING_MESSAGE); 
						 }else if(value >4096) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 8192;
							 score.setText("得分:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "你的厉害程序都被吓着了，之后不再推送提示，分数继续为您加上去！", "游戏提示",JOptionPane.WARNING_MESSAGE); 
						 }
						 bs[i][j+1].setText(temp.toString());
						 flag = 1;
						 AlwaysFlag =1;
						 break;
					}
					
				}
				for(int j=Diff-1; j>=0; j--) {
					if(bs[i][j].getText()=="") {
						for(int k=j-1; k>=0; k--) {
							if(bs[i][k].getText()!="") {
								bs[i][j].setText(bs[i][k].getText());
								bs[i][k].setText("");
								flag =1;
								break;
							}
						}
					}
				}
				 for(int j=0; j<Diff;j++) {
					 if(bs[i][j].getText()=="") {
						 fulljudge =1; 
						 break;
					 }
				 }
				
			}
		}
		 if(AlwaysFlag == 1) {
			 Always++;
			 AlwaysFlag=0;
		 }else {
			 Always=0;
		 }
		 if(flag == 1){
			 c.creat();
			 Step++;
			 step.setText("已走步数:"+Integer.valueOf(Step).toString());
			 fulljudge = 0;
			 flag = 0;
		}else {//未能成功进行移动
			
			//如果全满则进行游戏结束判断
			if(fulljudge == 0) {
			//判断游戏是否无法继续进行而结束
				for(int i=0; i<Diff-1; i++) {
					for(int j=0; j<Diff-1; j++) {
						if(i==0) {
							if(j==0) {
								if(bs[i][j].getText().equals(bs[i][j+1].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText())) {
										gamejudge += 1;
										break;
								}
							}else if(j==Diff-1) {
								if(bs[i][j].getText().equals(bs[i][j-1].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText())) {
									    gamejudge += 1;
									    break;
								}
							}else {
								if(bs[i][j].getText().equals(bs[i][j-1].getText()) || bs[i][j].getText().equals(bs[i][j+1].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText())) {
										gamejudge += 1;
										break;
								}
							}
						}
						if(i==Diff-1) {
							if(j==0) {
								if(bs[i][j].getText().equals(bs[i][j+1].getText()) || bs[i][j].getText().equals(bs[i-1][j].getText())) {
										gamejudge += 1;
										break;
								}
							}else if(j==Diff-1) {
								if(bs[i][j].getText().equals(bs[i][j-1].getText()) || bs[i][j].getText().equals(bs[i-1][j].getText())) {
									    gamejudge += 1;
									    break;
								}
							}else {
								if(bs[i][j].getText().equals(bs[i][j-1].getText()) || bs[i][j].getText().equals(bs[i][j+1].getText()) || bs[i][j].getText().equals(bs[i-1][j].getText())) {
										gamejudge += 1;
										break;
								}
							}
						}
						if(j==0) {
							if(i!=0 && i!=Diff-1) {
								if(bs[i][j].getText().equals(bs[i-1][j].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText()) || bs[i][j].getText().equals(bs[i][j+1].getText())) {
									gamejudge += 1;
									break;
								}
							}
						}
						if(j==Diff-1) {
							if(i==0) {
								if(bs[i][j].getText().equals(bs[i][j-1].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText())) {
									gamejudge +=1;
									break;
								}
							}else if(i!=0 && i!=Diff-1) {
								if(bs[i][j].getText().equals(bs[i-1][j].getText()) || bs[i][j].getText().equals(bs[i][j-1].getText()) ) {
									gamejudge +=1;
									break;
								}
							}else {
								if(bs[i][j].getText().equals(bs[i-1][j].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText()) || bs[i][j].getText().equals(bs[i][j-1].getText())) {
									gamejudge += 1;
									break;
								}
								
							}
							
					}
						if(i!=0 && i!=Diff-1 && j!=0 && j!=Diff-1) {
							if(bs[i][j].getText().equals(bs[i-1][j].getText()) || bs[i][j].getText().equals(bs[i+1][j].getText()) || bs[i][j].getText().equals(bs[i][j-1].getText()) ||
									bs[i][j].getText().equals(bs[i][j+1].getText())) {
								gamejudge += 1;
								break;
							}
						}
						
						
					}
					if(gamejudge != 0) {
						break;
					}
					
				}
				if(gamejudge > 0) {
					//游戏仍然可以继续
					gamejudge = 0;
					fulljudge = 0;
				}else {
					if(Score>MaxScore) {
						sr.writeMaxScore(Integer.valueOf(Score).toString());
					}
					JOptionPane.showMessageDialog(new JPanel(), "游戏在下一次合并后即将结束，或者已经无法再进行移动合并了哦\n若继续游戏。请重置游戏 ( ▼-▼ )", "游戏失败提示",JOptionPane.WARNING_MESSAGE);  
				}
			}
			
		}
		 	DrewColor.drewColor(bs, Diff);
		 	
		 	if(Always>=3 && Always<=4) {
			 	always.setBackground(Color.BLACK);
			 	always.setBounds(1100, 380, 370, 50);
			 	always.setForeground(Color.white);
			 	always.setFont(new Font("楷体", Font.BOLD, 30));
			 	always.setText("加分:5!连击数达到了"+Always+"!");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
				 score.setText("得分:"+SCORE);
			 	 
		 	}else if(Always>=5 && Always<=6) {
		 		always.setBackground(Color.green);
			 	always.setForeground(Color.black);
			 	always.setText("加分10！竟然连击"+Always+"次!");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 10;
				 score.setText("得分:"+SCORE);
			  
		 	}else if(Always>=7) {
		 		always.setBackground(Color.WHITE);
		 		always.setFont(new Font("楷体", Font.BOLD, 30));
			 	always.setForeground(Color.RED);
			 	always.setText("加分15！继续！连击"+Always+"！天！");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 15;
				 score.setText("得分:"+SCORE);
			  
		 	}else {
		 		always.setBounds(1200, 380, 200, 50);
		 		always.setBackground(Color.YELLOW);
		 		always.setForeground(Color.BLACK);
		 		always.setFont(new Font("黑体", Font.BOLD, 20));
		 		always.setText("连击:"+Always);
		 	}
		 		Score= Integer.valueOf(score.getText().split(":")[1]);
		 		if(Integer.valueOf(score.getText().split(":")[1]) > MaxScore) {
		 		maxscore.setText("最高分:"+(Integer.valueOf(score.getText().split(":")[1])));
		 	} 	
		  
		 		
		 	GameListen.this.Score = (Integer.valueOf(score.getText().split(":")[1]));
		    
	}
	
	
	public void keyReleased(KeyEvent e) {
		 
		
	}
	 
	public void keyTyped(KeyEvent e) {
	 
		
	}	
	
}