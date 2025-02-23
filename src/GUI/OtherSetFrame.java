package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beans.Difficult;
import Music.MainFrameBGM;

/**
 * ���ڶ���Ϸ�������õ�UI��� 
 * @author Chenyanqian
 */

public class OtherSetFrame extends JFrame{
    
	private int DifficultClickCount = 0;
	private int OpenOffClickCount = 0;
	private int OpenOff2ClickCount = 0;
	private int MusicSelectClickCount = 0;
	private int Musicnow = 1;
	private int xOld = 0;
	private int yOld = 0;
	JButton closeTipsBT = null;
	
	private static final long serialVersionUID = 1L;
	
	public OtherSetFrame(MainFrameBGM bgm, Difficult Diff) {
		
	
		//ͼƬ��ͼ��
		ImageIcon BackgroundIcon = new ImageIcon("Images//BackgroundIMG//GameSetting.png");
		ImageIcon Submit = new ImageIcon("images//Button//Submit.png");
		ImageIcon Submit1 = new ImageIcon("images//Button//Submit-1.png");	
		ImageIcon Submit2 = new ImageIcon("images//Button//Submit-2.png");
		ImageIcon MusicButton = new ImageIcon("images//Button//MusicButton.png");
		ImageIcon MusicChoose = new ImageIcon("images//Button//MusicChoose.png");
		ImageIcon DifficultChoose = new ImageIcon("images//Button//DifficultChoose.png");
		ImageIcon opentip = new ImageIcon("images//Button//openTip.png");
		ImageIcon closetip = new ImageIcon("images//Button//closeTip.png");

		Container contain = this.getContentPane();
		this.setSize(632, 709);
		this.setTitle("��Ϸ����");
		this.setLocationRelativeTo(null); 
		this.setLayout(null);
		
		//�����ק
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
			   OtherSetFrame.this.setLocation(xx, yy);//������ק�󣬴��ڵ�λ��
			   }
		 });
		 
	    //��������
	    JLabel backgroundLabel = new JLabel(BackgroundIcon);
		this.getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
		backgroundLabel.setBounds(0, 0, BackgroundIcon.getIconWidth(), BackgroundIcon.getIconHeight());
		((JPanel)contain).setOpaque(false);
 
		//��Ϸ�Ѷ�����
		JComboBox<String> DifficultBOX = new JComboBox<String>();
		DifficultBOX.addItem("4��4����");
		DifficultBOX.addItem("5��5����");
		DifficultBOX.setSelectedItem("4��4����");
		DifficultBOX.setBackground(new Color(26,188,156));
		DifficultBOX.setCursor(new Cursor(Cursor.HAND_CURSOR));
		DifficultBOX.setForeground(Color.white);
		DifficultBOX.setBounds(this.getWidth()/2, this.getHeight()-500, 250, 40);
		DifficultBOX.setVisible(false);
		DifficultBOX.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent e) {
				 
				 //�˴�С�Ʊ꣺���Ͱ�ȫ���⣬����ʹ����instanceof���ɳ��֣��˴��ܹ���֤��ȡ������Դ��JComboBox��������Ӵ�С�Ʊ���
				 int index = ((JComboBox<String>)e.getSource()).getSelectedIndex();
				 int temp = Diff.getDiff();
				 if(index == 1) Diff.setDiff(5);
				 if(index == 0) Diff.setDiff(4);
				 if(Diff.getDiff()!=temp) {
					 Diff.setFlag(2);
				 }
			}
			
		});
		JButton DifficultChooseBT = new JButton("");
		DifficultChooseBT.setSize(220, 40);
		DifficultChooseBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		DifficultChooseBT.setIcon(DifficultChoose);
		DifficultChooseBT.setBounds(this.getWidth()/2-230, this.getHeight()-500, 220, 40);
		DifficultChooseBT.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				DifficultClickCount++;
				 if(DifficultClickCount==1) {
				 DifficultBOX.setVisible(true);}
				 else {
					 DifficultClickCount=0;
					 DifficultBOX.setVisible(false);
				 }
			}
			
		});
		
		//�������ֿ���
		JButton OpenOff = new JButton("");
		OpenOff.setSize(250, 40);
		OpenOff.setBackground(new Color(26,188,156));
		OpenOff.setText("���ֿ�����");
		OpenOff.setForeground(Color.WHITE);
		OpenOff.setCursor(new Cursor(Cursor.HAND_CURSOR));
		OpenOff.setBounds(this.getWidth()/2,  this.getHeight()-450, 250, 40);
		OpenOff.setVisible(false);
		OpenOff.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				OpenOff2ClickCount++;
				 if(OpenOff2ClickCount==1) {
					bgm.StopBGM();
					Musicnow =0;
					OpenOff.setText("�����ѹر�");
					}
				 else {
					 if(Musicnow==0) Musicnow = 1;
					 switch(Musicnow) {
					 case 1:bgm.ChangeBGM("Sounds//Foxtail.wav");break;
					 case 2:bgm.ChangeBGM("Sounds//TheTruthThatYouLeave.wav");break;
					 case 3:bgm.ChangeBGM("Sounds//Cry.wav");break;
					 }
					 OpenOff2ClickCount=0; 
					 OpenOff.setText("�����ѿ���");
				 }
			}
			
		});
		JButton MusicBT = new JButton("");
		MusicBT.setSize(220, 40);
		MusicBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		MusicBT.setIcon(MusicButton);
		MusicBT.setBounds(this.getWidth()/2-230, this.getHeight()-450, 220, 40);
		MusicBT.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				OpenOffClickCount++;
				 if(OpenOffClickCount==1) {
					 OpenOff.setVisible(true);}
				 else {
					 OpenOffClickCount=0; 
					 OpenOff.setVisible(false);
				 }
			}
			
		});
		
		//��������ѡ��
		JPanel MusicChoosePanel = new JPanel();
		MusicChoosePanel.setLayout(null);
		MusicChoosePanel.setBackground(new Color(26,188,156));
		MusicChoosePanel.setBounds(this.getWidth()/2, this.getHeight()-400,250,120);
		MusicChoosePanel.setVisible(false);
		
		JButton MusicChooseOne = new JButton("��ѡ�У��������� 1");
		MusicChooseOne.setSize(250, 40);
		MusicChooseOne.setBackground(new Color(0,0,0));
		MusicChooseOne.setOpaque(false);
		MusicChooseOne.setBounds(0, 0, 250, 40);
		MusicChooseOne.setCursor(new Cursor(Cursor.HAND_CURSOR));
		MusicChooseOne.setForeground(Color.WHITE);
		
		JButton MusicChooseTwo = new JButton("��������2");
		MusicChooseTwo.setSize(250, 120);
		MusicChooseTwo.setBackground(new Color(0,0,0));
		MusicChooseTwo.setOpaque(false);
		MusicChooseTwo.setBounds(0, 40, 250, 40);
		MusicChooseTwo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		MusicChooseTwo.setForeground(Color.WHITE);
		
		JButton MusicChooseThree = new JButton("�������� 3");
		MusicChooseThree.setSize(250, 120);
		MusicChooseThree.setBackground(new Color(0,0,0));
		MusicChooseThree.setOpaque(false);
		MusicChooseThree.setBounds(0, 80, 250, 40);
		MusicChooseThree.setCursor(new Cursor(Cursor.HAND_CURSOR));
		MusicChooseThree.setForeground(Color.WHITE);
		
		MusicChoosePanel.add(MusicChooseOne);
		MusicChoosePanel.add(MusicChooseTwo);
		MusicChoosePanel.add(MusicChooseThree);
		
		JButton MusicChooseBT = new JButton("");
		MusicChooseBT.setSize(220, 40);
		MusicChooseBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		MusicChooseBT.setIcon(MusicChoose);
		MusicChooseBT.setBounds(this.getWidth()/2-230, this.getHeight()-400, 220, 40);
		
		closeTipsBT = new JButton("�ر�С��ʾ");
		closeTipsBT.setSize(220, 40);
		closeTipsBT.setBounds(this.getWidth()/2-225, this.getHeight()-350, 210, 40);
		closeTipsBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		closeTipsBT.setIcon(closetip);
		
		closeTipsBT.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				System.out.println(Diff.getTipflag());
				 if(Diff.getTipflag()==0) {
					 Diff.setTipflag(1);
					 System.out.println("run");
					 closeTipsBT.setIcon(opentip);
					 return;
				 }
				 if(Diff.getTipflag()==1) {
					 Diff.setTipflag(0);
					 closeTipsBT.setIcon(closetip);
					 return;
				 }
			}
			
		});
		MusicChooseBT.addMouseListener(new MouseAdapter() {
		
			
			public void mouseClicked(MouseEvent e) {
				 MusicSelectClickCount++;
				 if(MusicSelectClickCount==1) {
					 MusicChoosePanel.setVisible(true);
				 }else {
					 MusicSelectClickCount=0;
					 MusicChoosePanel.setVisible(false);
				 }
			}
			
		});
		MusicChooseOne.addMouseListener(new MouseAdapter() {

			 
			public void mouseClicked(MouseEvent e) {
				 if(Musicnow == 0) {
					 JOptionPane.showMessageDialog(new JPanel(), "���ѹر����֣��뿪����ѡ��", "������ʾ",JOptionPane.WARNING_MESSAGE);  
					 return;
				 }
				 bgm.ChangeBGM("Sounds//Foxtail.wav");
				 Musicnow =1;
			     MusicChooseOne.setText("��ѡ�У���������1");
			     MusicChooseTwo.setText("��������2");
			     MusicChooseThree.setText("��������3");
			}
			
		});
		MusicChooseTwo.addMouseListener(new MouseAdapter() {

			 
			public void mouseClicked(MouseEvent e) {
				 if(Musicnow == 0) {
					 JOptionPane.showMessageDialog(new JPanel(), "���ѹر����֣��뿪����ѡ��", "������ʾ", JOptionPane.WARNING_MESSAGE);  
					 return;
				 }
				bgm.ChangeBGM("Sounds//TheTruthThatYouLeave.wav");
				Musicnow =2;
			     MusicChooseOne.setText("��������1");
			     MusicChooseTwo.setText("��ѡ�У���������2");
			     MusicChooseThree.setText("��������3");
			}
			
		});
		MusicChooseThree.addMouseListener(new MouseAdapter() {

			 
			public void mouseClicked(MouseEvent e) {
				 if(Musicnow == 0) {
					 JOptionPane.showMessageDialog(new JPanel(), "���ѹر����֣��뿪����ѡ��", "������ʾ",JOptionPane.WARNING_MESSAGE);  
					 return;
				 }
				bgm.ChangeBGM("Sounds//Cry.wav");
				Musicnow =3;
			     MusicChooseOne.setText("��������1");
			     MusicChooseTwo.setText("��������2");
			     MusicChooseThree.setText("��ѡ�У���������3");
			}
			
		});
		//�˳���ť
		JButton SubmitBT = new JButton("");
		SubmitBT.setSize(220, 46);
		SubmitBT.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SubmitBT.setIcon(Submit);
		SubmitBT.setRolloverIcon(Submit1);
		SubmitBT.setPressedIcon(Submit2);
		SubmitBT.setBounds(this.getWidth()/2-110, this.getHeight()-70, 220, 46);
		SubmitBT.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				 OtherSetFrame.this.setVisible(false);
			}	
			
		});
		 
		//������
		contain.add(SubmitBT);
		contain.add(DifficultChooseBT);
		contain.add(DifficultBOX);
		contain.add(MusicBT);
		contain.add(OpenOff);
		contain.add(MusicChooseBT);
		contain.add(MusicChoosePanel);
		contain.add(closeTipsBT);
		//���ô����ޱ���
		this.dispose();
		this.setUndecorated(true);
		this.setVisible(true);
	}
	 
	 
}
