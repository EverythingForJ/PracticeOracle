import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("찾고자 하시는 사원의 이름 : ");
		String name = scan.next();
		// 2. step
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					System.out.println("Driver Loading Failure");
				}
				
				// 3. 오라클 드라이버를 통해서 자바가 오라클DB에 접속하도록하자.
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","tiger");
					// 다른사람것을 쓰고싶다면 localhost(or 자기 IP)자리에 그사람의 ip를 넣기, enterprize는 orcl, expression은 ex쓰기.
					stmt = conn.createStatement(); //4. step
					String sql = "SELECT empno, ename, sal, job "+
					" FROM emp "+
							// " WHERE UPPER(ename) = UPPER('"+ name+"') ";
							// " WHERE UPPER(ename) = '"+ name.toUpperCase() +"' ";
					" WHERE UPPER(ename) LIKE '%"+ name.toUpperCase() +"%' ";
					//System.out.println("sql = "+sql);
					rs = stmt.executeQuery(sql); // 5.step
					while(rs.next()) {
						int empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						double sal = rs.getDouble("sal");
						String job = rs.getString("job");
						System.out.println(empno+", " +ename+", " +sal+", "+ job);
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
