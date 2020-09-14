import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class JDBCDemo3 {
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
					sb.append(" SELECT ename, hiredate, NEXT_DAY(ADD_MONTHS(hiredate, 6),'월요일') FROM emp "); // String클래스보다 buffer가 속도가 훨씬 빠르다.
					sb.append(" WHERE deptno=20");
					// System.out.println("sql= " +sb.toString());
					
					rs = stmt.executeQuery(sb.toString()); // 5.
					while(rs.next()) {// 6.
						String ename = rs.getString(1);
						Date hiredate = rs.getDate("hiredate");
						Date next_monday = rs.getDate(3);
						System.out.println(ename + ","+ hiredate +","+next_monday);
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
