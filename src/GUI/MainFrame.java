package GUI;


import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beans.Difficult;
import Music.MainFrameBGM;

/**
 * ????????
 * @author Chenyanqian
 *
 */
public class MainFrame extends JFrame {
	 
	private static final long serialVersionUID = 1L;
	
	//???????????????????
	 int xOld = 0;
	 int yOld = 0;
	 Difficult Diff = new Difficult(4);
	 private OtherSetFrame set;
	 private GameFrame gameframe;
	 
	 public MainFrame() {
		 
		 MainFrameBGM bgm = new MainFrameBGM("Sounds//Foxtail.wav");
		 //???
		 ImageIcon BackgroundIcon = new ImageIcon("Images//BackgroundIMG//MainFrame.png");
		 ImageIcon Start = new ImageIcon("Images//Button//Start.png");
		 ImageIcon Start1 = new ImageIcon("Images//Button//Start-1.png");
		 ImageIcon Start2 = new ImageIcon("Images//Button//Start-2.png");
		 ImageIcon Set = new ImageIcon("Images//Button//Set.png");
		 ImageIcon Set1 = new ImageIcon("Images//Button//Set-1.png");
		 ImageIcon Set2 = new ImageIcon("Images//Button//Set-2.png");
		 ImageIcon About = new ImageIcon("Images//Button//About.png");
		 ImageIcon About1 = new ImageIcon("Images//Button//About-1.png");
		 ImageIcon About2 = new ImageIcon("Images//Button//About-2.png");
		 ImageIcon Exit = new ImageIcon("Images//Button//Exit.png");
		 ImageIcon Exit1 = new ImageIcon("Images//Button//Exit-1.png");
		 ImageIcon Exit2 = new ImageIcon("Images//Button//Exit-2.png");
		 ImageIcon MusicOPEN = new ImageIcon("Images//Button//MusicOPEN.gif");
		/*
		  //???????
		 Toolkit tl =  Toolkit.getDefaultToolkit();
		 */
		 
		 //Container???
		 Container contain = this.getContentPane();
		 
		 this.setSize(1500, 700);
		 this.setLocationRelativeTo(null); 
		 //???????????
		 this.addMouseListener(new MouseAdapter() {
			  
			   public void mousePressed(MouseEvent e) {
			     xOld = e.getX();//???????????????
			     yOld = e.getY();
			    }
		 });
			   
	     this.addMouseMotionListener(new MouseMotionAdapter() {
			     
			   public void mouseDragged(MouseEvent e) {
			   int xOnScreen = e.getXOnScreen();
			   int yOnScreen = e.getYOnScreen();
			   int xx = xOnScreen - xOld;
			   int yy = yOnScreen - yOld;
			   MainFrame.this.setLocation(xx, yy);//????????????????
			   }
		 });
		 //Frame????????
		 JLabel backgroundLabel = new JLabel(BackgroundIcon);
		 this.getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		 backgroundLabel.setBounds(0, 0, BackgroundIcon.getIconWidth(), BackgroundIcon.getIconHeight());
		 ((JPanel)contain).setOpaque(false);
		 
		 //?????????
		 JButton StartBT = new JButton("");
		 StartBT.setSize(220, 46);
		 StartBT.setPreferredSize(new Dimension(80,20));
		 StartBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 StartBT.setIcon(Start);
		 StartBT.setPressedIcon(Start2);
		 StartBT.setRolloverIcon(Start1);
		 StartBT.setBounds(this.getWidth()/2-110,this.getHeight()/2,220,46);	 
		 StartBT.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				  
			      MainFrame.this.setVisible(false);
			      
			      if(gameframe==null)
			    	  gameframe = new GameFrame(MainFrame.this , Diff);
			      else {
			    	  if(Diff.getFlag()==2) {
			    	  gameframe.dispose();
			    	  Diff.setFlag(1);
			    	  gameframe = new GameFrame(MainFrame.this , Diff);
			    	  }	
			    	  gameframe.setVisible(true);
			      }
			 
		}});
		 //?????????
		 JButton SetBT = new JButton("");
		 SetBT.setSize(220, 46);
		 SetBT.setPreferredSize(new Dimension(80,20));
		 SetBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 SetBT.setIcon(Set);
		 SetBT.setPressedIcon(Set2);
		 SetBT.setRolloverIcon(Set1);
		 SetBT.setBounds(this.getWidth()/2-110,this.getHeight()/2+55,220,46);	
		 SetBT.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				  if(gameframe!=null){
					  JOptionPane.showMessageDialog(new JPanel(), "????????????????????????\n??1?????????????????????????????????????????????????ив? ???????????????????\n??2??????????????????????????????????????????????????????????????ив?", "???????",JOptionPane.WARNING_MESSAGE);    
				  }
				  if(set==null) 
				  set = new OtherSetFrame(bgm, Diff);
				  else
					  set.setVisible(true);
				
				  
			 }
		});
		 //??????????
		 JButton AboutBT = new JButton("");
		 AboutBT.setSize(220, 46);
		 AboutBT.setPreferredSize(new Dimension(80,20));
		 AboutBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 AboutBT.setIcon(About);
		 AboutBT.setPressedIcon(About2);
		 AboutBT.setRolloverIcon(About1);
		 AboutBT.setBounds(this.getWidth()/2-110,this.getHeight()/2+110,220,46);	
		 AboutBT.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			     new AboutGameFrame();
			 }
			 
		 });
		 //??????
		 JButton ExitBT = new JButton("");
		 ExitBT.setSize(220, 46);
		 ExitBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 ExitBT.setPreferredSize(new Dimension(80,20));
		 ExitBT.setIcon(Exit);
	     ExitBT.setPressedIcon(Exit2);
	     ExitBT.setRolloverIcon(Exit1);
	     ExitBT.setBounds(this.getWidth()/2-110,this.getHeight()/2+165,220,46);	 
		 ExitBT.addMouseListener(new MouseAdapter() {
			//????????
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			 
		});
		 //???????????????????????+
		 JButton MusicBT = new JButton("");
		 MusicBT.setSize(30, 40);
		 MusicBT.setIcon(MusicOPEN);
		 Color c = new Color(0,0,0);//?????????
		 MusicBT.setBackground(c);
		 MusicBT.setOpaque(false);//??????
		 MusicBT.setBorder(null);
		 MusicBT.setBounds(this.getWidth()-150, this.getHeight()-50, 30, 40);
		 MusicBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 MusicBT.setToolTipText("?????????");
		 
		 this.setLayout(null);
		 contain.add(StartBT);
		 contain.add(SetBT);
		 contain.add(AboutBT);
		 contain.add(ExitBT);
		 contain.add(MusicBT);
		 
		 //???????????
		 this.dispose();
		
		 this.setUndecorated(true);
		 //???
		 this.setVisible(true);
		 
	 }
 
}
