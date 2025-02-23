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
 * ��Ϸ�¼�����
 * @author Chenyanqian
 *
 */
public class GameListen implements KeyListener{
	int flag = 0;
	Block[][] bs;//��ǰ�������
	String[][] bstemp;//��һ�η������
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
	int Always=0;//������־��
	int srollFlag = 0; //�ɷ���˱�־
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
		//��ʼ����¼���� 
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
		//��ʼ����¼���� 
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
		step.setText("���߲���:"+Step);
		score.setText("�÷�:"+Score);
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
	
		
		
	 
	//�Ƿ���Ի�ȡ��һ�μ�¼�ı�־��
	public int getSrollFlag() {
		return srollFlag;
	}

	//��ȡ��һ�μ�¼
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
		//������һ�μ�¼
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
							 score.setText("�÷�:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("�÷�:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024����������������ɿ��Լ�����ϷӴ~", "��Ϸͨ����ʾ",JOptionPane.WARNING_MESSAGE);  
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
							 score.setText("�÷�:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("�÷�:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024����������������ɿ��Լ�����ϷӴ~", "��Ϸͨ����ʾ",JOptionPane.WARNING_MESSAGE);  
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
							 score.setText("�÷�:"+SCORE);
						 }else if(value<1024 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("�÷�:"+SCORE);
						 }else if(value == 1024) {
							 JOptionPane.showMessageDialog(new JPanel(), "1024����������������ɿ��Լ�����ϷӴ~", "��Ϸͨ����ʾ",JOptionPane.WARNING_MESSAGE);  
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
							 score.setText("�÷�:"+SCORE);
						 }else if(value<=512 && value>128){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 12;
							 score.setText("�÷�:"+SCORE);
						 }else if(value<=700 && value>512){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 25;
							 score.setText("�÷�:"+SCORE);
						 }else if(value<1024 && value>700){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 45;
							 score.setText("�÷�:"+SCORE);
						 }else if(value == 1024) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 1024;
							 score.setText("�÷�:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "1024������������ӷ�1024�֣�", "��Ϸ��ʾ",JOptionPane.WARNING_MESSAGE);  
						 }else if(value<=1500 && value>1024) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 50;
							 score.setText("�÷�:"+SCORE);
						 }else if(value<=1750 && value>1500) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 70;
							 score.setText("�÷�:"+SCORE);
						 }else if(value<2048 && value>1750) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 100;
							 score.setText("�÷�:"+SCORE);
						 }else if(value==2048){
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 2048;
							 score.setText("�÷�:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "2048�����������������Ϸͨ�أ�������Ȼ���Լ�����Ϸ���ӷ�1024 * 2 = 2048�֣�", "��Ϸͨ����ʾ",JOptionPane.WARNING_MESSAGE); 	
						 }else if(value > 2048 && value<=4096) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 4096;
							 score.setText("�÷�:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "4096����������������������ӷ�2048 * 2 = 4096�֣�", "��Ϸ��ʾ",JOptionPane.WARNING_MESSAGE); 
						 }else if(value >4096) {
							 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 8192;
							 score.setText("�÷�:"+SCORE);
							 JOptionPane.showMessageDialog(new JPanel(), "����������򶼱������ˣ�֮����������ʾ����������Ϊ������ȥ��", "��Ϸ��ʾ",JOptionPane.WARNING_MESSAGE); 
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
			 step.setText("���߲���:"+Integer.valueOf(Step).toString());
			 fulljudge = 0;
			 flag = 0;
		}else {//δ�ܳɹ������ƶ�
			
			//���ȫ���������Ϸ�����ж�
			if(fulljudge == 0) {
			//�ж���Ϸ�Ƿ��޷��������ж�����
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
					//��Ϸ��Ȼ���Լ���
					gamejudge = 0;
					fulljudge = 0;
				}else {
					if(Score>MaxScore) {
						sr.writeMaxScore(Integer.valueOf(Score).toString());
					}
					JOptionPane.showMessageDialog(new JPanel(), "��Ϸ����һ�κϲ��󼴽������������Ѿ��޷��ٽ����ƶ��ϲ���Ŷ\n��������Ϸ����������Ϸ ( ��-�� )", "��Ϸʧ����ʾ",JOptionPane.WARNING_MESSAGE);  
				}
			}
			
		}
		 	DrewColor.drewColor(bs, Diff);
		 	
		 	if(Always>=3 && Always<=4) {
			 	always.setBackground(Color.BLACK);
			 	always.setBounds(1100, 380, 370, 50);
			 	always.setForeground(Color.white);
			 	always.setFont(new Font("����", Font.BOLD, 30));
			 	always.setText("�ӷ�:5!�������ﵽ��"+Always+"!");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 5;
				 score.setText("�÷�:"+SCORE);
			 	 
		 	}else if(Always>=5 && Always<=6) {
		 		always.setBackground(Color.green);
			 	always.setForeground(Color.black);
			 	always.setText("�ӷ�10����Ȼ����"+Always+"��!");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 10;
				 score.setText("�÷�:"+SCORE);
			  
		 	}else if(Always>=7) {
		 		always.setBackground(Color.WHITE);
		 		always.setFont(new Font("����", Font.BOLD, 30));
			 	always.setForeground(Color.RED);
			 	always.setText("�ӷ�15������������"+Always+"���죡");
			 	 int SCORE = Integer.valueOf((score.getText().split(":"))[1]).intValue() + 15;
				 score.setText("�÷�:"+SCORE);
			  
		 	}else {
		 		always.setBounds(1200, 380, 200, 50);
		 		always.setBackground(Color.YELLOW);
		 		always.setForeground(Color.BLACK);
		 		always.setFont(new Font("����", Font.BOLD, 20));
		 		always.setText("����:"+Always);
		 	}
		 		Score= Integer.valueOf(score.getText().split(":")[1]);
		 		if(Integer.valueOf(score.getText().split(":")[1]) > MaxScore) {
		 		maxscore.setText("��߷�:"+(Integer.valueOf(score.getText().split(":")[1])));
		 	} 	
		  
		 		
		 	GameListen.this.Score = (Integer.valueOf(score.getText().split(":")[1]));
		    
	}
	
	
	public void keyReleased(KeyEvent e) {
		 
		
	}
	 
	public void keyTyped(KeyEvent e) {
	 
		
	}	
	
}