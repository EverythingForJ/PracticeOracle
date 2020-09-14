import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo2 {
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
					// 다른사람것을 쓰고싶다면 localhost(or 자기 IP)자리에 그사람의 ip를 넣기, enterprize는 orcl, expression은 ex쓰기.
					stmt = conn.createStatement(); //4. step
					String sql = "SELECT empno, ename, sal, TRUNC(sal/20) AS Daypay " + 
							"FROM emp " + 
							"WHERE deptno = 10";
					rs = stmt.executeQuery(sql); // 5.step
					System.out.println("사원번호, 사원명, 봉급, 일급");
					System.out.println("-------------------------------------------");
					while(rs.next()) {
						int empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						double sal = rs.getDouble("sal");
						int sal_day = rs.getInt("Daypay");
						System.out.println(empno+", " +ename+", " +sal+", "+ sal_day);
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
