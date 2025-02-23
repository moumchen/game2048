package Beans;

/**
 * 用于保存玩家记录的游戏难度值
 * @author Chenyanqian
 */
public class Difficult {
	
	int Diff;
	int flag = 0; //记录游戏进入后重新设置游戏的标志
	int tipflag = 0; //默认开放为0，小提醒
	
	
	public int getTipflag() {
		return tipflag;
	}

	public void setTipflag(int tipflag) {
		this.tipflag = tipflag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	int Reset = 0;
	public Difficult(int Diff) {
		this.Diff = Diff;
	}
	
	public int getReset() {
		return Reset;
	}

	public void setReset(int reset) {
		Reset = reset;
	}

	public int getDiff() {
		return Diff;
	}

	public void setDiff(int diff) {
		Diff = diff;
	}
	
	
}
