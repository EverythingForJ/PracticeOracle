import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

public class JdbcDemo2 {
	private Properties infos;
	
	public JdbcDemo2() {
		infos = new Properties();
		File file = new File("C:/temp/dbinfo.properties");
		try {
			infos.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JdbcDemo2 jd = new JdbcDemo2();
		
		Class.forName(jd.infos.getProperty("DBDRIVER")); // 2.
		Connection conn = DriverManager.getConnection(jd.infos.getProperty("DBURL"),jd.infos.getProperty("DBUSER"),jd.infos.getProperty("DBPASSWORD")); // 3.
		Statement stmt = conn.createStatement(); // 4. 
		String sql = "SELECT sysdate FROM dual";
		ResultSet rs = stmt.executeQuery(sql); // 5. 
		while(rs.next()) { // 6.
			Date today = rs.getDate(1);
			System.out.println(today);
		}
		try{ // 7.
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			System.out.println("´Ý´Â°É ½ÇÆÐ...");
		}
	}
}
