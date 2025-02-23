package Fuction;

import Beans.Block;

/**
 * 创建随机数
 * 
 *
 */
public class createRandom {
	Block[][] bs;
	int Diff;
	
	public createRandom(Block[][] bs, int Diff) {
		this.bs=bs;
		this.Diff = Diff;
	}
	
	public void reset(Block[][] bs, int Diff) {
		this.bs = bs;
		this.Diff = Diff;
	}
	
	public void creat() {
		int x;
		int y;
		 //随机赋值首数字块
		 do{
			 x = (int) Math.round(Math.random()*10);
			 y = (int) Math.round(Math.random()*10);
			 if(x>=Diff && x<2*Diff) x -= Diff;
			 if(x>=2*Diff) x-= 2*Diff;
			 if(y>=Diff && y<2*Diff) y -= Diff;
			 if(y>=2*Diff) y-= 2*Diff;
			 System.out.println(x+" "+y);
		 }while(bs[x][y].getText()!="");
		 
		 bs[x][y].setText("2");
		 
	}
}

 