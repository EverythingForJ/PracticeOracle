import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import oracle.jdbc.driver.OracleDriver;

public class MetadataDemo {
	public static void main(String[] args) throws SQLException{
		OracleDriver od = new OracleDriver();
		DriverManager.registerDriver(od); // Driver ���
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println(driver);
			System.out.println("driver.getMajorVersion() = "+driver.getMajorVersion());
			System.out.println("driver.getMinorVersion() = "+driver.getMinorVersion());
		}
		
//		DriverManager.deregisterDriver(od); // Driver ����
//		System.out.println("Driver ���� ��");
//		while(drivers.hasMoreElements()) {
//			Driver driver = drivers.nextElement();
//			System.out.println(driver);
//		}
	}
}
