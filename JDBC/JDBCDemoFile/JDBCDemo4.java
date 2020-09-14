import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class JDBCDemo4 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					System.out.println("Driver Loading Failure");
				}
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
					stmt = conn.createStatement(); //4. step
					StringBuffer sb = new StringBuffer();
					sb.append("SELECT sysdate, TO_CHAR(sysdate, 'CC YYYY.MM.DD DAY A.M. HH:MI:SS')FROM dual"); // String클래스보다 buffer가 속도가 훨씬 빠르다.
					// System.out.println("sql= " +sb.toString());
					
					rs = stmt.executeQuery(sb.toString()); // 5.
					while(rs.next()) {// 6.
						Date sysdate = rs.getDate("sysdate");
						String date = rs.getString(2);
						System.out.println(sysdate +","+ date);
					}
				}catch (SQLException e) {
					System.out.println("연결 실패..");
				}
				
				// 7. step
				try{
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				}catch(SQLException e) {
					System.out.println("닫는걸 실패...");
				}
	}
}
