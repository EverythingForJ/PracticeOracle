import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class JDBCDemo5 {
	public static void main(String[] args) {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection(); // 2,3 step
		//System.out.println(conn);
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement(); // 4. step
			String sql = " SELECT ename, TO_CHAR(sal, '$999,999'), TO_CHAR(comm, '$999,999'), TO_CHAR(sal*12 + NVL(comm, 0), '$999,999') " + 
					" FROM emp " +
					" WHERE TO_CHAR(hiredate, 'YYYY') = '1981'";
			rs = stmt.executeQuery(sql); // 5. step		
			while(rs.next()) {
				String ename = rs.getString("ename");
				String sal = rs.getString(2);
				String comm = rs.getString(3);
				String annual_salary = rs.getString(4);
				System.out.println(ename+","+sal+","+comm+","+annual_salary);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		DBClose.close(conn); // 7. step
	}
}
