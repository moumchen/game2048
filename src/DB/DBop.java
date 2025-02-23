package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.ErrorFrame;

/**
 * 排名用，用于获取和上载数据信息
 * @author ChenYanQian
 *
 */
public class DBop {
	
	static String CloudDB = "jdbc:mysql://120.79.87.67:3306/Game2048?useUnicode=true&characterEncoding=UTF-8";
	static String driver = "com.mysql.jdbc.Driver";
	static String user = "root";
	static String pass = "cyq967240";
	
	/**
	 * 获取连接
	 * @return Connection
	 */
	public static Connection getConnect() {
		Connection con =null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(CloudDB, user, pass);
			return con;
		}catch(Exception e) {
			 e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 关闭资源
	 * @param con
	 * @param st
	 * @param rs
	 */
	public static void release(Connection con, Statement st, ResultSet rs) {
		
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		try {
			if(st!=null) {
				st.close();
			}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		try {
			if(con!=null) {
				con.close();
			}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		
	}
}
