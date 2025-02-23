package Fuction;

import java.awt.Color;

import Beans.Block;

/**
 * 对矩阵方块作色
 * @author Chenyanqian
 *
 */
public class DrewColor {

	public static void drewColor(Block[][] bs, int Diff) {
		
		for(int i=0; i<Diff; i++) {
			for(int j=0; j<Diff; j++) {
				if(bs[i][j].getText()=="") {
					bs[i][j].setBackground(Color.YELLOW);;
				}
				if(bs[i][j].getText()!="") {
					if(bs[i][j].getText().equals("2")) {
						bs[i][j].setBackground(new Color(183,247,255));
					}
					if(bs[i][j].getText().equals("4")) {
						bs[i][j].setBackground(new Color(100,189,255));
					}
					if(bs[i][j].getText().equals("8")) {
						bs[i][j].setBackground(new Color(52,128,254));
					}
					if(bs[i][j].getText().equals("16")) {
						bs[i][j].setBackground(new Color(206,117,236));
					}
					if(bs[i][j].getText().equals("32")) {
						bs[i][j].setBackground(new Color(230,49,167));
					}
					if(bs[i][j].getText().equals("64")) {
						bs[i][j].setBackground(new Color(140,240,66));
					}
					if(bs[i][j].getText().equals("128")) {
						bs[i][j].setBackground(new Color(81,225,218));
					}
					if(bs[i][j].getText().equals("256")) {
						bs[i][j].setBackground(new Color(81,189,225));
					}
					if(bs[i][j].getText().equals("512")) {
						bs[i][j].setBackground(new Color(235,235,71));
					}
					if(bs[i][j].getText().equals("1024")) {
						bs[i][j].setBackground(new Color(245,0,0));
					}
					if(bs[i][j].getText().equals("2048")) {
						bs[i][j].setBackground(new Color(100,189,255));
					}
				}
			}
		}
	}
}
