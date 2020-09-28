import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import oracle.jdbc.driver.OracleDriver;

public class MetadataDemo {
	public static void main(String[] args) throws SQLException{
		OracleDriver od = new OracleDriver();
		DriverManager.registerDriver(od); // Driver 등록
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println(driver);
			System.out.println("driver.getMajorVersion() = "+driver.getMajorVersion());
			System.out.println("driver.getMinorVersion() = "+driver.getMinorVersion());
		}
		
//		DriverManager.deregisterDriver(od); // Driver 해제
//		System.out.println("Driver 해제 후");
//		while(drivers.hasMoreElements()) {
//			Driver driver = drivers.nextElement();
//			System.out.println(driver);
//		}
	}
}
