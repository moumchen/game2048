package Beans;

/**
 * ���ڱ�����Ҽ�¼����Ϸ�Ѷ�ֵ
 * @author Chenyanqian
 */
public class Difficult {
	
	int Diff;
	int flag = 0; //��¼��Ϸ���������������Ϸ�ı�־
	int tipflag = 0; //Ĭ�Ͽ���Ϊ0��С����
	
	
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
