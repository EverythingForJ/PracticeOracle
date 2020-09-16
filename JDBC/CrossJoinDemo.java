import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class CrossJoinDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3
		
//		String sql = "SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc " +
//				"FROM emp e, dept d"; // 비표준 Cartesian Product
		String sql = "SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc " +
				"FROM emp e CROSS JOIN dept d"; // 표준 Cross Join
		
		
		Statement stmt = conn.createStatement(); // 4
		ResultSet rs = stmt.executeQuery(sql); // 5
		int count = 0;
		while(rs.next()) { // 6
			int empno = rs.getInt(1);
			String ename = rs.getString(2);
			double sal = rs.getDouble(3);
			int deptno = rs.getInt(4);
			String dname = rs.getString(5);
			String loc = rs.getString(6);
			
			System.out.println(++count + " : " + empno +", "+ ename +", "+ sal +", "+ deptno +", "+ dname +", "+ loc);
		}
		
		DBClose.close(conn, stmt, rs); // 7
	}
}
