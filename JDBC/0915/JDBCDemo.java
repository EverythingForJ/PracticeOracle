import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class JDBCDemo {
	public static void main(String[] args) throws SQLException{
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2, 3
		
		Statement stmt = conn.createStatement(); // 4
		String sql = "SELECT deptno, job, TO_CHAR(sum(sal), '$999,999'), TO_CHAR(trunc(avg(sal)), '$999,999'), TO_CHAR(max(sal), '$999,999'), TO_CHAR(min(sal), '$999,999'), count(*) " + 
				" FROM emp" + 
				" WHERE deptno IN(10,20,30)" + 
				" GROUP BY deptno, job\r\n" + 
				"ORDER BY deptno, job ASC";

		ResultSet rs = stmt.executeQuery(sql); // 5
		System.out.println("deptno, job, sum, avg, max, min, count");
		while(rs.next()) {
			int deptno = rs.getInt(1);;
			String job = rs.getString(2);
			String sum = rs.getString(3);
			String avg = rs.getString(4);
			String max = rs.getString(5);
			String min = rs.getString(6);
			int count = rs.getInt(7);
			System.out.println(deptno+", "+job+", "+sum+", "+avg+", "+max+", "+min+", "+count);
		}
		
		DBClose.close(conn, stmt, rs); // 7
	}
}
