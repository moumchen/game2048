package Fuction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Beans.Block;
import DB.DBop;
import GUI.ErrorFrame;

/**
 * 读取游戏和保存游戏
 */
public class SaveAndRead{
	
	int[][] bs;
	int Step; 
	int Score; 
	int Diff;
	public final static String MaxScoreFile = "Config//ProgramConfig//max.properties";
	
	public int write(String name, Block[][] bs, int Step, int Score, int Diff) throws IOException {
		
		File newfile = new File("Config//SaveFiles//"+name+".properties");
		if(!newfile.exists()) {
			newfile.createNewFile();

	    }
		FileInputStream in = new FileInputStream(newfile);
		FileOutputStream out = new FileOutputStream(newfile);
		Properties p = new Properties();
		
	    p.load(in);
		p.setProperty("Step", Integer.valueOf(Step).toString());
		p.setProperty("Score", Integer.valueOf(Score).toString());
		p.setProperty("Diff", Integer.valueOf(Diff).toString());
		for(int i=0; i<Diff; i++) {
			for(int j=0; j<Diff; j++) {
				p.setProperty("bs["+i+"]["+j+"]", bs[i][j].getText());
			}
		}
		p.store(out, "CUIT2048-ConfigFile-UserSaveFile");

		return 1;
 
	}
	
	public String readFileName() {
		
		File dir = new File("Config//SaveFiles//");
		File[] files;
	 
		StringBuffer names = new StringBuffer();
		
		if(!dir.isDirectory()) {
			dir.mkdir();
		}
		names.append("\n");
		files = dir.listFiles();
		for(File file:files) {
			names.append(file.getName());
			names.append("\n");
		}
		
		return names.toString();
	}
	
	public int read(String name) throws IOException {
		File newfile = new File("Config//SaveFiles//"+name+".properties");
		if(!newfile.exists()) {
			newfile.createNewFile();
	    }
		FileInputStream in = new FileInputStream(newfile);
		Properties p = new Properties();
	    p.load(in);
	    this.Step = Integer.valueOf(p.getProperty("Step"));
	    this.Score = Integer.valueOf(p.getProperty("Score"));
	    this.Diff = Integer.valueOf(p.getProperty("Diff"));
	    bs = new int[Diff][Diff];
	    for(int i=0; i<Diff; i++) {
	    	for(int j=0; j<Diff; j++) {
	    	    
	    		if(p.getProperty("bs["+i+"]["+j+"]").equals("")) {
	    			try {
	    			bs[i][j]=0;
	    			}catch(Exception e) {
	    				System.out.println(i+" "+j);
	    			}
	    			continue;
	    		}
	    		bs[i][j] = Integer.valueOf(p.getProperty("bs["+i+"]["+j+"]"));
    		}
	    		 
	    
	    }
	    
	  
		return 1;
	}
	/**
	 * 读取整个程序的最高分数
	 * @return
	 */
	public int readMaxScore() {
		
		File file = new File(MaxScoreFile);
		try {
			if(!file.exists()) {
					file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			Properties p = new Properties();
			p.load(fis);
			if(p.getProperty("MaxScore")!=null) {
				System.out.println("过程化： 读  " + MaxScoreFile + " 详细： 最高分读取成功");
				return Integer.valueOf(p.getProperty("MaxScore")).intValue();
			}else {
				SaveAndRead.this.writeMaxScore("0");
				return 0;
			}
		}catch(Exception e) {
			new ErrorFrame();
		}
		
		return 0;
	}
	
	/**
	 * 将最高分数写入配置文件
	 * @return
	 */
	public void writeMaxScore(String score) {
		File file = new File(MaxScoreFile);
		try {
			if(!file.exists()) {
					file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file);
			Properties p = new Properties();
			p.load(fis);
			p.setProperty("MaxScore", score);
			p.store(fos, "CUIT2048 - MAXSCORE PROPERTIES");
			System.out.println("过程化： 写  " + MaxScoreFile + " 详细： 最高分写入成功");
		}catch(Exception e) {
			new ErrorFrame();
		}
		
	}
	
	public static String readTop() {
		
		Connection con = DBop.getConnect();
		StringBuffer sb = new StringBuffer();
		Statement st= null;
		ResultSet rs =null;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from TopScore order by cast(score as SIGNED INTEGER) desc");
			while(rs.next()) {
				sb.append(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"/");
			}
			return sb.toString();
		} catch (Exception e) {
		
		}finally {
			DBop.release(con, st, rs); 
		}

		return null;
	}
	
	public static int storeTopScore(String name, String Score, String add, String time) {  
		
		Connection con = DBop.getConnect();
		PreparedStatement st= null;
		try {
			st = con.prepareStatement("insert into TopScore values(?,?,?,?)");
			st.setString(1, name);
			st.setString(2, Score);
			st.setString(3, add);
			st.setString(4, time);
			st.executeUpdate(); 
			return 1;
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(new JPanel(), "上传信息失败，请检查网络连接情况", "错误",JOptionPane.WARNING_MESSAGE);  
		} 
		
		return -1;
		
	}
	
	
	public int[][] getBs() {
		return bs;
	}

	

	public int getStep() {
		return Step;
	}

	

	public int getScore() {
		return Score;
	}


	public int getDiff() {
		return Diff;
	}

}
