package driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver {
	
    static {
    	try {
    		// 드라이버가 로딩이 되면서 이 코드 블럭이 실행이 된다.
    		// 드라이버 매니저를 로딩해서 (=이 코드를 실행해서)
    		System.out.print("static code area");
			DriverManager.registerDriver(new MyDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println("url:" + url);
		System.out.println("info:" + info);
		
		/* 연결 작업 */
		
		return new MyConnection();
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
