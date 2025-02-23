package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Beans.Block;
import Beans.Difficult;
import Beans.Info;
import Fuction.ClearTips;
import Fuction.DrewColor;
import Fuction.GameListen;
import Fuction.SaveAndRead;
import Fuction.TipsContent;
import Fuction.createRandom;

/**
 * ����Ϸ����
 * @author Chenyanqian
 *
 */
public class GameFrame extends JFrame {
	 
	private static final long serialVersionUID = 1L;
	int xOld = 0;
	int yOld = 0;
	int Score = 0;
	int MaxScore = 0;
	int Step =0;
	Block[][] bs;
	int Diff = 4;
	int Xtemp=5;//�����ʼ��ʱʹ�õļ������
    int Ytemp=5;
    createRandom c;
    GameListen listen;
    SaveAndRead sr =null;
    JFrame choose;
    JFrame topframe = null;
    Info[] info= new Info[15] ;
    JTextArea more = null;
    Calendar now = null;
    TipsContent tipscontent = null;
    ClearTips cleartips = null;
    
    
	public GameFrame(MainFrame main, Difficult difficult) {	
		
		//���ݳ�ʼ��
		difficult.setFlag(1);
		this.Diff = difficult.getDiff();
		sr =  new SaveAndRead();
		MaxScore = sr.readMaxScore();
		tipscontent = new TipsContent();
		cleartips = new ClearTips();
		Timer timer = new Timer();//��ʱִ��tips���ݸ��ĵĳ���
		
		//ͼ��
		ImageIcon background = new ImageIcon("Images//BackgroundIMG//GameFrameBG.png");
		ImageIcon topbackground = new ImageIcon("Images//BackgroundIMG//TopBack.png");
		ImageIcon back = new ImageIcon("Images//Button//back.png");
		ImageIcon back1 = new ImageIcon("Images//Button//back-1.png");
		ImageIcon back2 = new ImageIcon("Images//Button//back-2.png");
		ImageIcon restart = new ImageIcon("Images//Button//restart.png");
		ImageIcon restart1 = new ImageIcon("Images//Button//restart-1.png");
		ImageIcon restart2 = new ImageIcon("Images//Button//restart-2.png");
		ImageIcon exit = new ImageIcon("Images//Button//exit2.png");
		ImageIcon exit1 = new ImageIcon("Images//Button//exit2-1.png");
		ImageIcon exit2 = new ImageIcon("Images//Button//exit2-2.png");
		ImageIcon save = new ImageIcon("Images//Button//Save.png");
		ImageIcon save1 = new ImageIcon("Images//Button//Save-1.png");
		ImageIcon save2 = new ImageIcon("Images//Button//Save-2.png");
		ImageIcon read = new ImageIcon("Images//Button//Read.png");
		ImageIcon read1 = new ImageIcon("Images//Button//Read-1.png");
		ImageIcon read2 = new ImageIcon("Images//Button//Read-2.png");
		ImageIcon top = new ImageIcon("Images//Button//TOP.png");
		ImageIcon up = new ImageIcon("Images//Button//Upload.png");
		ImageIcon unsrollback = new ImageIcon("Images//Button//unsrollback.png");
		
		//Container��ȡ
		Container contain = this.getContentPane();
		 
		this.setSize(1500, 700);
		
		this.setLocationRelativeTo(null); 

		 //Frame��������
		 JLabel backgroundLabel = new JLabel(background);
		 this.getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		 backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		 ((JPanel)contain).setOpaque(false);
		 //������ק����
		 this.addMouseListener(new MouseAdapter() {
			  
			   public void mousePressed(MouseEvent e) {
			     xOld = e.getX();//��¼��갴��ʱ������
			     yOld = e.getY();
			    }
		 });
			   
	     this.addMouseMotionListener(new MouseMotionAdapter() {
			     
			   public void mouseDragged(MouseEvent e) {
			   int xOnScreen = e.getXOnScreen();
			   int yOnScreen = e.getYOnScreen();
			   int xx = xOnScreen - xOld;
			   int yy = yOnScreen - yOld;
			   GameFrame.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
			   }
		 });
	     
	     //��Ϸ�����
	     JLabel outbround = new JLabel();
	     outbround.setBounds(this.getWidth()/2-275, this.getHeight()-550, 560, 560);
	     outbround.setBackground(Color.CYAN);
		 outbround.setOpaque(true);
	     this.add(outbround);

	     //�����ϷBlock,����ʼ��
	     bs = new Block[Diff][Diff];
	     for(int i=0; i<Diff; i++) {
			 for(int j=0; j<Diff; j++) {
				 bs[i][j] = new Block();
				 bs[i][j].InitBlock(Xtemp, Ytemp, Diff);
				 bs[i][j].setOpaque(true);
				 outbround.add(bs[i][j]);
				 if(Diff==4)
					 Xtemp += 140; //���10��
				 if(Diff==5)
					 Xtemp += 112;
			 }
			if(Diff==4) {
				Ytemp +=137;//���10
				Xtemp = 5;
			 }
			if(Diff==5) {
				 Ytemp +=110;//���10
				 Xtemp = 5;
			}
	     }	 
	     
	     //���¿�ʼ��Ϸ��ť
	     JButton restartBT = new JButton();
	     restartBT.setBounds(100, 200, 220, 46);
	     restartBT.setPreferredSize(new Dimension(80,20));
	     restartBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     restartBT.setIcon(restart);
	     restartBT.setPressedIcon(restart2);
	     restartBT.setRolloverIcon(restart1);
	     
	     //��ȡ��Ϸ��ť
	     JButton readBT = new JButton();
	     readBT.setBounds(100, 280, 220, 46);
	     readBT.setPreferredSize(new Dimension(80,20));
	     readBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     readBT.setIcon(read);
	     readBT.setPressedIcon(read2);
	     readBT.setRolloverIcon(read1);
	     
	     //������Ϸ��ť
	     JButton saveBT = new JButton();
	     saveBT.setBounds(100, 360, 220, 46);
	     saveBT.setPreferredSize(new Dimension(80,20));
	     saveBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     saveBT.setIcon(save);
	     saveBT.setPressedIcon(save2);
	     saveBT.setRolloverIcon(save1);
	    
	     //��Ϸ���������水ť
	     JButton backBT = new JButton();
	     backBT.setBounds(100, 440, 220, 46);
	     backBT.setPreferredSize(new Dimension(80,20));
	     backBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     backBT.setIcon(back);
	     backBT.setPressedIcon(back2);
	     backBT.setRolloverIcon(back1);
	     backBT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				 main.setVisible(true);
			     GameFrame.this.setVisible(false);
			     GameFrame.this.requestFocus();
			}
	    	 
	    	 
		});
	     //��Ϸ�˳���ť
	     JButton exitBT = new JButton();
	     exitBT.setBounds(100, 520, 220, 46);
	     exitBT.setPreferredSize(new Dimension(80,20));
	     exitBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     exitBT.setIcon(exit);
	     exitBT.setPressedIcon(exit2);
	     exitBT.setRolloverIcon(exit1);
	    
	     //�������
	     JLabel score = new JLabel();
	     score.setFont(new Font("����", Font.BOLD, 20));
	     score.setBounds(1200, 200, 200, 50);
	     score.setText("�÷�:0");
	     score.setBackground(Color.yellow);
	     score.setOpaque(true);
	     this.add(score);
	     
	     //��߷�
	     JLabel maxscore = new JLabel();
	     maxscore.setFont(new Font("����", Font.BOLD, 20));
	     maxscore.setBounds(1200, 260, 200, 50);
	     maxscore.setText("��߷�:"+MaxScore);
	     maxscore.setBackground(Color.yellow);
	     maxscore.setOpaque(true);
	     this.add(maxscore);
	     
	     //���߲���
	     JLabel stepsum = new JLabel();
	     stepsum.setFont(new Font("����", Font.BOLD, 20));
	     stepsum.setBounds(1200, 320, 200, 50);
	     stepsum.setText("���߲���:0");
	     stepsum.setBackground(Color.yellow);
	     stepsum.setOpaque(true);
	     this.add(stepsum);
	     
	     //����
	     JLabel always = new JLabel();
	     always.setFont(new Font("����", Font.BOLD, 20));
	     always.setBounds(1200, 380, 200, 50);
	     always.setText("����:0");
	     always.setBackground(Color.yellow);
	     always.setOpaque(true);
	     this.add(always);
	     
	     //tips
	     JLabel tip = new JLabel();
	     tip.setFont(new Font("΢���ź�", Font.BOLD, 15));
	     tip.setForeground(Color.WHITE);//ʱ��ע���ϴ�����32���ϣ��Ա�Ҫ�����������
	     tip.setBounds(470,90,750,100);
	     tip.setOpaque(false);
	     this.add(tip);
	     
	     //���˰�ť
	     JButton srollBT = new JButton();
	     srollBT.setBounds(1200, 460, 220, 46);
	     srollBT.setPreferredSize(new Dimension(80,20));
	     srollBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
	     srollBT.setIcon(unsrollback);
	     srollBT.setToolTipText("���˹�����ʱ�޷�ʹ�ã����ƶ�����");
	     this.add(srollBT);
	     
	     now = Calendar.getInstance();
	     
	     //����������2017��12��9��֮�����
	    // if(now.get(Calendar.YEAR)==2017 && now.get(Calendar.MONTH)+1==12 && now.get(Calendar.DAY_OF_MONTH)<=9) {
		     //ȫ������
		     JButton TopBT = new JButton();
		     TopBT.setBounds(1200, 520, 220, 46);
		     TopBT.setPreferredSize(new Dimension(80,20));
		     TopBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		     TopBT.setIcon(top);
		     this.add(TopBT);
		     
		     //�ϴ�
		     JButton UpBT = new JButton();
		     UpBT.setBounds(1200, 570, 220, 46);
		     UpBT.setPreferredSize(new Dimension(80,20));
		     UpBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		     UpBT.setIcon(up);
		     this.add(UpBT);
	     
		     TopBT.addMouseListener(new MouseAdapter() {
	
					public void mouseClicked(MouseEvent e) {
						
						if(topframe == null) {
							topframe = new JFrame();
							topframe.setSize(675, 471);
							topframe.setLocationRelativeTo(null); 
							topframe.setLayout(null);
							Container contain2 =topframe.getContentPane();
							JLabel backgroundLabel = new JLabel(topbackground);
							topframe.getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
							backgroundLabel.setBounds(0, 0, topbackground.getIconWidth(), topbackground.getIconHeight());
							((JPanel)contain2).setOpaque(false);
							topframe.dispose();
							System.out.println("runhere;");
							topframe.setUndecorated(true);
							topframe.setVisible(true);
							
							JButton close = new JButton("�ر�");
							close.setBackground(Color.WHITE);
							close.setBounds(620, 440,60, 30);
							close.addMouseListener(new MouseAdapter() {
							
								@Override
								public void mouseClicked(MouseEvent e) {
									topframe.setVisible(false);
								}
								
							});
							topframe.add(close);
							JLabel infos = new JLabel();
							infos.setOpaque(false);
							infos.setBounds(88, 120, 520, 40);
							infos.setFont(new Font("΢���ź�", Font.BOLD, 40));
							infos.setForeground(Color.WHITE);
						 	infos.setText("���� ���� ��ַ ʱ��");
							topframe.add(infos);
							int i=0;
							int y=170;
							while(i<10) {
								info[i] = new Info(88,y);
								y += 20;
								topframe.add(info[i]);
								i++;
							}
							
							more = new JTextArea();
							JScrollPane scroll = new JScrollPane(more);
							scroll.setBounds(88, 370, 520, 50);
							topframe.add(scroll);
						}else {
							topframe.setVisible(true);
						}
						 
						String top1 = SaveAndRead.readTop();	
						String[] top2 = null;
						if(top1!=null) {
							 top2 = top1.split("/");
						}
						int lenth = 0;
						if(top2!=null) {
							lenth = top2.length;
						} 
					 	int k=0;
					 	if(lenth!=0) {
						 	while(k<10) {
						 		
						 		 if(lenth!=0) {
						 			 info[k].setText(top2[k]);
						 			 lenth--;
						 		 }else {
						 			 info[k].setText("��λ�Դ�");
						 		 }
						 		 k++;
						 	} 
					 	}else {
					 		info[1].setText("���������������ӣ��޷���ȡ��������Ϣ");
					 	}
					 	StringBuffer sb =null;
					 	if(lenth!=0) {
					 		sb = new StringBuffer();
							while(lenth>0) {
								sb.append(top2[top2.length-lenth]+"\n");
								lenth--;
							}
					 	}
					 	if(sb!=null) {
					 		more.setText("�����������:\n"+sb.toString());
					 	}else {
					 		more.setText("���޸�������");
					 	}
	
					}
					
				
		      });
		      UpBT.addMouseListener(new MouseAdapter() {
	
					public void mouseClicked(MouseEvent e) {
						now = Calendar.getInstance();  
						String name = JOptionPane.showInputDialog(null, "�����������֣�");
						String add = JOptionPane.showInputDialog(null, "�������Ĺ�������е�ַ��");
					    int year = now.get(Calendar.YEAR);  
				        int month = now.get(Calendar.MONTH) + 1; 
				        int day = now.get(Calendar.DAY_OF_MONTH);
				        String time = "" + year +"��" + month + "��" + day+"��";
				        String score = Integer.valueOf(listen.getScore()).toString();
				        if(name!=null) {
					        System.out.println("���̻����������ϴ���"+name+ score+ add+ time);
					        int judge = SaveAndRead.storeTopScore(name, score, add, time);
					        if(judge==1) {
					        	JOptionPane.showMessageDialog(new JPanel(), "�ϴ��ɹ�", "������ʾ",JOptionPane.WARNING_MESSAGE);  
					        }
				        }
					}
					
		      });
	   //  }
	     exitBT.addMouseListener(new MouseAdapter() {
	    	 public void mouseClicked(MouseEvent e) {
	    		  
	    	     if(Integer.valueOf(maxscore.getText().split(":")[1]) > MaxScore){
	    	    	  sr.writeMaxScore(maxscore.getText().split(":")[1]);
	    	     }
	    		 System.exit(0);	 
	    	 
	    	 }
		});
	     restartBT.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					
					 for(int i=0; i<Diff; i++) {
						 for(int j=0; j<Diff; j++) {
						   bs[i][j].setText("");
						 }
					 }
					 c.creat();
					 Step = 0;
					 Score = 0;
					 score.setText("�÷�:0");
					 stepsum.setText("���߲���:0");
					 srollBT.setIcon(unsrollback);
					 listen.reset(bs, Diff, c, Score, MaxScore, Step, score, maxscore, stepsum);
					 DrewColor.drewColor(bs, Diff);
					 
				}
		    	
		    	 
			});
	      saveBT.addMouseListener(new MouseAdapter() {

			 
				public void mouseClicked(MouseEvent e) {
					 try {
						String name = JOptionPane.showInputDialog(null, "���뱣����ļ���");
						if(name!=null && !name.equals("")) {
							int result = sr.write(name, bs, listen.getStep(), listen.getScore(), Diff);
							if(result==1) {
								JOptionPane.showMessageDialog(new JPanel(), "����ɹ�", "������ʾ",JOptionPane.WARNING_MESSAGE);  
							}
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(new JPanel(), "�洢���������ԡ�", "������ʾ",JOptionPane.WARNING_MESSAGE);  
					}
					
					
				}
		    	 
		     
		     });
	      readBT.addMouseListener(new MouseAdapter() {
	    	 
	    	 public void mouseClicked(MouseEvent e) {
	    		 try {
						int[][] TempBS;
						String names = sr.readFileName();
						JOptionPane.showMessageDialog(new JPanel(), names, "�Ѿ����ڵ���Ϸ�����ļ�",JOptionPane.WARNING_MESSAGE);  
						String name = JOptionPane.showInputDialog(null, "������Ҫ��ȡ�Ľ�������");
						if(name!=null) {
							sr.read(name);
							TempBS = sr.getBs();
							Score = sr.getScore();
							Step = sr.getStep();
							//GameFrame.this.Diff = sr.getDiff();
							if(GameFrame.this.Diff != sr.getDiff()) {
								 GameFrame.this.Diff = sr.getDiff();
								 outbround.removeAll();
								 outbround.setVisible(false);
								 bs = new Block[Diff][Diff];  
								 Xtemp = 5;
								 Ytemp = 5;
								 bs = new Block[Diff][Diff];
								 for(int i=0; i<Diff; i++) {
									 for(int j=0; j<Diff; j++) {
										 bs[i][j] = new Block();
										 bs[i][j].InitBlock(Xtemp, Ytemp, Diff);
										 bs[i][j].setOpaque(true);
										 outbround.add(bs[i][j]);
										 if(Diff==4)
											 Xtemp += 140; //���10��
										 if(Diff==5)
											 Xtemp += 112;
									 }
									if(Diff==4) {
										Ytemp +=137;//���10
										Xtemp = 5;
									 }
									if(Diff==5) {
										 Ytemp +=110;//���10
										 Xtemp = 5;
									}
								 outbround.setVisible(true);
								 }
							}
							score.setText("�÷�:"+Integer.valueOf(Score).toString());
							stepsum.setText("���߲���:"+Integer.valueOf(Step).toString());
							for(int i=0; i<Diff; i++) {
								for(int j=0; j<Diff; j++) {
									if(TempBS[i][j]==0) {
										bs[i][j].setText("");
										continue;
									}
									bs[i][j].setText(Integer.valueOf(TempBS[i][j]).toString());
									
								}
							}
							srollBT.setIcon(unsrollback);
							listen.reset(bs, Diff, c, Score, MaxScore, Step, score, maxscore, stepsum);
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(new JPanel(), "�ļ������ڣ���������������", "������ʾ",JOptionPane.WARNING_MESSAGE);  
					}
					
	    	 }
		});
	     srollBT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				 if(listen!=null && listen.getSrollFlag()>0) {
					 listen.srollback();
				 }
			}
	    	 
		});
	     
	     if(difficult.getTipflag()==0) {
		     tipscontent.setTip(tip);
		     cleartips.setTip(tip);
		     timer.schedule(tipscontent, 3*1000, 120*1000);
		     timer.schedule(cleartips,18*1000,120*1000);
		     tip.setText("��������ʾһЩС��ʾ���������ر�����������������ѡ��ر�С��ʾ����Ŀǰ���򿪣�"); 
	     }else {
	    	 tip.setText("��������ʾһЩС��ʾ��������������������������ѡ���С��ʾ����Ŀǰ���رգ�"); 
	    	 cleartips.setTip(tip);
	    	 timer.schedule(cleartips,5*1000);
	     }
	     this.add(restartBT);
	     this.add(exitBT);
	     this.add(backBT);
	     this.add(readBT);
	     this.add(saveBT);
	     System.out.println(MaxScore);
		 //��Ϸ��������Ϸ����
		 c = new createRandom(bs,Diff);
		 c.creat();
		 listen =  new GameListen(bs, Diff, c, Score, MaxScore, Step, score, maxscore, stepsum, sr, always, srollBT);
		 this.addKeyListener(listen);
		 this.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 GameFrame.this.requestFocus();
				 
			 }
		});
		 
		 
		 
	     //�����ޱ�����
		 this.requestFocus();
		 this.dispose();
		 this.setUndecorated(true);
		 this.setLayout(null);
	     this.setVisible(true);
	}

}



