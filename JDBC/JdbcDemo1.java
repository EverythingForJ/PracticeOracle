import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JdbcDemo1 {
	private static final String DRIVER;
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	
	static{
		DRIVER = "oracle.jdbc.driver.OracleDriver";
		URL = "jdbc:oracle:thin:@localhost:1521:orcl";
		USER = "scott";
		PASSWORD = "tiger";
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER); // 2.
		Connection conn = DriverManager.getConnection(URL,USER,PASSWORD); // 3.
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
