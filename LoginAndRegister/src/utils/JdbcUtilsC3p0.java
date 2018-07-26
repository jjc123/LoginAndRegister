package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtilsC3p0 {
	// 得到连接
	public static Connection getConnection() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			Connection connection = comboPooledDataSource.getConnection();
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 关闭连接
	public static void close(Connection cnn, Statement st) {
		if (st != null) {
			try {
				st.close();
				st = null; // 告知系统回收
			} catch (Exception e) {
				e.printStackTrace();
				// 将错误转换成RuntimeException运行错误抛出
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

	// 关闭连接重载
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
