package Fuction;

import java.util.TimerTask;

import javax.swing.JLabel;
 
/**
 * Tip��Ϣ������ݹ���
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
		case 1:tip.setText("��֪���𣿣�2048��Ϸ���緢����2014��3��20�գ�ԭ��2048��GitHub�Ϸ�����������Gabriele Cirulli��");tip.setBounds(390,90,750,100);break;
		case 2:tip.setText("��ܰС��ʾ: ��Ϸ�����п��ܻ����ʧȥ���������������Ϸ��弴���ػ񽹵�Ӵ~ (#`O��)");tip.setBounds(450, 90, 700, 100);break;
		case 3:tip.setText("��Ϸ����1������������ܷ��ڽ��䣬���ְ�˳��������С���������������ʹδ����ڵ���һ��/�������ġ�"); tip.setBounds(400,90,750,100);break;
		case 4:tip.setText("��Ϸ����2��ʱ��ע���ϴ�����32���ϣ��Ա�Ҫ���������,�Դ������ڵ�һ��Ϊ��Ҫ�ƶ�����!"); tip.setBounds(420,90,750,100);break;
		case 5:tip.setText("��ܰС��ʾ����Ϸ������ʹ�ð�ť������Ϸ���ʧȥ���㣬�����Ϸ����ػ񽹵㳩����Ϸ�ɣ�(#`O��");tip.setBounds(400,90,750,100);break;
		case 6:tip.setText("��֪����?:���½ǿ��Բ鿴ȫ���������������ϴ����ķ���������������PK�ɣ�����Ҫ����ʹ�ã�");tip.setBounds(400,90,750,100);break;
		case 7:tip.setText("����Ϸ������Ա���������� ���� ������ ����Ǭ ����");tip.setBounds(400,90,750,100);break;
		}
		
	}
	 
	public void run() {
		changeTips(tip);
		
	}
	
	
}

 