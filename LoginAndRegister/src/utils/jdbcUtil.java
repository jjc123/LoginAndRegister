package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbcUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	static {
		try {
			Properties properties = new Properties();
			//得到配置文件的流通道
			InputStream in = jdbcUtil.class.getResourceAsStream("/db.properties");
			properties.load(in);
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			driverClass = properties.getProperty("driverClass");
			//注册Driver
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("驱程程序注册出错");
		}
	}
	
	//得到连接
	public static Connection getConnection() {
		try {
			Connection cnn = DriverManager.getConnection(url, user, password);
			return cnn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//关闭连接
	public static void close(Connection cnn, Statement st) {
		if (st != null) {
			try {
				st.close();
				st = null;     //告知系统回收
			} catch (Exception e) {
				e.printStackTrace();
				//将错误转换成RuntimeException运行错误抛出
				throw new RuntimeException(e);   
			}
		}
		if (cnn != null) {
			try {
				cnn.close();
				cnn = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	//关闭连接重载
	public static void close(Connection cnn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (st != null) {
			try {
				st.close();
				rs = null;  
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if (cnn != null) {
			try {
				cnn.close();
				cnn = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}
